////////////////////////////////////////////////////////////////////////////////
//
// Created by BBruhns on 07.04.2018.
//
// Copyright (c) 2006 - 2018 FORCAM GmbH. All rights reserved.
////////////////////////////////////////////////////////////////////////////////

package mynamespace.web.controller;

import mynamespace.web.model.FlightSearchResult;
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
@WebServlet("/booking")
//"CarrierCode": "AA", "FlightConnectionNumber": "17", "FlightDate": "01.10.2017",
public class BookingServlet  extends HttpServlet {

//    ODataClient mODataClient = ODataClientFactory.getClient();
//<a href="/booking.jsp?flightDate=${flight.flightDate}&connId=${searchResult.connId}&carrierCode=${flight.mCarrierId}" class="btn btn-info" role="button">Buchen</a>
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String flightDate = req.getParameter("flightDate");
        final String connectionId = req.getParameter("connId");
        final String carrierCode = req.getParameter("carrierCode");

//        final String serviceUri = "http://localhost:8080/flightDataManagement.svc/";
//        final String entitySetName = "Connections";
//        req.setAttribute("searchResults", searchResults);
        //        req.getSession().setAttribute("inputAirportOfDeparture", inputAirportOfDeparture);
        //        req.getSession().setAttribute("inputAirportOfArrival", inputAirportOfArrival);
        //        req.getSession().setAttribute("inputDepartureFlightDate", inputDepartureFlightDate);
        //        req.getSession().setAttribute("inputReturnFlightDate", inputReturnFlightDate);
        //        req.setAttribute("searchResults", searchResults);

        req.setAttribute("flightDate", flightDate);
        req.setAttribute("connectionId", connectionId);
        req.setAttribute("carrierCode", carrierCode);

        req.getRequestDispatcher("/booking.jsp").forward(req, resp);
    }

//    private ClientEntitySetIterator<ClientEntitySet, ClientEntity> readEntities(URI absoluteUri) {
//        final ODataEntitySetIteratorRequest<ClientEntitySet, ClientEntity> request = mODataClient.getRetrieveRequestFactory().getEntitySetIteratorRequest(
//            absoluteUri);
//        // todo odata4 sample/server limitation not handling metadata=full
//        request.setAccept("application/json;odata.metadata=minimal");
//        final ODataRetrieveResponse<ClientEntitySetIterator<ClientEntitySet, ClientEntity>> response = request.execute();
//
//        return response.getBody();
//    }

}

