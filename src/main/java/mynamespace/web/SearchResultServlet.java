////////////////////////////////////////////////////////////////////////////////
//
// Created by bruhn on 27.03.2018.
//
// Copyright (c) 2006 - 2018 FORCAM GmbH. All rights reserved.
////////////////////////////////////////////////////////////////////////////////

package mynamespace.web;

import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.request.retrieve.ODataEntitySetIteratorRequest;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientEntitySet;
import org.apache.olingo.client.api.domain.ClientEntitySetIterator;
import org.apache.olingo.client.api.domain.ClientProperty;
import org.apache.olingo.client.core.ODataClientFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 *
 */
@WebServlet("/result")
public class SearchResultServlet extends HttpServlet {

    ODataClient mODataClient = ODataClientFactory.getClient();
    public List<ClientProperty> flightProperties;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String inputAirportOfDeparture = (String) req.getAttribute("inputAirportOfDeparture");
        String inputAirportOfArrival = (String) req.getAttribute("inputAirportOfArrival");

        String inputAirportOfDeparture2 = req.getParameter("inputAirportOfDeparture");
        String inputAirportOfArrival2 = req.getParameter("inputAirportOfArrival");

        String inputDepartureFlightDate = (String) req.getAttribute("inputDepartureFlightDate");
        String inputReturnFlightDate = (String) req.getAttribute("inputReturnFlightDate");
        //        boolean oneWayFlight = (boolean) req.getAttribute("oneWayFlight");

        //        <a href="mobile.jsp?id=iphone">Iphone 4S</a>
        //        String clickedPhoneId = request.getParameter("id");
        //        <c:if test="${param.id == 's5'}">S5 has been clicked</c:if>

        String name = req.getParameter("first_name");

        String serviceUri = "http://localhost:8080/flightDataManagement.svc";
        String entitySetName = "Flights";
        ClientEntitySetIterator<ClientEntitySet, ClientEntity> iterator = readEntities(serviceUri, entitySetName);

        while (iterator.hasNext()) {
            ClientEntity ce = iterator.next();
            //            print("Entry:\n" + prettyPrint(ce.getProperties(), 0));
            flightProperties = ce.getProperties();
        }

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String inputAirportOfDeparture = (String) req.getAttribute("inputAirportOfDeparture");
        String inputAirportOfDeparture2 = req.getParameter("inputAirportOfDeparture");

        String name = req.getParameter("first_name");

        String inputAirportOfArrival = (String) req.getAttribute("inputAirportOfArrival");
        String inputAirportOfArrival2 = req.getParameter("inputAirportOfArrival");

        String inputDepartureFlightDate = (String) req.getAttribute("inputDepartureFlightDate");
        String inputReturnFlightDate = (String) req.getAttribute("inputReturnFlightDate");
        boolean oneWayFlight = (boolean) req.getAttribute("oneWayFlight");

        String serviceUri = "http://localhost:8080/flightDataManagement.svc";
        String entitySetName = "Flights";
        ClientEntitySetIterator<ClientEntitySet, ClientEntity> iterator = readEntities(serviceUri, entitySetName);

        while (iterator.hasNext()) {
            ClientEntity ce = iterator.next();
            //            print("Entry:\n" + prettyPrint(ce.getProperties(), 0));
            flightProperties = ce.getProperties();
        }

    }

    public ClientEntitySetIterator<ClientEntitySet, ClientEntity> readEntities(String serviceUri, String entitySetName) {
        URI absoluteUri = mODataClient.newURIBuilder(serviceUri).appendEntitySetSegment(entitySetName).build();
        return readEntities(absoluteUri);
    }

    private ClientEntitySetIterator<ClientEntitySet, ClientEntity> readEntities(URI absoluteUri) {
        System.out.println("URI = " + absoluteUri);
        ODataEntitySetIteratorRequest<ClientEntitySet, ClientEntity> request = mODataClient.getRetrieveRequestFactory()
                                                                                           .getEntitySetIteratorRequest(absoluteUri);
        // odata4 sample/server limitation not handling metadata=full
        request.setAccept("application/json;odata.metadata=minimal");
        ODataRetrieveResponse<ClientEntitySetIterator<ClientEntitySet, ClientEntity>> response = request.execute();

        return response.getBody();
    }

    //    private static String prettyPrint(Collection<ClientProperty> properties, int level) {
    //        StringBuilder b = new StringBuilder();
    //
    //        for (ClientProperty entry : properties) {
    //            intend(b, level);
    //            ClientValue value = entry.getValue();
    //            if (value.isCollection()) {
    //                ClientCollectionValue cclvalue = value.asCollection();
    //                b.append(prettyPrint(cclvalue.asJavaCollection(), level + 1));
    //            } else if (value.isComplex()) {
    //                ClientComplexValue cpxvalue = value.asComplex();
    //                b.append(prettyPrint(cpxvalue.asJavaMap(), level + 1));
    //            } else if (value.isEnum()) {
    //                ClientEnumValue cnmvalue = value.asEnum();
    //                b.append(entry.getName()).append(": ");
    //                b.append(cnmvalue.getValue()).append("\n");
    //            } else if (value.isPrimitive()) {
    //                b.append(entry.getName()).append(": ");
    //                b.append(entry.getValue()).append("\n");
    //            }
    //        }
    //        return b.toString();
    //    }
}
