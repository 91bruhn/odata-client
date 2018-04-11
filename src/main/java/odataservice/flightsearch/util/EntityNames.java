package odataservice.flightsearch.util;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

/**
 *
 */
public class EntityNames {

    // ------------------------------------------------------------------------
    // constants
    // ------------------------------------------------------------------------

    // ========================================================================
    //                           ODATA-SERVER-FLIGHT-DATA-MANAGEMENT
    // ========================================================================

    public static final String SERVICE_URI = "http://localhost:8080/flightDataManagement.svc/";

    public static final String HEADER_ACCEPT_JSON = "application/json;odata.metadata=minimal";

    // ========================================================================
    //                           SERVICE NAMESPACE
    // ========================================================================

    public static final String NAMESPACE = "OData.FlightDataManagement";

    // ========================================================================
    //                          ENTITY TYPES NAMES
    // ========================================================================

    public static final String ET_SCARR_NAME = "Carrier";
    //
    public static final String ET_SPFLI_NAME = "Connection";
    //
    public static final String ET_SBOOK_NAME = "Booking";
    public static final FullQualifiedName ET_SBOOK_FQN = new FullQualifiedName(NAMESPACE, ET_SBOOK_NAME);

    // ========================================================================
    //                          ENTITY SET NAMES
    // ========================================================================

    public static final String ES_SPFLI_NAME = "Connections";
    public static final String ES_SFLIGHT_NAME = "Flights";
    public static final String ES_SBOOK_NAME = "Bookings";

    // ========================================================================
    //                        KEY PARAMETERS OF ENTITIES
    // ========================================================================

    public static final String CARRIER_ID = "CarrierCode";
    public static final String CONNECTION_ID = "FlightConnectionNumber";
    public static final String FLIGHT_DATE = "FlightDate";
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

}
