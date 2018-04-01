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
public class Carrier {

    private String mCarrId;
    private String mCarrName;
    private String mCurrCode;
    private String mUrl;

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
