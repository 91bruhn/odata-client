package odataservice.flightsearch.controller;

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

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final HttpSession session = req.getSession(true);
        } catch (RuntimeException e) {
            throw new ServletException("Server Error occurred in FlightDataServlet");
        }
    }
}
