////////////////////////////////////////////////////////////////////////////////
//
// Created by BBruhns on 09.04.2018.
//
// Copyright (c) 2006 - 2018 FORCAM GmbH. All rights reserved.
////////////////////////////////////////////////////////////////////////////////

package mynamespace.web.controller;

import mynamespace.web.service.DataTransformator;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.request.cud.ODataEntityCreateRequest;
import org.apache.olingo.client.api.communication.response.ODataEntityCreateResponse;
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
@WebServlet("/bookingSuccessful")
public class bookingSuccessfulServlet extends HttpServlet {

    ODataClient mODataClient = ODataClientFactory.getClient();

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
        final ClientEntity departureBookingEntity = this.buildBookingEntity(sex, flightClass, luggWeight, isSmoker, depCarrId, depConnId, depflightDate);
        responseEntityBooking = createEntity(this.createCreateBookingURI(), departureBookingEntity);
        responseBookingId = DataTransformator.getBookingIdFromClientEntity(responseEntityBooking);
        req.setAttribute("departureFlightBookingId", responseBookingId);

        // send new return flight booking to the server and retrieve bookingId
        final ClientEntity returnBookingEntity = this.buildBookingEntity(sex, flightClass, luggWeight, isSmoker, retCarrId, retConnId, retflightDate);
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

    private ClientEntity createEntity(URI absoluteUri, ClientEntity ce) {
        final ODataEntityCreateRequest<ClientEntity> request = mODataClient.getCUDRequestFactory().getEntityCreateRequest(absoluteUri, ce);
        // odata4 sample/server limitation not handling metadata=full
        request.setAccept("application/json;odata.metadata=minimal");
        final ODataEntityCreateResponse<ClientEntity> response = request.execute();

        return response.getBody();
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
