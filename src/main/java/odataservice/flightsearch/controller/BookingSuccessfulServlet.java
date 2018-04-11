package odataservice.flightsearch.controller;

import odataservice.flightsearch.util.DataTransformator;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.request.cud.ODataEntityCreateRequest;
import org.apache.olingo.client.api.communication.response.ODataEntityCreateResponse;
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
@WebServlet("/bookingSuccessful")
public class BookingSuccessfulServlet extends HttpServlet {

    private ODataClient mODataClient = ODataClientFactory.getClient();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String sex = (String) req.getSession().getAttribute("sex");
        final String flightClass = (String) req.getSession().getAttribute("flightClass");
        final double luggWeight = Double.valueOf((String) req.getSession().getAttribute("luggWeight"));
        final String isSmoker = (String) req.getSession().getAttribute("isSmoker");

        final String depCarrId = (String) req.getSession().getAttribute("chosenDepartureCarrId");
        final String depConnId = (String) req.getSession().getAttribute("chosenDepartureConnId");
        final String depflightDate = (String) req.getSession().getAttribute("chosenDepartureFlightDate");

        final String retCarrId = (String) req.getSession().getAttribute("chosenReturnCarrId");
        final String retConnId = (String) req.getSession().getAttribute("chosenReturnConnId");
        final String retflightDate = (String) req.getSession().getAttribute("chosenReturnFlightDate");

        ClientEntity responseEntityBooking;
        String responseBookingId;

        // send new departure flight booking to the server and retrieve bookingId
        final ClientEntity departureBookingEntity = DataTransformator.buildBookingEntity(mODataClient,
                                                                                         sex,
                                                                                         flightClass,
                                                                                         luggWeight,
                                                                                         isSmoker,
                                                                                         depCarrId,
                                                                                         depConnId,
                                                                                         depflightDate);
        responseEntityBooking = createEntity(this.createCreateBookingURI(), departureBookingEntity);
        responseBookingId = DataTransformator.getBookingIdFromClientEntity(responseEntityBooking);
        req.setAttribute("departureFlightBookingId", responseBookingId);

        // send new return flight booking to the server and retrieve bookingId
        final ClientEntity returnBookingEntity = DataTransformator.buildBookingEntity(mODataClient,
                                                                                      sex,
                                                                                      flightClass,
                                                                                      luggWeight,
                                                                                      isSmoker,
                                                                                      retCarrId,
                                                                                      retConnId,
                                                                                      retflightDate);
        responseEntityBooking = createEntity(this.createCreateBookingURI(), returnBookingEntity);
        responseBookingId = DataTransformator.getBookingIdFromClientEntity(responseEntityBooking);
        req.setAttribute("returnFlightBookingId", responseBookingId);

        req.getRequestDispatcher("/bookingSuccessful.jsp").forward(req, resp);
    }

    private URI createCreateBookingURI() {
        //http://localhost:8080/flightDataManagement.svc/Flights(CarrierCode='AA',FlightConnectionNumber='17',FlightDate='01.10.2017')?$expand=Connection,Carrier
        final String serviceUri = "http://localhost:8080/flightDataManagement.svc/";
        final String entitySetNameBookings = "Bookings";

        return mODataClient.newURIBuilder(serviceUri).appendEntitySetSegment(entitySetNameBookings).build();
    }

    private ClientEntity createEntity(URI absoluteUri, ClientEntity ce) {
        final ODataEntityCreateRequest<ClientEntity> request = mODataClient.getCUDRequestFactory().getEntityCreateRequest(absoluteUri, ce);
        // odata4 sample/server limitation not handling metadata=full
        request.setAccept("application/json;odata.metadata=minimal");
        final ODataEntityCreateResponse<ClientEntity> response = request.execute();

        return response.getBody();
    }

}
