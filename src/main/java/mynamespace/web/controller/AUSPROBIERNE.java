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

    }

}
