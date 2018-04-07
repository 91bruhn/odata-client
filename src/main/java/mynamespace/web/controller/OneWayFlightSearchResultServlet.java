////////////////////////////////////////////////////////////////////////////////
//
// Created by BBruhns on 06.04.2018.
//
// Copyright (c) 2006 - 2018 FORCAM GmbH. All rights reserved.
////////////////////////////////////////////////////////////////////////////////

package mynamespace.web.controller;

import mynamespace.web.model.FlightSearchResult;
import mynamespace.web.service.DataTransformator;
import org.apache.commons.lang3.StringUtils;
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
@WebServlet("/oneWaySearchResult")
public class OneWayFlightSearchResultServlet extends HttpServlet {

    ODataClient mODataClient = ODataClientFactory.getClient();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String inputAirportOfDeparture = req.getParameter("inputAirportOfDeparture");
        final String inputAirportOfArrival = req.getParameter("inputAirportOfArrival");
        final String inputDepartureFlightDate = req.getParameter("inputDepartureFlightDate");

        final String serviceUri = "http://localhost:8080/flightDataManagement.svc/";
        final String entitySetName = "Connections";

        //1 -- http://localhost:8080/flightDataManagement.svc/Connections?$filter=DepartureCity eq 'NEWYORK' and ArrivalCity eq 'SANFRANCISCO'
        //2 -- &$expand=Flights($select=FlightDate,MaxSeatsEconomyClass,OccupiedSeatsInEconomyClass,MaxSeatsBusinessClass,OccupiedSeatsBusinessClass,MaxSeatsFirstClass,OccupiedSeatsFirstClass),
        //3 Carrier($select=CarrierName,URL)
        //TODO use for Flights, Connection vllt Constants
        final URI absoluteUri = mODataClient.newURIBuilder(serviceUri).appendEntitySetSegment(entitySetName).filter(
            "DepartureCity eq 'NEWYORK' and ArrivalCity eq 'SANFRANCISCO'").expandWithSelect("Flights",
                                                                                             "FlightDate",
                                                                                             "MaxSeatsEconomyClass",
                                                                                             "OccupiedSeatsInEconomyClass",
                                                                                             "MaxSeatsBusinessClass",
                                                                                             "OccupiedSeatsBusinessClass",
                                                                                             "MaxSeatsFirstClass",
                                                                                             "OccupiedSeatsFirstClass").expandWithSelect("Carrier",
                                                                                                                                         "CarrierName",
                                                                                                                                         "URL").build();
        final ClientEntitySetIterator<ClientEntitySet, ClientEntity> iterator = readEntities(absoluteUri);
        final List<FlightSearchResult> searchResults = new ArrayList<>();
        //        iterator.forEachRemaining(clientEntity -> {
        //            searchResults.add(DataTransformator.transformFlightSearchResultRequestToFlightSearchResult(iterator.next(),
        //                                                                                                                    inputDepartureFlightDate,
        //                                                                                                                    inputReturnFlightDate));
        //        });
        while (iterator.hasNext()) {
            searchResults.add(DataTransformator.transformFlightSearchResultRequestToFlightSearchResult(iterator.next(),
                                                                                                       inputDepartureFlightDate,
                                                                                                       StringUtils.EMPTY));
        }
        //        req.getSession().setAttribute("searchResults", searchResults);
        req.getSession().setAttribute("searchResults", searchResults);
        req.setAttribute("searchResults", searchResults);
        req.getRequestDispatcher("/oneWaySearchResult.jsp").forward(req, resp);
    }

    public ClientEntitySetIterator<ClientEntitySet, ClientEntity> readEntities(String serviceUri, String entitySetName) {
        URI absoluteUri = mODataClient.newURIBuilder(serviceUri).appendEntitySetSegment(entitySetName).build();
        return readEntities(absoluteUri);
    }

    private ClientEntitySetIterator<ClientEntitySet, ClientEntity> readEntities(URI absoluteUri) {
        //        System.out.println("URI = " + absoluteUri);
        ODataEntitySetIteratorRequest<ClientEntitySet, ClientEntity> request = mODataClient.getRetrieveRequestFactory()
                                                                                           .getEntitySetIteratorRequest(absoluteUri);
        // odata4 sample/server limitation not handling metadata=full
        request.setAccept("application/json;odata.metadata=minimal");
        ODataRetrieveResponse<ClientEntitySetIterator<ClientEntitySet, ClientEntity>> response = request.execute();

        return response.getBody();
    }

}
