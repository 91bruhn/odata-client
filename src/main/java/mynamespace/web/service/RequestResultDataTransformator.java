////////////////////////////////////////////////////////////////////////////////
//
// Created by bruhn on 01.04.2018.
//
// Copyright (c) 2006 - 2018 FORCAM GmbH. All rights reserved.
////////////////////////////////////////////////////////////////////////////////

package mynamespace.web.service;

import mynamespace.web.model.Booking;
import mynamespace.web.model.Carrier;
import mynamespace.web.model.Connection;
import mynamespace.web.model.Flight;
import mynamespace.web.model.FlightSearchResult;
import mynamespace.web.model.Plane;
import org.apache.olingo.client.api.domain.ClientEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static mynamespace.web.util.EntityNames.AIRPORT_FROM;
import static mynamespace.web.util.EntityNames.AIRPORT_TO;
import static mynamespace.web.util.EntityNames.ARRIVAL_TIME;
import static mynamespace.web.util.EntityNames.BOOKING_ID;
import static mynamespace.web.util.EntityNames.CARRIER_ID;
import static mynamespace.web.util.EntityNames.CARRIER_NAME;
import static mynamespace.web.util.EntityNames.CITY_FROM;
import static mynamespace.web.util.EntityNames.CITY_TO;
import static mynamespace.web.util.EntityNames.CONNECTION_ID;
import static mynamespace.web.util.EntityNames.CONSUMPTION;
import static mynamespace.web.util.EntityNames.CONSUM_UNIT;
import static mynamespace.web.util.EntityNames.COUNTRY_FROM;
import static mynamespace.web.util.EntityNames.COUNTRY_TO;
import static mynamespace.web.util.EntityNames.CURRENCY;
import static mynamespace.web.util.EntityNames.CUSTOMER_ID;
import static mynamespace.web.util.EntityNames.DEPARTURE_TIME;
import static mynamespace.web.util.EntityNames.DISTANCE;
import static mynamespace.web.util.EntityNames.DISTANCE_UNIT;
import static mynamespace.web.util.EntityNames.FLIGHT_CLASS;
import static mynamespace.web.util.EntityNames.FLIGHT_DATE;
import static mynamespace.web.util.EntityNames.FLIGHT_TIME;
import static mynamespace.web.util.EntityNames.FLIGHT_TYPE;
import static mynamespace.web.util.EntityNames.HAS_INVOICE;
import static mynamespace.web.util.EntityNames.IS_CANCELLED;
import static mynamespace.web.util.EntityNames.IS_RESERVED;
import static mynamespace.web.util.EntityNames.IS_SMOKER;
import static mynamespace.web.util.EntityNames.LENGTH;
import static mynamespace.web.util.EntityNames.LENGTH_UNIT;
import static mynamespace.web.util.EntityNames.LUGGAGE_WEIGHT;
import static mynamespace.web.util.EntityNames.ORDER_DATE;
import static mynamespace.web.util.EntityNames.PERIOD;
import static mynamespace.web.util.EntityNames.PLANE_TYPE;
import static mynamespace.web.util.EntityNames.PRICE;
import static mynamespace.web.util.EntityNames.PRODUCER;
import static mynamespace.web.util.EntityNames.SEATS_MAX;
import static mynamespace.web.util.EntityNames.SEATS_MAX_B;
import static mynamespace.web.util.EntityNames.SEATS_MAX_E;
import static mynamespace.web.util.EntityNames.SEATS_MAX_F;
import static mynamespace.web.util.EntityNames.SEATS_OCC_B;
import static mynamespace.web.util.EntityNames.SEATS_OCC_E;
import static mynamespace.web.util.EntityNames.SEATS_OCC_F;
import static mynamespace.web.util.EntityNames.SEX;
import static mynamespace.web.util.EntityNames.SPAN;
import static mynamespace.web.util.EntityNames.SPAN_UNIT;
import static mynamespace.web.util.EntityNames.SPEED;
import static mynamespace.web.util.EntityNames.SPEED_UNIT;
import static mynamespace.web.util.EntityNames.TANK_CAPACITY;
import static mynamespace.web.util.EntityNames.TANK_CAP_UNIT;
import static mynamespace.web.util.EntityNames.URL;
import static mynamespace.web.util.EntityNames.WEIGHT;
import static mynamespace.web.util.EntityNames.WEIGHT_UNIT;

/**
 *
 */
public class RequestResultDataTransformator {

    public static FlightSearchResult transformFlightSearchResultRequestToFlightSearchResult(ClientEntity entityFlightSearchResult, String dateFrom) {
        final FlightSearchResult flightSearchResult = new FlightSearchResult();

        flightSearchResult.setConnId((String) entityFlightSearchResult.getProperty(CONNECTION_ID).getValue().asPrimitive().toValue());
        flightSearchResult.setCountryFrom((String) entityFlightSearchResult.getProperty(COUNTRY_FROM).getValue().asPrimitive().toValue());
        flightSearchResult.setCityFrom((String) entityFlightSearchResult.getProperty(CITY_FROM).getValue().asPrimitive().toValue());
        flightSearchResult.setAirpFrom((String) entityFlightSearchResult.getProperty(AIRPORT_FROM).getValue().asPrimitive().toValue());
        flightSearchResult.setDepTime((String) entityFlightSearchResult.getProperty(DEPARTURE_TIME).getValue().asPrimitive().toValue());
        flightSearchResult.setCountryTo((String) entityFlightSearchResult.getProperty(COUNTRY_TO).getValue().asPrimitive().toValue());
        flightSearchResult.setCityTo((String) entityFlightSearchResult.getProperty(CITY_TO).getValue().asPrimitive().toValue());
        flightSearchResult.setAirpTo((String) entityFlightSearchResult.getProperty(AIRPORT_TO).getValue().asPrimitive().toValue());
        flightSearchResult.setArrTime((String) entityFlightSearchResult.getProperty(ARRIVAL_TIME).getValue().asPrimitive().toValue());
        flightSearchResult.setFlTime((Integer) entityFlightSearchResult.getProperty(FLIGHT_TIME).getValue().asPrimitive().toValue());
        flightSearchResult.setDistance((Double) entityFlightSearchResult.getProperty(DISTANCE).getValue().asPrimitive().toValue());
        flightSearchResult.setDistId((String) entityFlightSearchResult.getProperty(DISTANCE_UNIT).getValue().asPrimitive().toValue());

        final Carrier carrier = new Carrier();
        carrier.setCarrName((String) entityFlightSearchResult.getProperty("Carrier").getComplexValue().get("CarrierName").getValue().asPrimitive().toValue());
        carrier.setUrl((String) entityFlightSearchResult.getProperty("Carrier").getComplexValue().get("URL").getValue().asPrimitive().toValue());

        final List<Flight> flights = new ArrayList<>();
        entityFlightSearchResult.getProperty("Flights").getCollectionValue().forEach(flightRequestEntity -> {
            final Flight flight = new Flight();
            final String flightDate = (String) flightRequestEntity.asComplex().get("FlightDate").getValue().asPrimitive().toValue();

            //            if (considerFlight(flightDate, dateFrom)) {//TODO richtig machen
            flight.setFlightDate(flightDate);
            //            flight.setAirfair((Double) flightRequestEntity.asComplex().get("Airfare").getValue().asPrimitive().toValue());
            //            flight.setPlane((String) flightRequestEntity.asComplex().get("PlaneType").getValue().asPrimitive().toValue());
            flight.setSeatsMaxE((Integer) flightRequestEntity.asComplex().get("MaxSeatsEconomyClass").getValue().asPrimitive().toValue());
            flight.setSeatsMaxB((Integer) flightRequestEntity.asComplex().get("MaxSeatsBusinessClass").getValue().asPrimitive().toValue());
            flight.setSeatsMaxF((Integer) flightRequestEntity.asComplex().get("MaxSeatsFirstClass").getValue().asPrimitive().toValue());
            flight.setSeatsOccupiedE((Integer) flightRequestEntity.asComplex().get("OccupiedSeatsInEconomyClass").getValue().asPrimitive().toValue());
            flight.setSeatsOccupiedB((Integer) flightRequestEntity.asComplex().get("OccupiedSeatsBusinessClass").getValue().asPrimitive().toValue());
            flight.setSeatOccupiedF((Integer) flightRequestEntity.asComplex().get("OccupiedSeatsFirstClass").getValue().asPrimitive().toValue());

            flights.add(flight);
            //            }
        });

        flightSearchResult.setCarrier(carrier);
        flightSearchResult.setFlights(flights);

        return flightSearchResult;
    }

    // only considering flights that are starting at given date or up to a week later todo beschreibung
    private static boolean considerFlight(String flightDate, String requestedDepartureDate) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        final LocalDate localDate = LocalDate.parse(flightDate, formatter);
        final LocalDate exactRequestedDate = LocalDate.parse(requestedDepartureDate, formatter);
        final LocalDate oneWeekAfterRequestedDate = LocalDate.parse(requestedDepartureDate, formatter).plusDays(8);

        return localDate.isEqual(exactRequestedDate) || localDate.isBefore(oneWeekAfterRequestedDate);

    }

    public static Connection transformConnectionEntityToConnection(ClientEntity entityConnection) {
        final Connection connection = new Connection();

        connection.setConnId((String) entityConnection.getProperty(CONNECTION_ID).getValue().asPrimitive().toValue());
        connection.setScarrId((String) entityConnection.getProperty(CARRIER_ID).getValue().asPrimitive().toValue());
        connection.setFlType((String) entityConnection.getProperty(FLIGHT_TYPE).getValue().asPrimitive().toValue());
        connection.setCountryFrom((String) entityConnection.getProperty(COUNTRY_FROM).getValue().asPrimitive().toValue());
        connection.setCityFrom((String) entityConnection.getProperty(CITY_FROM).getValue().asPrimitive().toValue());
        connection.setAirpFrom((String) entityConnection.getProperty(AIRPORT_FROM).getValue().asPrimitive().toValue());
        connection.setDepTime((String) entityConnection.getProperty(DEPARTURE_TIME).getValue().asPrimitive().toValue());
        connection.setCountryTo((String) entityConnection.getProperty(COUNTRY_TO).getValue().asPrimitive().toValue());
        connection.setCityTo((String) entityConnection.getProperty(CITY_TO).getValue().asPrimitive().toValue());
        connection.setAirpTo((String) entityConnection.getProperty(AIRPORT_TO).getValue().asPrimitive().toValue());
        connection.setArrTime((String) entityConnection.getProperty(ARRIVAL_TIME).getValue().asPrimitive().toValue());
        connection.setFlTime((Integer) entityConnection.getProperty(FLIGHT_TIME).getValue().asPrimitive().toValue());
        connection.setDistance((Double) entityConnection.getProperty(DISTANCE).getValue().asPrimitive().toValue());
        connection.setDistId((String) entityConnection.getProperty(DISTANCE_UNIT).getValue().asPrimitive().toValue());
        connection.setPeriod((Integer) entityConnection.getProperty(PERIOD).getValue().asPrimitive().toValue());

        return connection;
    }

    public static Flight transformFlightEntityToFlight(ClientEntity entityFlight) {
        final Flight flight = new Flight();

        flight.setFlightDate((String) entityFlight.getProperty(FLIGHT_DATE).getValue().asPrimitive().toValue());
        flight.setCarrierId((String) entityFlight.getProperty(CARRIER_ID).getValue().asPrimitive().toValue());
        flight.setConnectionId((String) entityFlight.getProperty(CONNECTION_ID).getValue().asPrimitive().toValue());
        flight.setPlane((String) entityFlight.getProperty(PLANE_TYPE).getValue().asPrimitive().toValue());
        flight.setAirfair((Double) entityFlight.getProperty(PRICE).getValue().asPrimitive().toValue());
        flight.setCurrency((String) entityFlight.getProperty(CURRENCY).getValue().asPrimitive().toValue());
        flight.setSeatsMaxE((Integer) entityFlight.getProperty(SEATS_MAX_E).getValue().asPrimitive().toValue());
        flight.setSeatsOccupiedE((Integer) entityFlight.getProperty(SEATS_OCC_E).getValue().asPrimitive().toValue());
        flight.setSeatsMaxB((Integer) entityFlight.getProperty(SEATS_MAX_B).getValue().asPrimitive().toValue());
        flight.setSeatsOccupiedB((Integer) entityFlight.getProperty(SEATS_OCC_B).getValue().asPrimitive().toValue());
        flight.setSeatsMaxF((Integer) entityFlight.getProperty(SEATS_MAX_F).getValue().asPrimitive().toValue());
        flight.setSeatOccupiedF((Integer) entityFlight.getProperty(SEATS_OCC_F).getValue().asPrimitive().toValue());

        return flight;
    }

    public static Booking transformBookingEntityToBooking(ClientEntity entityBooking) {
        final Booking booking = new Booking();

        booking.setBookId((String) entityBooking.getProperty(BOOKING_ID).getValue().asPrimitive().toValue());
        booking.setCarrierId((String) entityBooking.getProperty(CARRIER_ID).getValue().asPrimitive().toValue());
        booking.setConnectionId((String) entityBooking.getProperty(CONNECTION_ID).getValue().asPrimitive().toValue());
        booking.setFlightId((String) entityBooking.getProperty(FLIGHT_DATE).getValue().asPrimitive().toValue());
        booking.setCustomId((String) entityBooking.getProperty(CUSTOMER_ID).getValue().asPrimitive().toValue());
        booking.setCustType((String) entityBooking.getProperty(SEX).getValue().asPrimitive().toValue());
        booking.setSmoker((Boolean) entityBooking.getProperty(IS_SMOKER).getValue().asPrimitive().toValue());
        booking.setLuggWeight((Integer) entityBooking.getProperty(LUGGAGE_WEIGHT).getValue().asPrimitive().toValue());
        booking.setWUnit((String) entityBooking.getProperty(WEIGHT_UNIT).getValue().asPrimitive().toValue());
        booking.setInvoice((Boolean) entityBooking.getProperty(HAS_INVOICE).getValue().asPrimitive().toValue());
        booking.setFlightClass((String) entityBooking.getProperty(FLIGHT_CLASS).getValue().asPrimitive().toValue());
        booking.setOrderDate((String) entityBooking.getProperty(ORDER_DATE).getValue().asPrimitive().toValue());
        booking.setCancelled((Boolean) entityBooking.getProperty(IS_CANCELLED).getValue().asPrimitive().toValue());
        booking.setReserved((Boolean) entityBooking.getProperty(IS_RESERVED).getValue().asPrimitive().toValue());

        return booking;
    }

    public static Carrier transformCarrierEntityToCarrier(ClientEntity entityCarrier) {
        final Carrier carrier = new Carrier();

        carrier.setCarrId((String) entityCarrier.getProperty(CARRIER_ID).getValue().asPrimitive().toValue());
        carrier.setCarrName((String) entityCarrier.getProperty(CARRIER_NAME).getValue().asPrimitive().toValue());
        carrier.setCurrCode((String) entityCarrier.getProperty(CURRENCY).getValue().asPrimitive().toValue());
        carrier.setUrl((String) entityCarrier.getProperty(URL).getValue().asPrimitive().toValue());

        return carrier;
    }

    public static Plane transformPlaneEntityToPlane(ClientEntity entityPlane) {
        final Plane plane = new Plane();

        plane.setPlaneType((String) entityPlane.getProperty(PLANE_TYPE).getValue().asPrimitive().toValue());
        plane.setSeatsMaxE((Integer) entityPlane.getProperty(SEATS_MAX).getValue().asPrimitive().toValue());
        plane.setConsumption((Integer) entityPlane.getProperty(CONSUMPTION).getValue().asPrimitive().toValue());
        plane.setConUnit((String) entityPlane.getProperty(CONSUM_UNIT).getValue().asPrimitive().toValue());
        plane.setTankCap((Integer) entityPlane.getProperty(TANK_CAPACITY).getValue().asPrimitive().toValue());
        plane.setCapUnit((String) entityPlane.getProperty(TANK_CAP_UNIT).getValue().asPrimitive().toValue());
        plane.setWeight((Integer) entityPlane.getProperty(WEIGHT).getValue().asPrimitive().toValue());
        plane.setSpan((Integer) entityPlane.getProperty(SPAN).getValue().asPrimitive().toValue());
        plane.setSpanUnit((String) entityPlane.getProperty(SPAN_UNIT).getValue().asPrimitive().toValue());
        plane.setLength((Integer) entityPlane.getProperty(LENGTH).getValue().asPrimitive().toValue());
        plane.setLengUnit((String) entityPlane.getProperty(LENGTH_UNIT).getValue().asPrimitive().toValue());
        plane.setOpSpeed((Integer) entityPlane.getProperty(SPEED).getValue().asPrimitive().toValue());
        plane.setSpeedUnit((String) entityPlane.getProperty(SPEED_UNIT).getValue().asPrimitive().toValue());
        plane.setProducer((String) entityPlane.getProperty(PRODUCER).getValue().asPrimitive().toValue());

        return plane;
    }
}
