package odataservice.flightsearch.model;

/**
 *
 */
public class Carrier {

    private String mCarrId;
    private String mCarrName;
    private String mCurrCode;
    private String mUrl;

    // ------------------------------------------------------------------------
    // getters/setters
    // ------------------------------------------------------------------------

    public String getCarrId() {
        return mCarrId;
    }

    public void setCarrId(String carrId) {
        mCarrId = carrId;
    }

    public String getCarrName() {
        return mCarrName;
    }

    public void setCarrName(String carrName) {
        mCarrName = carrName;
    }

    public String getCurrCode() {
        return mCurrCode;
    }

    public void setCurrCode(String currCode) {
        mCurrCode = currCode;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}
