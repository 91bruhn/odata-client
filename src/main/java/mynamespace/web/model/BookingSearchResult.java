////////////////////////////////////////////////////////////////////////////////
//
// Created by BBruhns on 09.04.2018.
//
// Copyright (c) 2006 - 2018 FORCAM GmbH. All rights reserved.
////////////////////////////////////////////////////////////////////////////////

package mynamespace.web.model;

/**
 *
 */
public class BookingSearchResult {

    private Booking mBooking;
    private Connection mConnection;
    private Carrier mCarrier;

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
