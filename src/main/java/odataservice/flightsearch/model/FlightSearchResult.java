package odataservice.flightsearch.model;

/**
 * Combined Object of a flight and its attached connection and carrier.
 */
public class FlightSearchResult {

    private Flight mFlight;
    private Connection mConnection;
    private Carrier mCarrier;

    // ------------------------------------------------------------------------
    // getters/setters
    // ------------------------------------------------------------------------

    public Flight getFlight() {
        return mFlight;
    }

    public void setFlight(Flight flight) {
        mFlight = flight;
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
