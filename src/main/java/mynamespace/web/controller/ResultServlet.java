////////////////////////////////////////////////////////////////////////////////
//
// Created by bruhn on 01.04.2018.
//
// Copyright (c) 2006 - 2018 FORCAM GmbH. All rights reserved.
////////////////////////////////////////////////////////////////////////////////

package mynamespace.web.controller;

import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.core.ODataClientFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 *
 */
@WebServlet("/bbb")
public class ResultServlet extends HttpServlet {

    ODataClient mODataClient = ODataClientFactory.getClient();

    //    @Override
    //    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //        String inputAirportOfDeparture = (String) req.getAttribute("inputAirportOfDeparture");
    //        String inputAirportOfArrival = (String) req.getAttribute("inputAirportOfArrival");
    //
    //        String inputAirportOfDeparture2 = req.getParameter("inputAirportOfDeparture");
    //        String inputAirportOfArrival2 = req.getParameter("inputAirportOfArrival");
    //
    //        String inputDepartureFlightDate = (String) req.getAttribute("inputDepartureFlightDate");
    //        String inputReturnFlightDate = (String) req.getAttribute("inputReturnFlightDate");
    //        //        boolean oneWayFlight = (boolean) req.getAttribute("oneWayFlight");
    //
    //        //        <a href="mobile.jsp?id=iphone">Iphone 4S</a>
    //        //        String clickedPhoneId = request.getParameter("id");
    //        //        <c:if test="${param.id == 's5'}">S5 has been clicked</c:if>
    //
    //        req.setAttribute("name1", "Hussein Terek");
    //        req.setAttribute("dada", inputAirportOfDeparture2);
    //        req.setAttribute("bb", inputAirportOfDeparture);
    //
    //        String name = req.getParameter("first_name");
    //
    //        String serviceUri = "http://localhost:8080/flightDataManagement.svc";
    //        String entitySetName = "Flights";
    //        ClientEntitySetIterator<ClientEntitySet, ClientEntity> iterator = readEntities(serviceUri, entitySetName);
    //        List<Flight> flights = new ArrayList<>();
    //        while (iterator.hasNext()) {
    //            ClientEntity ce = iterator.next();
    //            //            ce.getProperty()
    //            //            print("Entry:\n" + prettyPrint(ce.getProperties(), 0));
    //            //            flights = ce.getProperties();
    //            flights.add(Mapper.transformFlightEntityToFlight(ce));
    //            //            flights.add(ce);
    //        }
    //        //        req.getSession().setAttribute("flights", flights);
    //        req.getSession().setAttribute("flights", flights);
    //        req.setAttribute("flights", flights);
    //        req.getRequestDispatcher("/result.jsp").forward(req, resp);
    //    }
    //
    //    public ClientEntitySetIterator<ClientEntitySet, ClientEntity> readEntities(String serviceUri, String entitySetName) {
    //        URI absoluteUri = mODataClient.newURIBuilder(serviceUri).appendEntitySetSegment(entitySetName).build();
    //        return readEntities(absoluteUri);
    //    }
    //
    //    private ClientEntitySetIterator<ClientEntitySet, ClientEntity> readEntities(URI absoluteUri) {
    //        System.out.println("URI = " + absoluteUri);
    //        ODataEntitySetIteratorRequest<ClientEntitySet, ClientEntity> request = mODataClient.getRetrieveRequestFactory()
    //                                                                                           .getEntitySetIteratorRequest(absoluteUri);
    //        // odata4 sample/server limitation not handling metadata=full
    //        request.setAccept("application/json;odata.metadata=minimal");
    //        ODataRetrieveResponse<ClientEntitySetIterator<ClientEntitySet, ClientEntity>> response = request.execute();
    //
    //        return response.getBody();
    //    }
}
