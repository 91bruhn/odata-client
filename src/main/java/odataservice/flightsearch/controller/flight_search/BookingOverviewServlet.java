package odataservice.flightsearch.controller.flight_search;

import odataservice.flightsearch.model.FlightSearchResult;
import odataservice.flightsearch.util.DataTransformator;
import odataservice.flightsearch.util.EntityNames;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.request.retrieve.ODataEntityRequest;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.core.ODataClientFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static odataservice.flightsearch.util.EntityNames.AIRPORT_FROM;
import static odataservice.flightsearch.util.EntityNames.AIRPORT_TO;
import static odataservice.flightsearch.util.EntityNames.ARRIVAL_TIME;
import static odataservice.flightsearch.util.EntityNames.CARRIER_ID;
import static odataservice.flightsearch.util.EntityNames.CARRIER_NAME;
import static odataservice.flightsearch.util.EntityNames.CITY_FROM;
import static odataservice.flightsearch.util.EntityNames.CITY_TO;
import static odataservice.flightsearch.util.EntityNames.COUNTRY_FROM;
import static odataservice.flightsearch.util.EntityNames.COUNTRY_TO;
import static odataservice.flightsearch.util.EntityNames.CURRENCY;
import static odataservice.flightsearch.util.EntityNames.DEPARTURE_TIME;
import static odataservice.flightsearch.util.EntityNames.ES_SFLIGHT_NAME;
import static odataservice.flightsearch.util.EntityNames.ET_SCARR_NAME;
import static odataservice.flightsearch.util.EntityNames.ET_SPFLI_NAME;
import static odataservice.flightsearch.util.EntityNames.FLIGHT_DATE;
import static odataservice.flightsearch.util.EntityNames.FLIGHT_TIME;
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
@WebServlet("/bookingOverview")
public class BookingOverviewServlet extends HttpServlet {

    private ODataClient mODataClient = ODataClientFactory.getClient();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String firstName = req.getParameter("inputFirstName");
        final String lastName = req.getParameter("inputLastName");
        final String sex = req.getParameter("inputSex");
        final String flightClass = req.getParameter("inputFlightClass");
        final String luggWeight = req.getParameter("inputLuggWeight");
        final String isSmoker = req.getParameter("isSmoker");

        ClientEntity clientEntity = this.readEntity(this.createFlightSearchRequestURI(this.createFlightKeyValuesForDepartureFlight(req)));
        final FlightSearchResult departureFlightSearchResult = DataTransformator.transformFlightSearchResultRequestToFlightSearchResult(clientEntity);

        clientEntity = this.readEntity(this.createFlightSearchRequestURI(this.createFlightKeyValuesForReturnFlight(req)));
        final FlightSearchResult returnFlightSearchResult = DataTransformator.transformFlightSearchResultRequestToFlightSearchResult(clientEntity);

        req.getSession().setAttribute("firstName", firstName);
        req.getSession().setAttribute("lastName", lastName);
        req.getSession().setAttribute("sex", sex);
        req.getSession().setAttribute("flightClass", flightClass);
        req.getSession().setAttribute("luggWeight", luggWeight);
        req.getSession().setAttribute("isSmoker", isSmoker);

        req.getSession().setAttribute("departureFlightSearchResult", departureFlightSearchResult);
        req.getSession().setAttribute("returnFlightSearchResult", returnFlightSearchResult);
        req.getSession().setAttribute("finalPrice",
                                      this.calculateFinalPrice(flightClass,
                                                          departureFlightSearchResult.getFlight().getAirfair(),
                                                          departureFlightSearchResult.getFlight().getCurrency(),
                                                          returnFlightSearchResult.getFlight().getAirfair(),
                                                          returnFlightSearchResult.getFlight().getCurrency()));
        req.getRequestDispatcher("/bookingOverview.jsp").forward(req, resp);
    }

    private Map<String, Object> createFlightKeyValuesForDepartureFlight(HttpServletRequest req) {
        final Map<String, Object> keyValues = new HashMap<>();
        keyValues.put(EntityNames.CARRIER_ID, req.getSession().getAttribute("chosenDepartureCarrId"));
        keyValues.put(EntityNames.CONNECTION_ID, req.getSession().getAttribute("chosenDepartureConnId"));
        keyValues.put(EntityNames.FLIGHT_DATE, req.getSession().getAttribute("chosenDepartureFlightDate"));

        return keyValues;
    }

    private Map<String, Object> createFlightKeyValuesForReturnFlight(HttpServletRequest req) {
        final Map<String, Object> keyValues = new HashMap<>();
        keyValues.put(EntityNames.CARRIER_ID, req.getSession().getAttribute("chosenReturnCarrId"));
        keyValues.put(EntityNames.CONNECTION_ID, req.getSession().getAttribute("chosenReturnConnId"));
        keyValues.put(EntityNames.FLIGHT_DATE, req.getSession().getAttribute("chosenReturnFlightDate"));

        return keyValues;
    }

    private URI createFlightSearchRequestURI(Map<String, Object> flightKeyValues) {
        return mODataClient.newURIBuilder(SERVICE_URI)
                           .appendEntitySetSegment(ES_SFLIGHT_NAME)
                           .appendKeySegment(flightKeyValues)
                           .select(PRICE,
                                   FLIGHT_DATE,
                                   CURRENCY,
                                   SEATS_MAX_E,
                                   SEATS_OCC_E,
                                   SEATS_MAX_B,
                                   SEATS_OCC_B,
                                   SEATS_MAX_F,
                                   SEATS_OCC_F)
                           .expandWithSelect(ET_SPFLI_NAME,
                                             COUNTRY_FROM,
                                             CITY_FROM,
                                             AIRPORT_FROM,
                                             COUNTRY_TO,
                                             CITY_TO,
                                             AIRPORT_TO,
                                             FLIGHT_TIME,
                                             DEPARTURE_TIME,
                                             ARRIVAL_TIME)
                           .expandWithSelect(ET_SCARR_NAME, CARRIER_ID, CARRIER_NAME, URL)
                           .build();
    }

    private ClientEntity readEntity(URI absoluteUri) {
        final ODataEntityRequest<ClientEntity> request = mODataClient.getRetrieveRequestFactory().getEntityRequest(absoluteUri);
        request.setAccept(HEADER_ACCEPT_JSON);
        ODataRetrieveResponse<ClientEntity> response = request.execute();

        return response.getBody();
    }

    private double calculateFinalPrice(String flightClass, double airfareDepartureFlight, String currencyDepartureFlight, double airfareReturnFlight,
                                       String currencyReturnFlight) {
        final DataTransformator dataTransformator = new DataTransformator();
        final double depFlightPriceInEuros = dataTransformator.calculateFlightPriceInEuros(airfareDepartureFlight, currencyDepartureFlight);
        final double retFlightPriceInEuros = dataTransformator.calculateFlightPriceInEuros(airfareReturnFlight, currencyReturnFlight);

        return this.adjustFlightPrice(flightClass, depFlightPriceInEuros) + this.adjustFlightPrice(flightClass, retFlightPriceInEuros);
    }

    private double adjustFlightPrice(String flightClass, double airfare) {
        switch (flightClass) {
            case "Business Class (1,5-facher Aufschlag)":
                return airfare * 1.5;
            case "First Class (3,5-facher Aufschlag)":
                return airfare * 3.5;
            default:
                //Economy Class - Standard
                return airfare;
        }
    }

}

