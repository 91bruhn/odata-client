package odataservice.flightsearch.model;

/**
 *
 */
public class Booking {

    private String mBookId;
    private String mCarrierId;
    private String mConnectionId;
    private String mFlightId;
    private String mCustomId;
    private String mCustType;
    private boolean mSmoker;
    private double mLuggWeight;
    private String mWUnit;
    private boolean mInvoice;
    private String mFlightClass;
    private String mOrderDate;
    private boolean mCancelled;
    private boolean mReserved;

    // ------------------------------------------------------------------------
    // getters/setters
    // ------------------------------------------------------------------------

    public String getBookId() {
        return mBookId;
    }

    public void setBookId(String bookId) {
        mBookId = bookId;
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

    public String getFlightId() {
        return mFlightId;
    }

    public void setFlightId(String flightId) {
        mFlightId = flightId;
    }

    public String getCustomId() {
        return mCustomId;
    }

    public void setCustomId(String customId) {
        mCustomId = customId;
    }

    public String getCustType() {
        return mCustType;
    }

    public void setCustType(String custType) {
        mCustType = custType.equals("Female") ? "weiblich" : "männlich";
    }

    public String getSmoker() {
        return mSmoker ? "Ja" : "Nein";
    }

    public void setSmoker(boolean smoker) {
        mSmoker = smoker;
    }

    public double getLuggWeight() {
        return mLuggWeight;
    }

    public void setLuggWeight(double luggWeight) {
        mLuggWeight = luggWeight;
    }

    public String getWUnit() {
        return mWUnit;
    }

    public void setWUnit(String WUnit) {
        mWUnit = WUnit;
    }

    public boolean isInvoice() {
        return mInvoice;
    }

    public void setInvoice(boolean invoice) {
        mInvoice = invoice;
    }

    public String getFlightClass() {
        return mFlightClass;
    }

    public void setFlightClass(String flightClass) {
        mFlightClass = flightClass;
    }

    public String getOrderDate() {
        return mOrderDate;
    }

    public void setOrderDate(String orderDate) {
        mOrderDate = orderDate;
    }

    public String getCancelled() {
        return mCancelled ? "Ja" : "Nein";
    }

    public void setCancelled(boolean cancelled) {
        mCancelled = cancelled;
    }

    public boolean isReserved() {
        return mReserved;
    }

    public void setReserved(boolean reserved) {
        mReserved = reserved;
    }

}
