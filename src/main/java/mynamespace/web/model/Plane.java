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
public class Plane {

    private String mPlaneType;
    private int mSeatsMaxE;
    private int mSeatsMaxB;
    private int mSeatsMaxF;
    private double mConsumption;
    private String mConUnit;
    private double mTankCap;
    private String mCapUnit;
    private double mWeight;
    private String mWeiUnit;
    private double mSpan;
    private String mSpanUnit;
    private double mLength;
    private String mLengUnit;
    private double mOpSpeed;
    private String mSpeedUnit;
    private String mProducer;

    // ------------------------------------------------------------------------
    // methods
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // getters/setters
    // ------------------------------------------------------------------------

    public String getProducer() {
        return mProducer;
    }

    public void setProducer(String producer) {
        mProducer = producer;
    }

    public String getPlaneType() {
        return mPlaneType;
    }

    public void setPlaneType(String planeType) {
        mPlaneType = planeType;
    }

    public int getSeatsMaxE() {
        return mSeatsMaxE;
    }

    public void setSeatsMaxE(int seatsMaxE) {
        mSeatsMaxE = seatsMaxE;
    }

    public int getSeatsMaxB() {
        return mSeatsMaxB;
    }

    public void setSeatsMaxB(int seatsMaxB) {
        mSeatsMaxB = seatsMaxB;
    }

    public int getSeatsMaxF() {
        return mSeatsMaxF;
    }

    public void setSeatsMaxF(int seatsMaxF) {
        mSeatsMaxF = seatsMaxF;
    }

    public double getConsumption() {
        return mConsumption;
    }

    public void setConsumption(double consumption) {
        mConsumption = consumption;
    }

    public String getConUnit() {
        return mConUnit;
    }

    public void setConUnit(String conUnit) {
        mConUnit = conUnit;
    }

    public double getTankCap() {
        return mTankCap;
    }

    public void setTankCap(double tankCap) {
        mTankCap = tankCap;
    }

    public String getCapUnit() {
        return mCapUnit;
    }

    public void setCapUnit(String capUnit) {
        mCapUnit = capUnit;
    }

    public double getWeight() {
        return mWeight;
    }

    public void setWeight(double weight) {
        mWeight = weight;
    }

    public String getWeiUnit() {
        return mWeiUnit;
    }

    public void setWeiUnit(String weiUnit) {
        mWeiUnit = weiUnit;
    }

    public double getSpan() {
        return mSpan;
    }

    public void setSpan(double span) {
        mSpan = span;
    }

    public String getSpanUnit() {
        return mSpanUnit;
    }

    public void setSpanUnit(String spanUnit) {
        mSpanUnit = spanUnit;
    }

    public double getLength() {
        return mLength;
    }

    public void setLength(double length) {
        mLength = length;
    }

    public String getLengUnit() {
        return mLengUnit;
    }

    public void setLengUnit(String lengUnit) {
        mLengUnit = lengUnit;
    }

    public double getOpSpeed() {
        return mOpSpeed;
    }

    public void setOpSpeed(double opSpeed) {
        mOpSpeed = opSpeed;
    }

    public String getSpeedUnit() {
        return mSpeedUnit;
    }

    public void setSpeedUnit(String speedUnit) {
        mSpeedUnit = speedUnit;
    }

}
