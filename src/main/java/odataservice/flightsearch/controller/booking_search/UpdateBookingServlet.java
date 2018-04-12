package odataservice.flightsearch.controller.booking_search;

import odataservice.flightsearch.model.BookingSearchResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
@WebServlet("/updateBooking")
public class UpdateBookingServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String bookingId = req.getParameter("bookingId");

        final BookingSearchResult departureBookingSearchResult = (BookingSearchResult) req.getSession().getAttribute("departureBookingSearchResult");
        final BookingSearchResult returnBookingSearchResult = (BookingSearchResult) req.getSession().getAttribute("returnBookingSearchResult");

        final BookingSearchResult bookingSearchResult;
        if (departureBookingSearchResult.getBooking().getBookId().equals(bookingId)) {
            bookingSearchResult = departureBookingSearchResult;
        } else {
            bookingSearchResult = returnBookingSearchResult;
        }

        req.getSession().setAttribute("bookingSearchResult", bookingSearchResult);
        req.getRequestDispatcher("/updateBooking.jsp").forward(req, resp);
    }

}
