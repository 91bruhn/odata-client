package odataservice.flightsearch.model;

/**
 *
 */
public class Connection {

    private String mConnId;
    private String mScarrId;
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
    private String mFlType;
    private int mPeriod;

    // ------------------------------------------------------------------------
    // getters/setters
    // ------------------------------------------------------------------------

    public String getConnId() {
        return mConnId;
    }

    public void setConnId(String connId) {
        mConnId = connId;
    }

    public String getScarrId() {
        return mScarrId;
    }

    public void setScarrId(String scarrId) {
        mScarrId = scarrId;
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

    public String getFlType() {
        return mFlType;
    }

    public void setFlType(String flType) {
        mFlType = flType;
    }

    public int getPeriod() {
        return mPeriod;
    }

    public void setPeriod(int period) {
        mPeriod = period;
    }
}
