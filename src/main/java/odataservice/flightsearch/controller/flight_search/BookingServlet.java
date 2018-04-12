package odataservice.flightsearch.controller.flight_search;

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
        req.setAttribute("flightDate", req.getParameter("flightDate"));
        req.setAttribute("connectionId", req.getParameter("connId"));
        req.setAttribute("carrierCode", req.getParameter("carrierCode"));
        //request parameters - from chosen return flight
        req.getSession().setAttribute("chosenReturnFlightDate", req.getParameter("flightDate"));
        req.getSession().setAttribute("chosenReturnConnId", req.getParameter("connId"));
        req.getSession().setAttribute("chosenReturnCarrId", req.getParameter("carrId"));

        req.getRequestDispatcher("/booking.jsp").forward(req, resp);
    }

}

