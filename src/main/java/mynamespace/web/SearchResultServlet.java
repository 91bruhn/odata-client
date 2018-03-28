////////////////////////////////////////////////////////////////////////////////
//
// Created by bruhn on 27.03.2018.
//
// Copyright (c) 2006 - 2018 FORCAM GmbH. All rights reserved.
////////////////////////////////////////////////////////////////////////////////

package mynamespace.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
@WebServlet("/result")
public class SearchResultServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String inputAirportOfDeparture = (String) req.getAttribute("inputAirportOfDeparture");
        String inputAirportOfArrival = (String) req.getAttribute("inputAirportOfArrival");
        String inputDepartureFlightDate = (String) req.getAttribute("inputDepartureFlightDate");
        String inputReturnFlightDate = (String) req.getAttribute("inputReturnFlightDate");
        boolean oneWayFlight = (boolean) req.getAttribute("oneWayFlight");

    }

}
