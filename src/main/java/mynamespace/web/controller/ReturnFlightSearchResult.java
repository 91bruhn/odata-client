////////////////////////////////////////////////////////////////////////////////
//
// Created by BBruhns on 07.04.2018.
//
// Copyright (c) 2006 - 2018 FORCAM GmbH. All rights reserved.
////////////////////////////////////////////////////////////////////////////////

package mynamespace.web.controller;

import mynamespace.web.model.FlightSearchResult;
import mynamespace.web.service.RequestResultDataTransformator;
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
@WebServlet("/returnFlightSearchResult")
public class ReturnFlightSearchResult extends HttpServlet {

    ODataClient mODataClient = ODataClientFactory.getClient();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String inputAirportOfDeparture = (String) req.getSession().getAttribute("inputAirportOfDeparture");
        final String inputAirportOfArrival = (String) req.getSession().getAttribute("inputAirportOfArrival");
        final String inputReturnFlightDate = (String) req.getSession().getAttribute("inputReturnFlightDate");

        final String serviceUri = "http://localhost:8080/flightDataManagement.svc/";
        final String entitySetName = "Connections";
        //1 -- http://localhost:8080/flightDataManagement.svc/Connections?$filter=DepartureCity eq 'NEWYORK' and ArrivalCity eq 'SANFRANCISCO'
        //2 -- &$expand=Flights($select=FlightDate,MaxSeatsEconomyClass,OccupiedSeatsInEconomyClass,MaxSeatsBusinessClass,OccupiedSeatsBusinessClass,MaxSeatsFirstClass,OccupiedSeatsFirstClass),
        //3 Carrier($select=CarrierName,URL)
        //TODO use for Flights, Connection vllt Constants
        //TODO naming, extra methode?
        //TODO umgedreht
        final URI absoluteUri = mODataClient.newURIBuilder(serviceUri)
                                            .appendEntitySetSegment(entitySetName)
                                            .filter("DepartureCity eq '" + inputAirportOfArrival + "' and ArrivalCity eq '" + inputAirportOfDeparture + "'")
                                            .expandWithSelect("Flights",
                                                              "FlightDate",
                                                              "MaxSeatsEconomyClass",
                                                              "OccupiedSeatsInEconomyClass",
                                                              "MaxSeatsBusinessClass",
                                                              "OccupiedSeatsBusinessClass",
                                                              "MaxSeatsFirstClass",
                                                              "OccupiedSeatsFirstClass")
                                            .expandWithSelect("Carrier", "CarrierName", "URL")
                                            .build();
        final ClientEntitySetIterator<ClientEntitySet, ClientEntity> iterator = readEntities(absoluteUri);
        final List<FlightSearchResult> returnFlightSearchResults = new ArrayList<>();

        while (iterator.hasNext()) {
            returnFlightSearchResults.add(RequestResultDataTransformator.transformFlightSearchResultRequestToFlightSearchResult(iterator.next(),
                                                                                                                                inputReturnFlightDate));
        }

        req.getSession().setAttribute("returnFlightSearchResults", returnFlightSearchResults);
        //        req.getSession().setAttribute("inputReturnFlightDate", inputReturnFlightDate);
        req.setAttribute("returnFlightSearchResults", returnFlightSearchResults);
        req.getRequestDispatcher("/returnFlightSearchResults.jsp").forward(req, resp);
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
