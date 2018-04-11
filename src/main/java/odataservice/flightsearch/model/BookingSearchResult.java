package odataservice.flightsearch.model;

/**
 * Combined object of a booking and its attached connection and carrier.
 */
public class BookingSearchResult {

    private Booking mBooking;
    private Connection mConnection;
    private Carrier mCarrier;

    // ------------------------------------------------------------------------
    // getters/setters
    // ------------------------------------------------------------------------

    public Booking getBooking() {
        return mBooking;
    }

    public void setBooking(Booking booking) {
        mBooking = booking;
    }

    public Connection getConnection() {
        return mConnection;
    }

    public void setConnection(Connection connection) {
        mConnection = connection;
    }

    public Carrier getCarrier() {
        return mCarrier;
    }

    public void setCarrier(Carrier carrier) {
        mCarrier = carrier;
    }

}
