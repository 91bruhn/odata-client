package odataservice.flightsearch.model;

import java.util.List;

/**
 * Combined Object of a Connection and the attached flights and its carrier.
 */
public class ConnectionSearchResult {

    private String connId;
    private List<Flight> mFlights;
    private Carrier mCarrier;
    private String mAirpFrom;
    private String mAirpTo;
    private String mCityFrom;
    private String mCityTo;
    private String mCountryFrom;
    private String mCountryTo;
    private int mFlTime;
    private String mDepTime;
    private String mArrTime;
    private double mDistance;
    private String mDistId;

    // ------------------------------------------------------------------------
    // getters/setters
    // ------------------------------------------------------------------------

    public String getConnId() {
        return connId;
    }

    public void setConnId(String connId) {
        this.connId = connId;
    }

    public List<Flight> getFlights() {
        return mFlights;
    }

    public void setFlights(List<Flight> flights) {
        mFlights = flights;
    }

    public Carrier getCarrier() {
        return mCarrier;
    }

    public void setCarrier(Carrier carrier) {
        mCarrier = carrier;
    }

    public String getAirpFrom() {
        return mAirpFrom;
    }

    public void setAirpFrom(String airpFrom) {
        mAirpFrom = airpFrom;
    }

    public String getAirpTo() {
        return mAirpTo;
    }

    public void setAirpTo(String airpTo) {
        mAirpTo = airpTo;
    }

    public String getCityFrom() {
        return mCityFrom;
    }

    public void setCityFrom(String cityFrom) {
        mCityFrom = cityFrom;
    }

    public String getCityTo() {
        return mCityTo;
    }

    public void setCityTo(String cityTo) {
        mCityTo = cityTo;
    }

    public String getCountryFrom() {
        return mCountryFrom;
    }

    public void setCountryFrom(String countryFrom) {
        mCountryFrom = countryFrom;
    }

    public String getCountryTo() {
        return mCountryTo;
    }

    public void setCountryTo(String countryTo) {
        mCountryTo = countryTo;
    }

    public int getFlTime() {
        return mFlTime;
    }

    public void setFlTime(int flTime) {
        mFlTime = flTime;
    }

    public String getDepTime() {
        return mDepTime;
    }

    public void setDepTime(String depTime) {
        mDepTime = depTime;
    }

    public String getArrTime() {
        return mArrTime;
    }

    public void setArrTime(String arrTime) {
        mArrTime = arrTime;
    }

    public double getDistance() {
        return mDistance;
    }

    public void setDistance(double distance) {
        mDistance = distance;
    }

    public String getDistId() {
        return mDistId;
    }

    public void setDistId(String distId) {
        mDistId = distId;
    }
}
