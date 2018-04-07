////////////////////////////////////////////////////////////////////////////////
//
// Created by bruhn on 01.04.2018.
//
// Copyright (c) 2006 - 2018 FORCAM GmbH. All rights reserved.
////////////////////////////////////////////////////////////////////////////////

package mynamespace.web.util;

/**
 *
 */
public class EntityNames {

    // ------------------------------------------------------------------------
    // constants
    // ------------------------------------------------------------------------
    // ========================================================================
    //                          ENTITY TYPES NAMES
    // ========================================================================

//    public static final String ET_SAPLANE_NAME = "Airplane";
    //    public static final FullQualifiedName ET_SAPLANE_FQN = new FullQualifiedName(NAMESPACE, ET_SAPLANE_NAME);
    //
    //    public static final String ET_SCARR_NAME = "Carrier";
    //    public static final FullQualifiedName ET_SCARR_FQN = new FullQualifiedName(NAMESPACE, ET_SCARR_NAME);
    //
    //    public static final String ET_SPFLI_NAME = "Connection";
    //    public static final FullQualifiedName ET_SPFLI_FQN = new FullQualifiedName(NAMESPACE, ET_SPFLI_NAME);
    //
    //    public static final String ET_SFLIGHT_NAME = "Flight";
    //    public static final FullQualifiedName ET_SFLIGHT_FQN = new FullQualifiedName(NAMESPACE, ET_SFLIGHT_NAME);
    //
    //    public static final String ET_SBOOK_NAME = "Booking";
    //    public static final FullQualifiedName ET_SBOOK_FQN = new FullQualifiedName(NAMESPACE, ET_SBOOK_NAME);

    // ========================================================================
    //                          ENTITY SET NAMES
    // ========================================================================

    public static final String ES_SCARR_NAME = "Carriers";
    public static final String ES_SAPLANE_NAME = "Airplanes";
    public static final String ES_SPFLI_NAME = "Connections";
    public static final String ES_SFLIGHT_NAME = "Flights";
    public static final String ES_SBOOK_NAME = "Bookings";

    // ========================================================================
    //                        CARRIER ENTITY ATTRIBUTES
    // ========================================================================

    public static final String CARRIER_ID = "CarrierCode";
    public static final String CONNECTION_ID = "FlightConnectionNumber";
    public static final String FLIGHT_DATE = "FlightDate";//TODO falsch
    public static final String PLANE_TYPE = "PlaneType";
    public static final String BOOKING_ID = "BookingId";

    // ========================================================================
    //                        FLIGHT ENTITY ATTRIBUTES
    // ========================================================================

    public static final String CARRIER_NAME = "CarrierName";
    public static final String URL = "URL";
    public static final String PRICE = "Airfare";
    public static final String CURRENCY = "LocalCurrencyOfAirline";
    public static final String SEATS_MAX_E = "MaxSeatsEconomyClass";
    public static final String SEATS_OCC_E = "OccupiedSeatsInEconomyClass";
    public static final String SEATS_MAX_B = "MaxSeatsBusinessClass";
    public static final String SEATS_OCC_B = "OccupiedSeatsBusinessClass";
    public static final String SEATS_MAX_F = "MaxSeatsFirstClass";
    public static final String SEATS_OCC_F = "OccupiedSeatsFirstClass";

    // ========================================================================
    //                        CONNECTION ENTITY ATTRIBUTES
    // ========================================================================

    public static final String COUNTRY_FROM = "DepartureCountryKey";
    public static final String CITY_FROM = "DepartureCity";
    public static final String AIRPORT_FROM = "DepartureAirport";
    public static final String DEPARTURE_TIME = "DepartureTime";
    public static final String COUNTRY_TO = "ArrivalCountryKey";
    public static final String CITY_TO = "ArrivalCity";
    public static final String AIRPORT_TO = "ArrivalAirport";
    public static final String ARRIVAL_TIME = "ArrivalTime";
    public static final String FLIGHT_TIME = "FlightTime";
    public static final String DISTANCE = "Distance";
    public static final String DISTANCE_UNIT = "MassUnitOfDistance";
    public static final String FLIGHT_TYPE = "FlightType";
    public static final String PERIOD = "ArrivalInDaysLater";

    // ========================================================================
    //                        BOOKING ENTITY ATTRIBUTES
    // ========================================================================

    public static final String CUSTOMER_ID = "CustomerId";
    public static final String SEX = "Sex";
    public static final String IS_SMOKER = "IsSmoker";
    public static final String LUGGAGE_WEIGHT = "LuggageWeight";
    public static final String WEIGHT_UNIT = "WeightUnit";
    public static final String HAS_INVOICE = "InvoiceAvailable";
    public static final String FLIGHT_CLASS = "FlightClass";
    public static final String ORDER_DATE = "OrderDate";
    public static final String IS_CANCELLED = "IsCancelled";
    public static final String IS_RESERVED = "IsReserved";

    // ========================================================================
    //                        PLANE ENTITY ATTRIBUTES
    // ========================================================================

    public static final String SEATS_MAX = "MaxSeats";
    public static final String CONSUMPTION = "Consumption";
    public static final String CONSUM_UNIT = "MassUnitOfConsumption";
    public static final String TANK_CAPACITY = "TankCapacity";
    public static final String TANK_CAP_UNIT = "UnitOfTankCapacity";
    public static final String WEIGHT = "Weight";
    public static final String SPAN = "WingSpan";
    public static final String SPAN_UNIT = "UnitOfSpan";
    public static final String LENGTH = "Length";
    public static final String LENGTH_UNIT = "UnitOfLength";
    public static final String SPEED = "Speed";
    public static final String SPEED_UNIT = "UnitOfSpeed";
    public static final String PRODUCER = " Producer";

}
