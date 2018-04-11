package mynamespace.web.controller;

import mynamespace.web.model.BookingSearchResult;
import mynamespace.web.util.DataTransformator;
import org.apache.commons.lang3.StringUtils;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.request.retrieve.ODataEntityRequest;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.core.ODataClientFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

/**
 *
 */
@WebServlet("/bookingSearchResult")
public class BookingSearchResultServlet extends HttpServlet {

    ODataClient mODataClient = ODataClientFactory.getClient();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String bookingIdDepFlight = req.getParameter("inputBookingIdDepFlight");
        final String bookingIdRetFlight = req.getParameter("inputBookingIdRetFlight");

        ClientEntity clientEntityBooking = readEntity(createFlightSearchRequestURI(bookingIdDepFlight));
        final BookingSearchResult departureBookingSearchResult = DataTransformator.transformBookingSearchResultRequestToBookingSearchResult(clientEntityBooking);
        BookingSearchResult returnBookingSearchResult = null;

        if (StringUtils.isNotEmpty(bookingIdRetFlight)) {
            clientEntityBooking = readEntity(createFlightSearchRequestURI(bookingIdRetFlight));
            returnBookingSearchResult = DataTransformator.transformBookingSearchResultRequestToBookingSearchResult(clientEntityBooking);
        }

        final RequestDispatcher servletRequest;
        if (returnBookingSearchResult != null) {
            req.getSession().setAttribute("returnBookingSearchResult", returnBookingSearchResult);
            servletRequest = req.getRequestDispatcher("/bookingSearchResult.jsp");
        } else {
            servletRequest = req.getRequestDispatcher("/singleBookingSearchResult.jsp");
        }

        req.getSession().setAttribute("departureBookingSearchResult", departureBookingSearchResult);
        servletRequest.forward(req, resp);
    }

    private URI createFlightSearchRequestURI(String bookingKeyValue) {
        final String serviceUri = "http://localhost:8080/flightDataManagement.svc/";

        final String entitySetNameBookings = "Bookings";
        //        final String entitySetNameFlight = "Flight";
        //        final String entitySetNameFlights = "Flights";//TODO name -> Collection?
        final String entitySetNameConnection = "Connection";
        final String entitySetNameCarrier = "Carrier";//TODO specific

        return mODataClient.newURIBuilder(serviceUri).appendEntitySetSegment(entitySetNameBookings).appendKeySegment(bookingKeyValue).expand(
            entitySetNameConnection,
            entitySetNameCarrier).build();
    }

    private ClientEntity readEntity(URI absoluteUri) {
        ODataEntityRequest<ClientEntity> request = mODataClient.getRetrieveRequestFactory().getEntityRequest(absoluteUri);
        // odata4 sample/server limitation not handling metadata=full
        request.setAccept("application/json;odata.metadata=minimal");
        ODataRetrieveResponse<ClientEntity> response = request.execute();

        return response.getBody();
    }

}
