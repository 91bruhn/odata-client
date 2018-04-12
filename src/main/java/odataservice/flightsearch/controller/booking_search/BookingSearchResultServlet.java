package odataservice.flightsearch.controller.booking_search;

import odataservice.flightsearch.model.BookingSearchResult;
import odataservice.flightsearch.util.DataTransformator;
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

import static odataservice.flightsearch.util.EntityNames.ES_SBOOK_NAME;
import static odataservice.flightsearch.util.EntityNames.ET_SCARR_NAME;
import static odataservice.flightsearch.util.EntityNames.ET_SPFLI_NAME;
import static odataservice.flightsearch.util.EntityNames.HEADER_ACCEPT_JSON;
import static odataservice.flightsearch.util.EntityNames.SERVICE_URI;

/**
 *
 */
@WebServlet("/bookingSearchResult")
public class BookingSearchResultServlet extends HttpServlet {

    private ODataClient mODataClient = ODataClientFactory.getClient();

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
        return mODataClient.newURIBuilder(SERVICE_URI).appendEntitySetSegment(ES_SBOOK_NAME).appendKeySegment(bookingKeyValue).expand(ET_SPFLI_NAME,
                                                                                                                                      ET_SCARR_NAME).build();
    }

    private ClientEntity readEntity(URI absoluteUri) {
        final ODataEntityRequest<ClientEntity> request = mODataClient.getRetrieveRequestFactory().getEntityRequest(absoluteUri);
        request.setAccept(HEADER_ACCEPT_JSON);
        ODataRetrieveResponse<ClientEntity> response = request.execute();

        return response.getBody();
    }

}
