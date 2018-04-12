package odataservice.flightsearch.controller.booking_search;

import odataservice.flightsearch.model.Booking;
import odataservice.flightsearch.model.BookingSearchResult;
import odataservice.flightsearch.util.DataTransformator;
import odataservice.flightsearch.util.EntityNames;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.request.cud.ODataEntityUpdateRequest;
import org.apache.olingo.client.api.communication.request.cud.UpdateType;
import org.apache.olingo.client.api.communication.response.ODataEntityUpdateResponse;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.core.ODataClientFactory;

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
@WebServlet("/bookingUpdateSuccessful")
public class BookingUpdateSuccessfulServlet extends HttpServlet {

    private ODataClient mODataClient = ODataClientFactory.getClient();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String sex = req.getParameter("inputSex");
        final String flightClass = req.getParameter("inputFlightClass");
        final double luggWeight = Double.valueOf(req.getParameter("inputLuggWeight"));
        final String isSmoker = req.getParameter("isSmoker");

        final BookingSearchResult bookingSearchResult = (BookingSearchResult) req.getSession().getAttribute("bookingSearchResult");
        final String carrId = bookingSearchResult.getCarrier().getCarrId();
        final String connId = bookingSearchResult.getConnection().getConnId();
        final String flightDate = bookingSearchResult.getBooking().getFlightId();
        final String bookingId = bookingSearchResult.getBooking().getBookId();

        final ClientEntity requestEntityBooking = DataTransformator.buildBookingEntity(mODataClient,
                                                                                       sex,
                                                                                       flightClass,
                                                                                       luggWeight,
                                                                                       isSmoker,
                                                                                       carrId,
                                                                                       connId,
                                                                                       flightDate);
        final int httpStatusCodeOfUpdate = this.updateEntity(this.createCreateBookingURI(bookingId), requestEntityBooking);
        //there is no payload returned for an update operation, the http status code 204 suggests the update was successful
        if (httpStatusCodeOfUpdate == 204) {
            final Booking updateBooking = bookingSearchResult.getBooking();
            updateBooking.setCustType(DataTransformator.transformSex(sex));
            updateBooking.setFlightClass(DataTransformator.transformFlightClass(flightClass));
            updateBooking.setLuggWeight(luggWeight);
            updateBooking.setSmoker(DataTransformator.transformIsSmoker(isSmoker));

            req.setAttribute("updatedResponseBooking", updateBooking);
            req.getRequestDispatcher("/displayUpdatedBooking.jsp").forward(req, resp);
        }
    }

    private URI createCreateBookingURI(String bookingId) {
        return mODataClient.newURIBuilder(EntityNames.SERVICE_URI).appendEntitySetSegment(EntityNames.ES_SBOOK_NAME).appendKeySegment(bookingId).build();
    }

    private int updateEntity(URI absoluteUri, ClientEntity ce) {
        final ODataEntityUpdateRequest<ClientEntity> request = mODataClient.getCUDRequestFactory().getEntityUpdateRequest(absoluteUri, UpdateType.PATCH, ce);
        request.setAccept(EntityNames.HEADER_ACCEPT_JSON);
        final ODataEntityUpdateResponse<ClientEntity> response = request.execute();

        return response.getStatusCode();
    }

}