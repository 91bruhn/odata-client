////////////////////////////////////////////////////////////////////////////////
//
// Created by bruhn on 01.04.2018.
//
// Copyright (c) 2006 - 2018 FORCAM GmbH. All rights reserved.
////////////////////////////////////////////////////////////////////////////////

package mynamespace.web.service;

import mynamespace.web.model.Flight;
import org.apache.olingo.client.api.domain.ClientEntity;

import static mynamespace.web.util.EntityNames.CARRIER_ID;
import static mynamespace.web.util.EntityNames.CONNECTION_ID;
import static mynamespace.web.util.EntityNames.CURRENCY;
import static mynamespace.web.util.EntityNames.FLIGHT_DATE;
import static mynamespace.web.util.EntityNames.PLANE_TYPE;
import static mynamespace.web.util.EntityNames.PRICE;
import static mynamespace.web.util.EntityNames.SEATS_MAX_B;
import static mynamespace.web.util.EntityNames.SEATS_MAX_E;
import static mynamespace.web.util.EntityNames.SEATS_MAX_F;
import static mynamespace.web.util.EntityNames.SEATS_OCC_B;
import static mynamespace.web.util.EntityNames.SEATS_OCC_E;
import static mynamespace.web.util.EntityNames.SEATS_OCC_F;

/**
 *
 */
public class Mapper {

    public static Flight transformFlight(ClientEntity clientEntity) {
        final Flight flight = new Flight();

        flight.setFlightDate((String) clientEntity.getProperty(FLIGHT_DATE).getValue().asPrimitive().toValue());
        flight.setAirfair((Double) clientEntity.getProperty(CARRIER_ID).getValue().asPrimitive().toValue());
        flight.setAirfair((Double) clientEntity.getProperty(CONNECTION_ID).getValue().asPrimitive().toValue());
        flight.setAirfair((Double) clientEntity.getProperty(PLANE_TYPE).getValue().asPrimitive().toValue());
        flight.setAirfair((Double) clientEntity.getProperty(PRICE).getValue().asPrimitive().toValue());
        flight.setAirfair((Double) clientEntity.getProperty(CURRENCY).getValue().asPrimitive().toValue());
        flight.setSeatsMaxE((Integer) clientEntity.getProperty(SEATS_MAX_E).getValue().asPrimitive().toValue());
        flight.setAirfair((Double) clientEntity.getProperty(SEATS_OCC_E).getValue().asPrimitive().toValue());
        flight.setAirfair((Double) clientEntity.getProperty(SEATS_MAX_B).getValue().asPrimitive().toValue());
        flight.setAirfair((Double) clientEntity.getProperty(SEATS_OCC_B).getValue().asPrimitive().toValue());
        flight.setAirfair((Double) clientEntity.getProperty(SEATS_MAX_F).getValue().asPrimitive().toValue());
        flight.setAirfair((Double) clientEntity.getProperty(SEATS_OCC_F).getValue().asPrimitive().toValue());

        return flight;
    }
}
