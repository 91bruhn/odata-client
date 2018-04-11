package mynamespace.web.controller;

import mynamespace.web.model.Booking;
import mynamespace.web.model.BookingSearchResult;
import mynamespace.web.util.DataTransformator;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.request.cud.ODataEntityUpdateRequest;
import org.apache.olingo.client.api.communication.request.cud.UpdateType;
import org.apache.olingo.client.api.communication.response.ODataEntityUpdateResponse;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.core.ODataClientFactory;
import org.apache.olingo.commons.api.edm.FullQualifiedName;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

        final ClientEntity requestEntityBooking = DataTransformator.buildBookingEntity(mODataClient, sex, flightClass, luggWeight, isSmoker, carrId, connId, flightDate);
        final int httpStatusCodeOfUpdate = this.updateEntity(this.createCreateBookingURI(bookingId), requestEntityBooking);
        if (httpStatusCodeOfUpdate == 204) {
            Booking updateBooking = bookingSearchResult.getBooking();
            updateBooking.setCustType(DataTransformator.transformSex(sex));
            updateBooking.setFlightClass(DataTransformator.transformFlightClass(flightClass));
            updateBooking.setLuggWeight(luggWeight);
            updateBooking.setSmoker(DataTransformator.transformIsSmoker(isSmoker));

            req.setAttribute("updatedResponseBooking", updateBooking);
            req.getRequestDispatcher("/displayUpdatedBooking.jsp").forward(req, resp);
        }
    }

    private URI createCreateBookingURI(String bookingId) {
        final String serviceUri = "http://localhost:8080/flightDataManagement.svc/";
        final String entitySetNameBookings = "Bookings";

        return mODataClient.newURIBuilder(serviceUri).appendEntitySetSegment(entitySetNameBookings).appendKeySegment(bookingId).build();
    }

    private int updateEntity(URI absoluteUri, ClientEntity ce) {
        final ODataEntityUpdateRequest<ClientEntity> request = mODataClient.getCUDRequestFactory().getEntityUpdateRequest(absoluteUri, UpdateType.PATCH, ce);
        // odata4 sample/server limitation not handling metadata=full
        request.setAccept("application/json;odata.metadata=minimal");
        final ODataEntityUpdateResponse<ClientEntity> response = request.execute();

        return response.getStatusCode();
    }

}