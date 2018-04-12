package odataservice.flightsearch.controller.flight_search;

import odataservice.flightsearch.model.ConnectionSearchResult;
import odataservice.flightsearch.util.DataTransformator;
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

import static odataservice.flightsearch.util.EntityNames.CARRIER_ID;
import static odataservice.flightsearch.util.EntityNames.CARRIER_NAME;
import static odataservice.flightsearch.util.EntityNames.CITY_FROM;
import static odataservice.flightsearch.util.EntityNames.CITY_TO;
import static odataservice.flightsearch.util.EntityNames.CURRENCY;
import static odataservice.flightsearch.util.EntityNames.ES_SFLIGHT_NAME;
import static odataservice.flightsearch.util.EntityNames.ES_SPFLI_NAME;
import static odataservice.flightsearch.util.EntityNames.ET_SCARR_NAME;
import static odataservice.flightsearch.util.EntityNames.FLIGHT_DATE;
import static odataservice.flightsearch.util.EntityNames.HEADER_ACCEPT_JSON;
import static odataservice.flightsearch.util.EntityNames.PRICE;
import static odataservice.flightsearch.util.EntityNames.SEATS_MAX_B;
import static odataservice.flightsearch.util.EntityNames.SEATS_MAX_E;
import static odataservice.flightsearch.util.EntityNames.SEATS_MAX_F;
import static odataservice.flightsearch.util.EntityNames.SEATS_OCC_B;
import static odataservice.flightsearch.util.EntityNames.SEATS_OCC_E;
import static odataservice.flightsearch.util.EntityNames.SEATS_OCC_F;
import static odataservice.flightsearch.util.EntityNames.SERVICE_URI;
import static odataservice.flightsearch.util.EntityNames.URL;

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

        final ClientEntitySetIterator<ClientEntitySet, ClientEntity> iterator = this.readEntities(this.createFlightSearchRequestURI(inputAirportOfDeparture,
                                                                                                                                    inputAirportOfArrival));
        final List<ConnectionSearchResult> searchResults = new ArrayList<>();
        while (iterator.hasNext()) {
            searchResults.add(DataTransformator.transformConnectionSearchResultRequestToConnectionSearchResult(iterator.next(),
                                                                                                               inputDepartureFlightDate,
                                                                                                               inputReturnFlightDate));
        }

        req.setAttribute("searchResults", searchResults);
        req.getSession().setAttribute("inputAirportOfDeparture", inputAirportOfDeparture);
        req.getSession().setAttribute("inputAirportOfArrival", inputAirportOfArrival);
        req.getSession().setAttribute("inputDepartureFlightDate", inputDepartureFlightDate);
        req.getSession().setAttribute("inputReturnFlightDate", inputReturnFlightDate);
        req.setAttribute("searchResults", searchResults);

        req.getRequestDispatcher("/searchResults.jsp").forward(req, resp);
    }

    private URI createFlightSearchRequestURI(String airportOfDeparture, String airportOfArrival) {
        return mODataClient.newURIBuilder(SERVICE_URI).appendEntitySetSegment(ES_SPFLI_NAME).filter(
            CITY_FROM + " eq '" + airportOfDeparture + "' and " + CITY_TO + " eq '" + airportOfArrival + "'").expandWithSelect(ES_SFLIGHT_NAME,
                                                                                                                               FLIGHT_DATE,
                                                                                                                               PRICE,
                                                                                                                               CURRENCY,
                                                                                                                               SEATS_MAX_E,
                                                                                                                               SEATS_OCC_E,
                                                                                                                               SEATS_MAX_B,
                                                                                                                               SEATS_OCC_B,
                                                                                                                               SEATS_MAX_F,
                                                                                                                               SEATS_OCC_F).expandWithSelect(
            ET_SCARR_NAME,
            CARRIER_ID,
            CARRIER_NAME,
            URL).build();
    }

    private ClientEntitySetIterator<ClientEntitySet, ClientEntity> readEntities(URI absoluteUri) {
        final ODataEntitySetIteratorRequest<ClientEntitySet, ClientEntity> request = mODataClient.getRetrieveRequestFactory().getEntitySetIteratorRequest(
            absoluteUri);
        request.setAccept(HEADER_ACCEPT_JSON);
        final ODataRetrieveResponse<ClientEntitySetIterator<ClientEntitySet, ClientEntity>> response = request.execute();

        return response.getBody();
    }

}
