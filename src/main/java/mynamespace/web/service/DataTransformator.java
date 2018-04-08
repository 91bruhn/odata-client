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
import mynamespace.web.model.ConnectionSearchResult;
import mynamespace.web.model.Flight;
import mynamespace.web.model.FlightSearchResult;
import mynamespace.web.model.Plane;
import org.apache.commons.lang3.StringUtils;
import org.apache.olingo.client.api.domain.ClientComplexValue;
import org.apache.olingo.client.api.domain.ClientEntity;

import java.text.DecimalFormat;
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
public class DataTransformator {

    public static ConnectionSearchResult transformConnectionSearchResultRequestToConnectionSearchResult(ClientEntity entityFlightSearchResult, String dateFrom,
                                                                                                    String dateTo) {
        final ConnectionSearchResult connectionSearchResult = new ConnectionSearchResult();
        connectionSearchResult.setConnId((String) entityFlightSearchResult.getProperty(CONNECTION_ID).getValue().asPrimitive().toValue());
        connectionSearchResult.setCountryFrom((String) entityFlightSearchResult.getProperty(COUNTRY_FROM).getValue().asPrimitive().toValue());
        connectionSearchResult.setCityFrom((String) entityFlightSearchResult.getProperty(CITY_FROM).getValue().asPrimitive().toValue());
        connectionSearchResult.setAirpFrom((String) entityFlightSearchResult.getProperty(AIRPORT_FROM).getValue().asPrimitive().toValue());
        connectionSearchResult.setDepTime((String) entityFlightSearchResult.getProperty(DEPARTURE_TIME).getValue().asPrimitive().toValue());
        connectionSearchResult.setCountryTo((String) entityFlightSearchResult.getProperty(COUNTRY_TO).getValue().asPrimitive().toValue());
        connectionSearchResult.setCityTo((String) entityFlightSearchResult.getProperty(CITY_TO).getValue().asPrimitive().toValue());
        connectionSearchResult.setAirpTo((String) entityFlightSearchResult.getProperty(AIRPORT_TO).getValue().asPrimitive().toValue());
        connectionSearchResult.setArrTime((String) entityFlightSearchResult.getProperty(ARRIVAL_TIME).getValue().asPrimitive().toValue());
        connectionSearchResult.setFlTime((Integer) entityFlightSearchResult.getProperty(FLIGHT_TIME).getValue().asPrimitive().toValue());
        connectionSearchResult.setDistance((Double) entityFlightSearchResult.getProperty(DISTANCE).getValue().asPrimitive().toValue());
        connectionSearchResult.setDistId((String) entityFlightSearchResult.getProperty(DISTANCE_UNIT).getValue().asPrimitive().toValue());

        final Carrier carrier = new Carrier();
        final ClientComplexValue complexValueCarrier = entityFlightSearchResult.getProperty("Carrier").getComplexValue();
        carrier.setCarrId((String) complexValueCarrier.get("CarrierCode").getValue().asPrimitive().toValue());
        carrier.setCarrName((String) complexValueCarrier.get("CarrierName").getValue().asPrimitive().toValue());
        carrier.setUrl((String) complexValueCarrier.get("URL").getValue().asPrimitive().toValue());

        final List<Flight> flights = new ArrayList<>();
        entityFlightSearchResult.getProperty("Flights").getCollectionValue().forEach(flightRequestEntity -> {
            final Flight flight = new Flight();
            final ClientComplexValue complexValueFlight = flightRequestEntity.asComplex();
            final String flightDate = (String) complexValueFlight.get("FlightDate").getValue().asPrimitive().toValue();

            if (considerFlight(flightDate, dateFrom, dateTo)) {//TODO richtig machen
                flight.setFlightDate(flightDate);
                flight.setAirfair((Double) complexValueFlight.get("Airfare").getValue().asPrimitive().toValue());
                flight.setCurrency((String) complexValueFlight.get("LocalCurrencyOfAirline").getValue().asPrimitive().toValue());
                //            flight.setPlane((String) flightRequestEntity.asComplex().get("PlaneType").getValue().asPrimitive().toValue());
                flight.setSeatsMaxE((Integer) complexValueFlight.get("MaxSeatsEconomyClass").getValue().asPrimitive().toValue());
                flight.setSeatsMaxB((Integer) complexValueFlight.get("MaxSeatsBusinessClass").getValue().asPrimitive().toValue());
                flight.setSeatsMaxF((Integer) complexValueFlight.get("MaxSeatsFirstClass").getValue().asPrimitive().toValue());
                flight.setSeatsOccupiedE((Integer) complexValueFlight.get("OccupiedSeatsInEconomyClass").getValue().asPrimitive().toValue());
                flight.setSeatsOccupiedB((Integer) complexValueFlight.get("OccupiedSeatsBusinessClass").getValue().asPrimitive().toValue());
                flight.setSeatsOccupiedF((Integer) complexValueFlight.get("OccupiedSeatsFirstClass").getValue().asPrimitive().toValue());

                flights.add(flight);
            }
        });

        connectionSearchResult.setCarrier(carrier);
        connectionSearchResult.setFlights(flights);

        return connectionSearchResult;
    }

    public static ConnectionSearchResult transformFlightSearchResultRequestToFlightSearchResult(ClientEntity entityFlightSearchResult) {
        final FlightSearchResult flightSearchResult = new FlightSearchResult();

        final Flight flight = new Flight();
        flight.setCurrency();


        final Carrier carrier = new Carrier();
        final Connection connection = new Connection();



//        connectionSearchResult.setConnId((String) entityFlightSearchResult.getProperty(CONNECTION_ID).getValue().asPrimitive().toValue());
//        connectionSearchResult.setCountryFrom((String) entityFlightSearchResult.getProperty(COUNTRY_FROM).getValue().asPrimitive().toValue());
//        connectionSearchResult.setCityFrom((String) entityFlightSearchResult.getProperty(CITY_FROM).getValue().asPrimitive().toValue());
//        connectionSearchResult.setAirpFrom((String) entityFlightSearchResult.getProperty(AIRPORT_FROM).getValue().asPrimitive().toValue());
//        connectionSearchResult.setDepTime((String) entityFlightSearchResult.getProperty(DEPARTURE_TIME).getValue().asPrimitive().toValue());
//        connectionSearchResult.setCountryTo((String) entityFlightSearchResult.getProperty(COUNTRY_TO).getValue().asPrimitive().toValue());
//        connectionSearchResult.setCityTo((String) entityFlightSearchResult.getProperty(CITY_TO).getValue().asPrimitive().toValue());
//        connectionSearchResult.setAirpTo((String) entityFlightSearchResult.getProperty(AIRPORT_TO).getValue().asPrimitive().toValue());
//        connectionSearchResult.setArrTime((String) entityFlightSearchResult.getProperty(ARRIVAL_TIME).getValue().asPrimitive().toValue());
//        connectionSearchResult.setFlTime((Integer) entityFlightSearchResult.getProperty(FLIGHT_TIME).getValue().asPrimitive().toValue());
//        connectionSearchResult.setDistance((Double) entityFlightSearchResult.getProperty(DISTANCE).getValue().asPrimitive().toValue());
//        connectionSearchResult.setDistId((String) entityFlightSearchResult.getProperty(DISTANCE_UNIT).getValue().asPrimitive().toValue());
//
//        final Carrier carrier = new Carrier();
//        carrier.setCarrName((String) entityFlightSearchResult.getProperty("Carrier").getComplexValue().get("CarrierName").getValue().asPrimitive().toValue());
//        carrier.setUrl((String) entityFlightSearchResult.getProperty("Carrier").getComplexValue().get("URL").getValue().asPrimitive().toValue());

//        final List<Flight> flights = new ArrayList<>();
//        entityFlightSearchResult.getProperty("Flights").getCollectionValue().forEach(flightRequestEntity -> {
//            final Flight flight = new Flight();
//            final String flightDate = (String) flightRequestEntity.asComplex().get("FlightDate").getValue().asPrimitive().toValue();
//
//            if (considerFlight(flightDate, dateFrom, dateTo)) {//TODO richtig machen
//                flight.setFlightDate(flightDate);
//                flight.setAirfair((Double) flightRequestEntity.asComplex().get("Airfare").getValue().asPrimitive().toValue());
//                flight.setCurrency((String) flightRequestEntity.asComplex().get("LocalCurrencyOfAirline").getValue().asPrimitive().toValue());
//                //            flight.setPlane((String) flightRequestEntity.asComplex().get("PlaneType").getValue().asPrimitive().toValue());
//                flight.setSeatsMaxE((Integer) flightRequestEntity.asComplex().get("MaxSeatsEconomyClass").getValue().asPrimitive().toValue());
//                flight.setSeatsMaxB((Integer) flightRequestEntity.asComplex().get("MaxSeatsBusinessClass").getValue().asPrimitive().toValue());
//                flight.setSeatsMaxF((Integer) flightRequestEntity.asComplex().get("MaxSeatsFirstClass").getValue().asPrimitive().toValue());
//                flight.setSeatsOccupiedE((Integer) flightRequestEntity.asComplex().get("OccupiedSeatsInEconomyClass").getValue().asPrimitive().toValue());
//                flight.setSeatsOccupiedB((Integer) flightRequestEntity.asComplex().get("OccupiedSeatsBusinessClass").getValue().asPrimitive().toValue());
//                flight.setSeatsOccupiedF((Integer) flightRequestEntity.asComplex().get("OccupiedSeatsFirstClass").getValue().asPrimitive().toValue());
//
//                flights.add(flight);
//            }
//        });
//
//        connectionSearchResult.setCarrier(carrier);
//        connectionSearchResult.setFlights(flights);

        return connectionSearchResult;
    }

    // only considering flights that are starting at given date or up to a week later todo beschreibung
    private static boolean considerFlight(String flightDateOfRequest, String requestedDateFrom, String requestedDateTo) {
        final DateTimeFormatter formatterRequestedDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        final DateTimeFormatter formatterRequestDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        final LocalDate flightDate = LocalDate.parse(flightDateOfRequest, formatterRequestDate);
        final LocalDate dateFrom = LocalDate.parse(requestedDateFrom, formatterRequestedDate);

        if (requestedDateTo.isEmpty()) {
            return flightDate.isAfter(dateFrom) || flightDate.isEqual(dateFrom);
        }

        final LocalDate dateTo = LocalDate.parse(requestedDateTo, formatterRequestedDate);

        return (flightDate.isAfter(dateFrom) || flightDate.isEqual(dateFrom)) && (flightDate.isBefore(dateTo) || flightDate.isEqual(dateTo));
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
        flight.setSeatsOccupiedF((Integer) entityFlight.getProperty(SEATS_OCC_F).getValue().asPrimitive().toValue());

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

    public String transformRequestCityName(String cityName) {
        switch (cityName) {
            case "SANFRANCISCO":
                return "San Francisco";
            case "NEWYORK":
                return "New York";
            case "FRANKFURT":
                return "Frankfurt";
            case "ROME":
                return "Rome";
            case "TOKYO":
                return "Tokyo";
            case "OSAKA":
                return "Osaka";
            case "BERLIN":
                return "Berlin";
            case "SINGAPORE":
                return "Singapore";
            case "KUALALUMPUR":
                return "Kualalumpur";
            case "BANGKOK":
                return "Bangkok";
            case "JAKARTA":
                return "Jakarta";
            case "HONGKONG":
                return "Hongkong";
            default:
                return StringUtils.EMPTY;
        }
    }

    //if price is not in euros then adjust price
    public double calculateFlightPriceInEuros(double flightPrice, String currency) {
        switch (currency) {
            case "EUR":
                break;
            case "USD":
                flightPrice = flightPrice * 0.846302926;
                break;
            case "CAD":
                flightPrice = flightPrice * 0.660759472;
                break;
            case "GBP":
                flightPrice = flightPrice * 1.13498532;
                break;
            case "JPY":
                flightPrice = flightPrice * 0.00751516998;
                break;
            case "AUD":
                flightPrice = flightPrice * 0.645898393;
                break;
            case "ZAR":
                flightPrice = flightPrice * 0.0628887704;
                break;
            case "SGD":
                flightPrice = flightPrice * 0.628227588;
                break;
            case "CHF":
                flightPrice = flightPrice * 0.859039785;
                break;
        }
        final DecimalFormat format = new DecimalFormat("#.00");

        return Double.parseDouble(format.format(flightPrice).replace(",", "."));
    }

    //combines the amount of available seats from economic, business and first class
    public int getCombinedAmountOfAvailableSeats(int seatsMaxE, int seatsMaxB, int seatsMaxF, int seatsOccuppiedE, int seatsOccuppiedB, int seatsOccuppiedF) {
        return (seatsMaxE - seatsOccuppiedE) + (seatsMaxB - seatsOccuppiedB) + (seatsMaxF - seatsOccuppiedF);
    }
}
