package mynamespace.web.controller;

import mynamespace.web.model.ConnectionSearchResult;
import mynamespace.web.util.DataTransformator;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.request.retrieve.ODataEntitySetIteratorRequest;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientEntitySet;
import org.apache.olingo.client.api.domain.ClientEntitySetIterator;
import org.apache.olingo.client.core.ODataClientFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@WebServlet("/searchResult")
public class FlightSearchResultServlet extends HttpServlet {

    private ODataClient mODataClient = ODataClientFactory.getClient();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String inputAirportOfDeparture = req.getParameter("inputAirportOfDeparture");
        final String inputAirportOfArrival = req.getParameter("inputAirportOfArrival");
        final String inputDepartureFlightDate = req.getParameter("inputDepartureFlightDate");
        final String inputReturnFlightDate = req.getParameter("inputReturnFlightDate");

        //TODO auslagern?? oder in Methode speichern...
        //TODO Sample Zeug iwie benutzen?
        //TODO Doku diese Applikation kann die Möglichkeiten von OData nur begrenzt ausreizen...
        final String serviceUri = "http://localhost:8080/flightDataManagement.svc/";
        final String entitySetName = "Connections";
        //TODO in Doku nutzen
        //1 -- http://localhost:8080/flightDataManagement.svc/Connections?$filter=DepartureCity eq 'NEWYORK' and ArrivalCity eq 'SANFRANCISCO'
        //2 -- &$expand=Flights($select=FlightDate,MaxSeatsEconomyClass,OccupiedSeatsInEconomyClass,MaxSeatsBusinessClass,OccupiedSeatsBusinessClass,MaxSeatsFirstClass,OccupiedSeatsFirstClass),
        //3 Carrier($select=CarrierName,URL)
        //TODO use for Flights, Connection vllt Constants
        //TODO naming, extra methode?
        final URI absoluteUri = mODataClient.newURIBuilder(serviceUri)
                                            .appendEntitySetSegment(entitySetName)
                                            .filter("DepartureCity eq '" + inputAirportOfDeparture + "' and ArrivalCity eq '" + inputAirportOfArrival + "'")
                                            .expandWithSelect("Flights",
                                                              "FlightDate",
                                                              "Airfare",
                                                              "LocalCurrencyOfAirline",
                                                              "MaxSeatsEconomyClass",
                                                              "OccupiedSeatsInEconomyClass",
                                                              "MaxSeatsBusinessClass",
                                                              "OccupiedSeatsBusinessClass",
                                                              "MaxSeatsFirstClass",
                                                              "OccupiedSeatsFirstClass")
                                            .expandWithSelect("Carrier", "CarrierCode", "CarrierName", "URL")
                                            .build();
        final ClientEntitySetIterator<ClientEntitySet, ClientEntity> iterator = readEntities(absoluteUri);
        final List<ConnectionSearchResult> searchResults = new ArrayList<>();

        while (iterator.hasNext()) {
            searchResults.add(DataTransformator.transformConnectionSearchResultRequestToConnectionSearchResult(iterator.next(),
                                                                                                               inputDepartureFlightDate,
                                                                                                               inputReturnFlightDate));
        }
        //TODO wenn keine Flüge gefunden wurden zu dem Datum
        //TODO muss jetzt eig noch alles als session scooped?
        //        req.getSession().setAttribute("searchResults", searchResults);
        req.setAttribute("searchResults", searchResults);
        req.getSession().setAttribute("inputAirportOfDeparture", inputAirportOfDeparture);
        req.getSession().setAttribute("inputAirportOfArrival", inputAirportOfArrival);
        req.getSession().setAttribute("inputDepartureFlightDate", inputDepartureFlightDate);
        req.getSession().setAttribute("inputReturnFlightDate", inputReturnFlightDate);
        req.setAttribute("searchResults", searchResults);

        req.getRequestDispatcher("/searchResults.jsp").forward(req, resp);
    }

    private ClientEntitySetIterator<ClientEntitySet, ClientEntity> readEntities(URI absoluteUri) {
        final ODataEntitySetIteratorRequest<ClientEntitySet, ClientEntity> request = mODataClient.getRetrieveRequestFactory().getEntitySetIteratorRequest(
            absoluteUri);
        // todo odata4 sample/server limitation not handling metadata=full
        request.setAccept("application/json;odata.metadata=minimal");
        final ODataRetrieveResponse<ClientEntitySetIterator<ClientEntitySet, ClientEntity>> response = request.execute();

        return response.getBody();
    }

}
