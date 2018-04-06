////////////////////////////////////////////////////////////////////////////////
//
// Created by BBruhns on 05.04.2018.
//
// Copyright (c) 2006 - 2018 FORCAM GmbH. All rights reserved.
////////////////////////////////////////////////////////////////////////////////

package mynamespace.web.controller;

import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.core.ODataClientFactory;

import java.net.URI;

/**
 *
 */
public class AUSPROBIERNE {

    public static void main(String[] args) {
        ODataClient mODataClient = ODataClientFactory.getClient();
        String serviceUri = "http://localhost:8080/flightDataManagement.svc";
        String entitySetName = "Connections";

        //expand, expandWithSelect

        //filter(URIFilter filter);
        URI absoluteUri = mODataClient.newURIBuilder(serviceUri).appendEntitySetSegment(entitySetName).filter(
            "DepartureCity eq 'NEWYORK' and ArrivalCity eq 'SANFRANCISCO'").expandWithSelect("Flights",
                                                                                             "FlightDate",
                                                                                             "MaxSeatsEconomyClass",
                                                                                             "OccupiedSeatsInEconomyClass",
                                                                                             "MaxSeatsBusinessClass",
                                                                                             "OccupiedSeatsBusinessClass",
                                                                                             "MaxSeatsFirstClass",
                                                                                             "OccupiedSeatsFirstClass").expandWithSelect("Carrier",
                                                                                                                                         "CarrierName",
                                                                                                                                         "URL").build();
        System.out.println(absoluteUri.toString());
        System.out.print(absoluteUri.toASCIIString());

        String queryDING = "?";
        //        String filterDING = "$filter=";
        //        String e = "'";
        //
        //        String filterBy =
        //            queryDING + filterDING + "DepartureCity eq " + e + inputAirportOfDeparture + e + " and ArrivalCity eq " + e + inputAirportOfArrival + e;
        //
        //        String selectsjd = "($select=FlightDate,MaxSeatsEconomyClass,OccupiedSeatsInEconomyClass,MaxSeatsBusinessClass,OccupiedSeatsBusinessClass,MaxSeatsFirstClass,OccupiedSeatsFirstClass)";
        //
        //        String expandByFlightsAndCarrier = "&$expand=Flights" + selectsjd + "Carrier($select=CarrierName,URL)";
        //
        //        String combined = serviceUri + entitySetName + filterBy + expandByFlightsAndCarrier;

        //1 -- http://localhost:8080/flightDataManagement.svc/Connections?$filter=DepartureCity eq 'NEWYORK' and ArrivalCity eq 'SANFRANCISCO'
        //2 -- &$expand=Flights($select=FlightDate,MaxSeatsEconomyClass,OccupiedSeatsInEconomyClass,MaxSeatsBusinessClass,OccupiedSeatsBusinessClass,MaxSeatsFirstClass,OccupiedSeatsFirstClass),
        //3 Carrier($select=CarrierName,URL)

    }

}
