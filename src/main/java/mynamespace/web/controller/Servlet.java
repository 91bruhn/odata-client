////////////////////////////////////////////////////////////////////////////////
//
// Created by BBruhns on 17.03.2018.
//
// Copyright (c) 2006 - 2018 FORCAM GmbH. All rights reserved.
////////////////////////////////////////////////////////////////////////////////

package mynamespace.web.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 */

public class Servlet extends HttpServlet {

    // ------------------------------------------------------------------------
    // constants
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // members
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // methods
    // ------------------------------------------------------------------------

//    public void init() throws ServletException {
//        OlingoSampleApp app = new OlingoSampleApp();
//        //        app.perform("http://localhost:8080/cars.svc");
//        try {
//            app.perform("http://localhost:8080/flightDataManagement.svc");
//
//        } catch (Exception e) {
//
//        }
//    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final HttpSession session = req.getSession(true);
//            Checker check = (Checker) session.getAttribute(Checker.class.getName());
//            if (check == null) {
//                check = new Checker();
//                session.setAttribute(Checker.class.getName(), check);
//            }

        } catch (RuntimeException e) {
            throw new ServletException("Server Error occurred in FlightDataServlet");
        }
    }
}
