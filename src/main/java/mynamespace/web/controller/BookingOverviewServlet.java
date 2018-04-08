////////////////////////////////////////////////////////////////////////////////
//
// Created by BBruhns on 08.04.2018.
//
// Copyright (c) 2006 - 2018 FORCAM GmbH. All rights reserved.
////////////////////////////////////////////////////////////////////////////////

package mynamespace.web.controller;

import mynamespace.web.model.ConnectionSearchResult;
import mynamespace.web.service.DataTransformator;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.request.retrieve.ODataEntitySetIteratorRequest;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientEntitySet;
import org.apache.olingo.client.api.domain.ClientEntitySetIterator;
import org.apache.olingo.client.core.ODataClientFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@WebServlet("/bookingOverview")
public class BookingOverviewServlet extends HttpServlet {

    ODataClient mODataClient = ODataClientFactory.getClient();

    //<a href="/booking.jsp?flightDate=${flight.flightDate}&connId=${searchResult.connId}&carrierCode=${flight.mCarrierId}" class="btn btn-info" role="button">Buchen</a>
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String flightDate = req.getParameter("flightDate");
        final String connectionId = req.getParameter("connId");
        final String carrierCode = req.getParameter("carrierCode");

        //        req.setAttribute("connectionSearchResult", connectionSearchResult);
        //        req.getSession().setAttribute("inputAirportOfDeparture", inputAirportOfDeparture);
        //        req.getSession().setAttribute("inputAirportOfArrival", inputAirportOfArrival);
        //        req.getSession().setAttribute("inputDepartureFlightDate", inputDepartureFlightDate);
        //        req.getSession().setAttribute("inputReturnFlightDate", inputReturnFlightDate);
        //        req.setAttribute("connectionSearchResult", connectionSearchResult);

        //        http://localhost:8080/flightDataManagement.svc/Flights(CarrierCode='AA',FlightConnectionNumber='17',FlightDate='01.10.2017')?$expand=Connection,Carrier
        final String serviceUri = "http://localhost:8080/flightDataManagement.svc/";
        final String entitySetNameFlights = "Flights";
        final String entitySetNameConnection = "Connection";
        final String entitySetNameCarrier = "Carrier";
        final URI absoluteUri = mODataClient.newURIBuilder(serviceUri)
                                            .appendEntitySetSegment(entitySetNameFlights)
                                            .select("Airfare",
                                                    "FlightDate",
                                                    "LocalCurrencyOfAirline",
                                                    "MaxSeatsEconomyClass",
                                                    "OccupiedSeatsInEconomyClass",
                                                    "MaxSeatsBusinessClass",
                                                    "OccupiedSeatsBusinessClass",
                                                    "MaxSeatsFirstClass",
                                                    "OccupiedSeatsFirstClass")
                                            .expandWithSelect(entitySetNameConnection,
                                                              "DepartureCountryKey",
                                                              "DepartureCity",
                                                              "DepartureAirport",
                                                              "ArrivalCountryKey",
                                                              "ArrivalCity",
                                                              "ArrivalAirport",
                                                              "FlightTime",
                                                              "DepartureTime",
                                                              "ArrivalTime")
                                            .expandWithSelect(entitySetNameCarrier, "CarrierName", "URL")
                                            .build();
        final ClientEntitySetIterator<ClientEntitySet, ClientEntity> iterator = readEntities(absoluteUri);
        final List<ConnectionSearchResult> connectionSearchResult = new ArrayList<>();
        while (iterator.hasNext()) {
            connectionSearchResult.add(DataTransformator.transformFlightSearchResultRequestToFlightSearchResult(iterator.next()));
        }

        req.setAttribute("flightDate", flightDate);
        req.setAttribute("connectionId", connectionId);
        req.setAttribute("carrierCode", carrierCode);

        req.getRequestDispatcher("/booking.jsp").forward(req, resp);
    }

    private ClientEntitySetIterator<ClientEntitySet, ClientEntity> readEntities(URI absoluteUri) {
        final ODataEntitySetIteratorRequest<ClientEntitySet, ClientEntity> request = mODataClient.getRetrieveRequestFactory().getEntitySetIteratorRequest(
            absoluteUri);
        // todo odata4 sample/server limitation not handling metadata=full
        request.setAccept("application/json;odata.metadata=minimal");
        final ODataRetrieveResponse<ClientEntitySetIterator<ClientEntitySet, ClientEntity>> response = request.execute();

        return response.getBody();
    }

}

