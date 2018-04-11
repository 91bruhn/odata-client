package odataservice.flightsearch.util;

import odataservice.flightsearch.model.Booking;
import odataservice.flightsearch.model.BookingSearchResult;
import odataservice.flightsearch.model.Carrier;
import odataservice.flightsearch.model.Connection;
import odataservice.flightsearch.model.ConnectionSearchResult;
import odataservice.flightsearch.model.Flight;
import odataservice.flightsearch.model.FlightSearchResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.domain.ClientComplexValue;
import org.apache.olingo.client.api.domain.ClientEntity;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static odataservice.flightsearch.util.EntityNames.AIRPORT_FROM;
import static odataservice.flightsearch.util.EntityNames.AIRPORT_TO;
import static odataservice.flightsearch.util.EntityNames.ARRIVAL_TIME;
import static odataservice.flightsearch.util.EntityNames.BOOKING_ID;
import static odataservice.flightsearch.util.EntityNames.CARRIER_ID;
import static odataservice.flightsearch.util.EntityNames.CARRIER_NAME;
import static odataservice.flightsearch.util.EntityNames.CITY_FROM;
import static odataservice.flightsearch.util.EntityNames.CITY_TO;
import static odataservice.flightsearch.util.EntityNames.CONNECTION_ID;
import static odataservice.flightsearch.util.EntityNames.COUNTRY_FROM;
import static odataservice.flightsearch.util.EntityNames.COUNTRY_TO;
import static odataservice.flightsearch.util.EntityNames.CURRENCY;
import static odataservice.flightsearch.util.EntityNames.CUSTOMER_ID;
import static odataservice.flightsearch.util.EntityNames.DEPARTURE_TIME;
import static odataservice.flightsearch.util.EntityNames.DISTANCE;
import static odataservice.flightsearch.util.EntityNames.DISTANCE_UNIT;
import static odataservice.flightsearch.util.EntityNames.ES_SFLIGHT_NAME;
import static odataservice.flightsearch.util.EntityNames.ET_SBOOK_FQN;
import static odataservice.flightsearch.util.EntityNames.ET_SCARR_NAME;
import static odataservice.flightsearch.util.EntityNames.ET_SPFLI_NAME;
import static odataservice.flightsearch.util.EntityNames.FLIGHT_CLASS;
import static odataservice.flightsearch.util.EntityNames.FLIGHT_DATE;
import static odataservice.flightsearch.util.EntityNames.FLIGHT_TIME;
import static odataservice.flightsearch.util.EntityNames.FLIGHT_TYPE;
import static odataservice.flightsearch.util.EntityNames.HAS_INVOICE;
import static odataservice.flightsearch.util.EntityNames.IS_CANCELLED;
import static odataservice.flightsearch.util.EntityNames.IS_RESERVED;
import static odataservice.flightsearch.util.EntityNames.IS_SMOKER;
import static odataservice.flightsearch.util.EntityNames.LUGGAGE_WEIGHT;
import static odataservice.flightsearch.util.EntityNames.ORDER_DATE;
import static odataservice.flightsearch.util.EntityNames.PERIOD;
import static odataservice.flightsearch.util.EntityNames.PLANE_TYPE;
import static odataservice.flightsearch.util.EntityNames.PRICE;
import static odataservice.flightsearch.util.EntityNames.SEATS_MAX_B;
import static odataservice.flightsearch.util.EntityNames.SEATS_MAX_E;
import static odataservice.flightsearch.util.EntityNames.SEATS_MAX_F;
import static odataservice.flightsearch.util.EntityNames.SEATS_OCC_B;
import static odataservice.flightsearch.util.EntityNames.SEATS_OCC_E;
import static odataservice.flightsearch.util.EntityNames.SEATS_OCC_F;
import static odataservice.flightsearch.util.EntityNames.SEX;
import static odataservice.flightsearch.util.EntityNames.URL;
import static odataservice.flightsearch.util.EntityNames.WEIGHT_UNIT;

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
        final ClientComplexValue complexValueCarrier = entityFlightSearchResult.getProperty(ET_SCARR_NAME).getComplexValue();
        carrier.setCarrId((String) complexValueCarrier.get(EntityNames.CARRIER_ID).getValue().asPrimitive().toValue());
        carrier.setCarrName((String) complexValueCarrier.get(CARRIER_NAME).getValue().asPrimitive().toValue());
        carrier.setUrl((String) complexValueCarrier.get(URL).getValue().asPrimitive().toValue());

        final List<Flight> flights = new ArrayList<>();
        entityFlightSearchResult.getProperty(ES_SFLIGHT_NAME).getCollectionValue().forEach(flightRequestEntity -> {
            final Flight flight = new Flight();
            final ClientComplexValue complexValueFlight = flightRequestEntity.asComplex();
            final String flightDate = (String) complexValueFlight.get(FLIGHT_DATE).getValue().asPrimitive().toValue();

            if (considerFlight(flightDate, dateFrom, dateTo)) {
                flight.setFlightDate(flightDate);
                flight.setAirfair((Double) complexValueFlight.get(PRICE).getValue().asPrimitive().toValue());
                flight.setCurrency((String) complexValueFlight.get(CURRENCY).getValue().asPrimitive().toValue());
                flight.setSeatsMaxE((Integer) complexValueFlight.get(SEATS_MAX_E).getValue().asPrimitive().toValue());
                flight.setSeatsMaxB((Integer) complexValueFlight.get(SEATS_MAX_B).getValue().asPrimitive().toValue());
                flight.setSeatsMaxF((Integer) complexValueFlight.get(SEATS_MAX_F).getValue().asPrimitive().toValue());
                flight.setSeatsOccupiedE((Integer) complexValueFlight.get(SEATS_OCC_E).getValue().asPrimitive().toValue());
                flight.setSeatsOccupiedB((Integer) complexValueFlight.get(SEATS_OCC_B).getValue().asPrimitive().toValue());
                flight.setSeatsOccupiedF((Integer) complexValueFlight.get(SEATS_OCC_F).getValue().asPrimitive().toValue());

                flights.add(flight);
            }
        });

        connectionSearchResult.setCarrier(carrier);
        connectionSearchResult.setFlights(flights);

        return connectionSearchResult;
    }

    public static FlightSearchResult transformFlightSearchResultRequestToFlightSearchResult(ClientEntity entityFlightSearchResult) {
        final FlightSearchResult flightSearchResult = new FlightSearchResult();

        final Flight flight = new Flight();
        flight.setFlightDate((String) entityFlightSearchResult.getProperty(FLIGHT_DATE).getValue().asPrimitive().toValue());
        flight.setAirfair((Double) entityFlightSearchResult.getProperty(PRICE).getValue().asPrimitive().toValue());
        flight.setCurrency((String) entityFlightSearchResult.getProperty(CURRENCY).getValue().asPrimitive().toValue());
        flight.setSeatsMaxF((Integer) entityFlightSearchResult.getProperty(SEATS_MAX_E).getValue().asPrimitive().toValue());
        flight.setSeatsOccupiedE((Integer) entityFlightSearchResult.getProperty(SEATS_OCC_E).getValue().asPrimitive().toValue());
        flight.setSeatsMaxB((Integer) entityFlightSearchResult.getProperty(SEATS_MAX_B).getValue().asPrimitive().toValue());
        flight.setSeatsOccupiedB((Integer) entityFlightSearchResult.getProperty(SEATS_OCC_B).getValue().asPrimitive().toValue());
        flight.setSeatsMaxF((Integer) entityFlightSearchResult.getProperty(SEATS_MAX_F).getValue().asPrimitive().toValue());
        flight.setSeatsOccupiedF((Integer) entityFlightSearchResult.getProperty(SEATS_OCC_F).getValue().asPrimitive().toValue());

        final ClientComplexValue complexValueConnection = entityFlightSearchResult.getProperty(ET_SPFLI_NAME).getComplexValue();
        final Connection connection = new Connection();
        connection.setCountryFrom((String) complexValueConnection.get(COUNTRY_FROM).getValue().asPrimitive().toValue());
        connection.setCityFrom((String) complexValueConnection.get(AIRPORT_FROM).getValue().asPrimitive().toValue());
        connection.setAirpFrom((String) complexValueConnection.get(AIRPORT_FROM).getValue().asPrimitive().toValue());
        connection.setCountryTo((String) complexValueConnection.get(COUNTRY_TO).getValue().asPrimitive().toValue());
        connection.setCityTo((String) complexValueConnection.get(CITY_TO).getValue().asPrimitive().toValue());
        connection.setAirpTo((String) complexValueConnection.get(AIRPORT_TO).getValue().asPrimitive().toValue());
        connection.setFlTime((Integer) complexValueConnection.get(FLIGHT_TIME).getValue().asPrimitive().toValue());
        connection.setDepTime((String) complexValueConnection.get(DEPARTURE_TIME).getValue().asPrimitive().toValue());
        connection.setArrTime((String) complexValueConnection.get(ARRIVAL_TIME).getValue().asPrimitive().toValue());

        final ClientComplexValue complexValueCarrier = entityFlightSearchResult.getProperty(ET_SCARR_NAME).getComplexValue();
        final Carrier carrier = new Carrier();
        carrier.setCarrId((String) complexValueCarrier.get(EntityNames.CARRIER_ID).getValue().asPrimitive().toValue());
        carrier.setCarrName((String) complexValueCarrier.get(CARRIER_NAME).getValue().asPrimitive().toValue());
        carrier.setUrl((String) complexValueCarrier.get(URL).getValue().asPrimitive().toValue());

        flightSearchResult.setFlight(flight);
        flightSearchResult.setConnection(connection);
        flightSearchResult.setCarrier(carrier);

        return flightSearchResult;
    }

    public static BookingSearchResult transformBookingSearchResultRequestToBookingSearchResult(ClientEntity entityBookingSearchResult) {
        final BookingSearchResult bookingSearchResult = new BookingSearchResult();

        final Booking booking = new Booking();
        booking.setBookId((String) entityBookingSearchResult.getProperty(BOOKING_ID).getValue().asPrimitive().toValue());
        booking.setFlightId((String) entityBookingSearchResult.getProperty(FLIGHT_DATE).getValue().asPrimitive().toValue());
        booking.setCustomId((String) entityBookingSearchResult.getProperty(CUSTOMER_ID).getValue().asPrimitive().toValue());
        booking.setCustType((String) entityBookingSearchResult.getProperty(SEX).getValue().asPrimitive().toValue());
        booking.setSmoker((Boolean) entityBookingSearchResult.getProperty(IS_SMOKER).getValue().asPrimitive().toValue());
        booking.setLuggWeight((Double) entityBookingSearchResult.getProperty(LUGGAGE_WEIGHT).getValue().asPrimitive().toValue());
        booking.setWUnit((String) entityBookingSearchResult.getProperty(WEIGHT_UNIT).getValue().asPrimitive().toValue());
        booking.setInvoice((Boolean) entityBookingSearchResult.getProperty(HAS_INVOICE).getValue().asPrimitive().toValue());
        booking.setFlightClass((String) entityBookingSearchResult.getProperty(FLIGHT_CLASS).getValue().asPrimitive().toValue());
        booking.setOrderDate((String) entityBookingSearchResult.getProperty(ORDER_DATE).getValue().asPrimitive().toValue());
        booking.setCancelled((Boolean) entityBookingSearchResult.getProperty(IS_CANCELLED).getValue().asPrimitive().toValue());
        booking.setReserved((Boolean) entityBookingSearchResult.getProperty(IS_RESERVED).getValue().asPrimitive().toValue());

        final ClientComplexValue complexValueConnection = entityBookingSearchResult.getProperty(ET_SPFLI_NAME).getComplexValue();
        final Connection connection = new Connection();
        connection.setConnId((String) complexValueConnection.get(CONNECTION_ID).getValue().asPrimitive().toValue());
        connection.setCountryFrom((String) complexValueConnection.get(COUNTRY_FROM).getValue().asPrimitive().toValue());
        connection.setCityFrom((String) complexValueConnection.get(AIRPORT_FROM).getValue().asPrimitive().toValue());
        connection.setAirpFrom((String) complexValueConnection.get(AIRPORT_FROM).getValue().asPrimitive().toValue());
        connection.setCountryTo((String) complexValueConnection.get(COUNTRY_TO).getValue().asPrimitive().toValue());
        connection.setCityTo((String) complexValueConnection.get(CITY_TO).getValue().asPrimitive().toValue());
        connection.setAirpTo((String) complexValueConnection.get(AIRPORT_TO).getValue().asPrimitive().toValue());
        connection.setFlTime((Integer) complexValueConnection.get(FLIGHT_TIME).getValue().asPrimitive().toValue());
        connection.setDepTime((String) complexValueConnection.get(DEPARTURE_TIME).getValue().asPrimitive().toValue());
        connection.setArrTime((String) complexValueConnection.get(ARRIVAL_TIME).getValue().asPrimitive().toValue());

        final ClientComplexValue complexValueCarrier = entityBookingSearchResult.getProperty(ET_SCARR_NAME).getComplexValue();
        final Carrier carrier = new Carrier();
        carrier.setCarrId((String) complexValueCarrier.get(CARRIER_ID).getValue().asPrimitive().toValue());
        carrier.setCarrName((String) complexValueCarrier.get(CARRIER_NAME).getValue().asPrimitive().toValue());
        carrier.setUrl((String) complexValueCarrier.get(URL).getValue().asPrimitive().toValue());

        bookingSearchResult.setBooking(booking);
        bookingSearchResult.setConnection(connection);
        bookingSearchResult.setCarrier(carrier);

        return bookingSearchResult;
    }

    //only consider flights that are between the given requested departure date and the return date
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
        booking.setLuggWeight((Double) entityBooking.getProperty(LUGGAGE_WEIGHT).getValue().asPrimitive().toValue());
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

    public static String getBookingIdFromClientEntity(ClientEntity clientEntityBooking) {
        return (String) clientEntityBooking.getProperty(BOOKING_ID).getValue().asPrimitive().toValue();
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

    public static ClientEntity buildBookingEntity(ODataClient oDataClient, String sex, String flightClass, double luggWeight, String isSmoker,
                                                  String carrierCode, String flightConnectionNumber, String FlightDate) {

        final ClientEntity entityBooking = oDataClient.getObjectFactory().newEntity(ET_SBOOK_FQN);

        entityBooking.getProperties().add(oDataClient.getObjectFactory()
                                                     .newPrimitiveProperty(CARRIER_ID,
                                                                           oDataClient.getObjectFactory().newPrimitiveValueBuilder().buildString(carrierCode)));
        entityBooking.getProperties().add(oDataClient.getObjectFactory()
                                                     .newPrimitiveProperty(CONNECTION_ID,
                                                                           oDataClient.getObjectFactory()
                                                                                      .newPrimitiveValueBuilder()
                                                                                      .buildString(flightConnectionNumber)));
        entityBooking.getProperties().add(oDataClient.getObjectFactory()
                                                     .newPrimitiveProperty(FLIGHT_DATE,
                                                                           oDataClient.getObjectFactory().newPrimitiveValueBuilder().buildString(FlightDate)));
        entityBooking.getProperties().add(oDataClient.getObjectFactory()
                                                     .newPrimitiveProperty(SEX,
                                                                           oDataClient.getObjectFactory()
                                                                                      .newPrimitiveValueBuilder()
                                                                                      .buildString(transformSex(sex))));
        entityBooking.getProperties().add(oDataClient.getObjectFactory()
                                                     .newPrimitiveProperty(IS_SMOKER,
                                                                           oDataClient.getObjectFactory()
                                                                                      .newPrimitiveValueBuilder()
                                                                                      .buildBoolean(transformIsSmoker(isSmoker))));
        entityBooking.getProperties().add(oDataClient.getObjectFactory()
                                                     .newPrimitiveProperty(LUGGAGE_WEIGHT,
                                                                           oDataClient.getObjectFactory().newPrimitiveValueBuilder().buildDouble(luggWeight)));
        entityBooking.getProperties().add(oDataClient.getObjectFactory()
                                                     .newPrimitiveProperty(WEIGHT_UNIT,
                                                                           oDataClient.getObjectFactory().newPrimitiveValueBuilder().buildString("KG")));
        entityBooking.getProperties().add(oDataClient.getObjectFactory()
                                                     .newPrimitiveProperty(HAS_INVOICE,
                                                                           oDataClient.getObjectFactory().newPrimitiveValueBuilder().buildBoolean(true)));
        entityBooking.getProperties().add(oDataClient.getObjectFactory()
                                                     .newPrimitiveProperty(FLIGHT_CLASS,
                                                                           oDataClient.getObjectFactory()
                                                                                      .newPrimitiveValueBuilder()
                                                                                      .buildString(DataTransformator.transformFlightClass(flightClass))));
        entityBooking.getProperties().add(oDataClient.getObjectFactory()
                                                     .newPrimitiveProperty(ORDER_DATE,
                                                                           oDataClient.getObjectFactory()
                                                                                      .newPrimitiveValueBuilder()
                                                                                      .buildString(getDateOfToday())));
        entityBooking.getProperties().add(oDataClient.getObjectFactory()
                                                     .newPrimitiveProperty(IS_CANCELLED,
                                                                           oDataClient.getObjectFactory().newPrimitiveValueBuilder().buildBoolean(false)));
        entityBooking.getProperties().add(oDataClient.getObjectFactory()
                                                     .newPrimitiveProperty(IS_RESERVED,
                                                                           oDataClient.getObjectFactory().newPrimitiveValueBuilder().buildBoolean(true)));

        return entityBooking;
    }

    public static String transformSex(String sex) {
        return sex.equals("weiblich") ? "Female" : "Male";
    }

    public static boolean transformIsSmoker(String isSmoker) {
        return isSmoker.equals("ja");
    }

    public static String getDateOfToday() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        final LocalDate today = LocalDate.now();

        return today.format(formatter);
    }

    public static String transformFlightClass(String flightClass) {
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
