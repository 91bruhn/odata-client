////////////////////////////////////////////////////////////////////////////////
//
// Created by BBruhns on 08.04.2018.
//
// Copyright (c) 2006 - 2018 FORCAM GmbH. All rights reserved.
////////////////////////////////////////////////////////////////////////////////

package mynamespace.web.model;

/**
 *
 */
public class FlightSearchResult {

    private Flight mFlight;
    private Connection mConnection;
    private Carrier mCarrier;

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
