package mynamespace.web.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
@WebServlet("/booking")
public class BookingServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String flightDate = req.getParameter("flightDate");
        final String connectionId = req.getParameter("connId");
        final String carrierCode = req.getParameter("carrierCode");
        //request parameters - from chosen return flight
        final String chosenReturnFlightDate = req.getParameter("flightDate");
        final String chosenReturnConnId = req.getParameter("connId");
        final String chosenReturnCarrId = req.getParameter("carrId");

        req.setAttribute("flightDate", flightDate);
        req.setAttribute("connectionId", connectionId);
        req.setAttribute("carrierCode", carrierCode);
        req.getSession().setAttribute("chosenReturnFlightDate", chosenReturnFlightDate);
        req.getSession().setAttribute("chosenReturnConnId", chosenReturnConnId);
        req.getSession().setAttribute("chosenReturnCarrId", chosenReturnCarrId);

        req.getRequestDispatcher("/booking.jsp").forward(req, resp);
    }

}

