////////////////////////////////////////////////////////////////////////////////
//
// Created by bruhn on 01.04.2018.
//
// Copyright (c) 2006 - 2018 FORCAM GmbH. All rights reserved.
////////////////////////////////////////////////////////////////////////////////

package mynamespace.web.model;

/**
 *
 */
public class Booking {

    private String mBookId;
    private String mScarrId;
    private String mSpfliId;
    private String mSflightId;
    private String mCustomId;
    private Character mCustType;
    private Character mSmoker;
    private double mLuggWeight;
    private String mWUnit;
    private boolean mInvoice;
    private Character mFlightClass;
    private String mOrderDate;
    private boolean mCancelled;
    private boolean mReserved;

    public String getBookId() {
        return mBookId;
    }

    public void setBookId(String bookId) {
        mBookId = bookId;
    }

    public String getScarrId() {
        return mScarrId;
    }

    public void setScarrId(String scarrId) {
        mScarrId = scarrId;
    }

    public String getSpfliId() {
        return mSpfliId;
    }

    public void setSpfliId(String spfliId) {
        mSpfliId = spfliId;
    }

    public String getSflightId() {
        return mSflightId;
    }

    public void setSflightId(String sflightId) {
        mSflightId = sflightId;
    }

    public String getCustomId() {
        return mCustomId;
    }

    public void setCustomId(String customId) {
        mCustomId = customId;
    }

    public Character getCustType() {
        return mCustType;
    }

    public void setCustType(Character custType) {
        mCustType = custType;
    }

    public Character getSmoker() {
        return mSmoker;
    }

    public void setSmoker(Character smoker) {
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

    public Character getFlightClass() {
        return mFlightClass;
    }

    public void setFlightClass(Character flightClass) {
        mFlightClass = flightClass;
    }

    public String getOrderDate() {
        return mOrderDate;
    }

    public void setOrderDate(String orderDate) {
        mOrderDate = orderDate;
    }

    public boolean isCancelled() {
        return mCancelled;
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
