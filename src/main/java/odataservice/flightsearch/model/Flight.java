package odataservice.flightsearch.model;

import java.util.List;

/**
 *
 */
public class Flight {

    private String mFlightDate;
    private String mCarrierId;
    private String mConnectionId;
    private String mPlane;
    private double mAirfair;
    private String mCurrency;
    private int mSeatsMaxE;
    private int mSeatsOccupiedE;
    private int mSeatsMaxB;
    private int mSeatsOccupiedB;
    private int mSeatsMaxF;
    private int mSeatsOccupiedF;

    // ------------------------------------------------------------------------
    // methods
    // ------------------------------------------------------------------------

    public Flight getFlight(List<Flight> flights, String id) {
        for (Flight flight : flights) {
            if (flight.getFlightDate().equals(id)) {
                return flight;
            }
        }

        return null;
    }

    // ------------------------------------------------------------------------
    // getters/setters
    // ------------------------------------------------------------------------

    public String getFlightDate() {
        return mFlightDate;
    }

    public void setFlightDate(String flightDate) {
        mFlightDate = flightDate;
    }

    public String getCarrierId() {
        return mCarrierId;
    }

    public void setCarrierId(String carrierId) {
        mCarrierId = carrierId;
    }

    public String getConnectionId() {
        return mConnectionId;
    }

    public void setConnectionId(String connectionId) {
        mConnectionId = connectionId;
    }

    public String getPlane() {
        return mPlane;
    }

    public void setPlane(String plane) {
        mPlane = plane;
    }

    public double getAirfair() {
        return mAirfair;
    }

    public void setAirfair(double airfair) {
        mAirfair = airfair;
    }

    public String getCurrency() {
        return mCurrency;
    }

    public void setCurrency(String currency) {
        mCurrency = currency;
    }

    public int getSeatsMaxE() {
        return mSeatsMaxE;
    }

    public void setSeatsMaxE(int seatsMaxE) {
        mSeatsMaxE = seatsMaxE;
    }

    public int getSeatsOccupiedE() {
        return mSeatsOccupiedE;
    }

    public void setSeatsOccupiedE(int seatsOccupiedE) {
        mSeatsOccupiedE = seatsOccupiedE;
    }

    public int getSeatsMaxB() {
        return mSeatsMaxB;
    }

    public void setSeatsMaxB(int seatsMaxB) {
        mSeatsMaxB = seatsMaxB;
    }

    public int getSeatsOccupiedB() {
        return mSeatsOccupiedB;
    }

    public void setSeatsOccupiedB(int seatsOccupiedB) {
        mSeatsOccupiedB = seatsOccupiedB;
    }

    public int getSeatsMaxF() {
        return mSeatsMaxF;
    }

    public void setSeatsMaxF(int seatsMaxF) {
        mSeatsMaxF = seatsMaxF;
    }

    public int getSeatsOccupiedF() {
        return mSeatsOccupiedF;
    }

    public void setSeatsOccupiedF(int seatsOccupiedF) {
        mSeatsOccupiedF = seatsOccupiedF;
    }
}
