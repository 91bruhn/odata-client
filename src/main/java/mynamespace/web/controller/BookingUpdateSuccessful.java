////////////////////////////////////////////////////////////////////////////////
//
// Created by BBruhns on 09.04.2018.
//
// Copyright (c) 2006 - 2018 FORCAM GmbH. All rights reserved.
////////////////////////////////////////////////////////////////////////////////

package mynamespace.web.controller;

import mynamespace.web.model.Booking;
import mynamespace.web.model.BookingSearchResult;
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
public class BookingUpdateSuccessful extends HttpServlet {

    ODataClient mODataClient = ODataClientFactory.getClient();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String sex = req.getParameter("inputSex");
        final String flightClass = req.getParameter("inputFlightClass");
        final double luggWeight = Double.valueOf(req.getParameter("inputLuggWeight"));
        final String isSmoker = req.getParameter("isSmoker");

        final BookingSearchResult bookingSearchResult = (BookingSearchResult) req.getSession().getAttribute("departureBookingSearchResult");
        final String carrId = bookingSearchResult.getCarrier().getCarrId();
        final String connId = bookingSearchResult.getConnection().getConnId();
        final String flightDate = bookingSearchResult.getBooking().getFlightId();
        final String bookingId = bookingSearchResult.getBooking().getBookId();

        final ClientEntity requestEntityBooking = this.buildBookingEntity(sex, flightClass, luggWeight, isSmoker, carrId, connId, flightDate);
        final int httpStatusCodeOfUpdate = this.updateEntity(this.createCreateBookingURI(bookingId), requestEntityBooking);
        if (httpStatusCodeOfUpdate == 204) {
            Booking updateBooking = bookingSearchResult.getBooking();
            updateBooking.setCustType(this.transformSex(sex));
            updateBooking.setFlightClass(this.transformFlightClass(flightClass));
            updateBooking.setLuggWeight(luggWeight);
            updateBooking.setSmoker(this.transformIsSmoker(isSmoker));

            req.setAttribute("updatedResponseBooking", updateBooking);
            req.getRequestDispatcher("/displayUpdatedBooking.jsp").forward(req, resp);
        }
    }

    private URI createCreateBookingURI(String bookingId) {
        final String serviceUri = "http://localhost:8080/flightDataManagement.svc/";
        final String entitySetNameBookings = "Bookings";

        return mODataClient.newURIBuilder(serviceUri).appendEntitySetSegment(entitySetNameBookings).appendKeySegment(bookingId).build();
    }

    private ClientEntity buildBookingEntity(String sex, String flightClass, double luggWeight, String isSmoker, String carrierCode,
                                            String flightConnectionNumber, String FlightDate) {
        final String namespace = "OData.FlightDataManagement";
        final FullQualifiedName bookingFqn = new FullQualifiedName(namespace, "Booking");
        final ClientEntity entityBooking = mODataClient.getObjectFactory().newEntity(bookingFqn);

        entityBooking.getProperties().add(mODataClient.getObjectFactory().newPrimitiveProperty("CarrierCode",
                                                                                               mODataClient.getObjectFactory()
                                                                                                           .newPrimitiveValueBuilder()
                                                                                                           .buildString(carrierCode)));
        entityBooking.getProperties().add(mODataClient.getObjectFactory().newPrimitiveProperty("FlightConnectionNumber",
                                                                                               mODataClient.getObjectFactory()
                                                                                                           .newPrimitiveValueBuilder()
                                                                                                           .buildString(flightConnectionNumber)));
        entityBooking.getProperties().add(mODataClient.getObjectFactory().newPrimitiveProperty("FlightDate",
                                                                                               mODataClient.getObjectFactory()
                                                                                                           .newPrimitiveValueBuilder()
                                                                                                           .buildString(FlightDate)));
        entityBooking.getProperties().add(mODataClient.getObjectFactory().newPrimitiveProperty("Sex",
                                                                                               mODataClient.getObjectFactory()
                                                                                                           .newPrimitiveValueBuilder()
                                                                                                           .buildString(this.transformSex(sex))));
        entityBooking.getProperties().add(mODataClient.getObjectFactory().newPrimitiveProperty("IsSmoker",
                                                                                               mODataClient.getObjectFactory()
                                                                                                           .newPrimitiveValueBuilder()
                                                                                                           .buildBoolean(transformIsSmoker(isSmoker))));
        entityBooking.getProperties().add(mODataClient.getObjectFactory().newPrimitiveProperty("LuggageWeight",
                                                                                               mODataClient.getObjectFactory()
                                                                                                           .newPrimitiveValueBuilder()
                                                                                                           .buildDouble(luggWeight)));
        entityBooking.getProperties().add(mODataClient.getObjectFactory().newPrimitiveProperty("WeightUnit",
                                                                                               mODataClient.getObjectFactory()
                                                                                                           .newPrimitiveValueBuilder()
                                                                                                           .buildString("KG")));
        entityBooking.getProperties().add(mODataClient.getObjectFactory().newPrimitiveProperty("InvoiceAvailable",
                                                                                               mODataClient.getObjectFactory()
                                                                                                           .newPrimitiveValueBuilder()
                                                                                                           .buildBoolean(true)));
        entityBooking.getProperties().add(mODataClient.getObjectFactory().newPrimitiveProperty("FlightClass",
                                                                                               mODataClient.getObjectFactory()
                                                                                                           .newPrimitiveValueBuilder()
                                                                                                           .buildString(this.transformFlightClass(flightClass))));
        entityBooking.getProperties().add(mODataClient.getObjectFactory().newPrimitiveProperty("OrderDate",
                                                                                               mODataClient.getObjectFactory()
                                                                                                           .newPrimitiveValueBuilder()
                                                                                                           .buildString(this.getDateOfToday())));
        entityBooking.getProperties().add(mODataClient.getObjectFactory().newPrimitiveProperty("IsCancelled",
                                                                                               mODataClient.getObjectFactory()
                                                                                                           .newPrimitiveValueBuilder()
                                                                                                           .buildBoolean(false)));
        entityBooking.getProperties().add(mODataClient.getObjectFactory().newPrimitiveProperty("IsReserved",
                                                                                               mODataClient.getObjectFactory()
                                                                                                           .newPrimitiveValueBuilder()
                                                                                                           .buildBoolean(true)));

        return entityBooking;
    }

    private int updateEntity(URI absoluteUri, ClientEntity ce) {
        final ODataEntityUpdateRequest<ClientEntity> request = mODataClient.getCUDRequestFactory().getEntityUpdateRequest(absoluteUri, UpdateType.PATCH, ce);
        // odata4 sample/server limitation not handling metadata=full
        request.setAccept("application/json;odata.metadata=minimal");
        final ODataEntityUpdateResponse<ClientEntity> response = request.execute();

        return response.getStatusCode();
    }

    private String getDateOfToday() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        final LocalDate today = LocalDate.now();
        return today.format(formatter);
    }

    private String transformSex(String sex) {
        return sex.equals("weiblich") ? "Female" : "Male";
    }

    private boolean transformIsSmoker(String isSmoker) {
        return isSmoker.equals("ja");
    }

    private String transformFlightClass(String flightClass) {
        switch (flightClass) {
            case "Business Class (1,5-facher Aufschlag)":
                return "Business";
            case "First Class (3,5-facher Aufschlag)":
                return "First Class";
            default:
                return "Economy";
        }
    }

}