package odataservice.flightsearch.controller;

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

        ClientEntity clientEntity = readEntity(createFlightSearchRequestURI(createFlightKeyValuesForDepartureFlight(req)));
        final FlightSearchResult departureFlightSearchResult = DataTransformator.transformFlightSearchResultRequestToFlightSearchResult(clientEntity);

        clientEntity = readEntity(createFlightSearchRequestURI(createFlightKeyValuesForReturnFlight(req)));
        final FlightSearchResult returnFlightSearchResult = DataTransformator.transformFlightSearchResultRequestToFlightSearchResult(clientEntity);

        req.getSession().setAttribute("firstName", firstName);
        req.getSession().setAttribute("lastName", lastName);
        req.getSession().setAttribute("sex", sex);
        req.getSession().setAttribute("flightClass", flightClass);
        req.getSession().setAttribute("luggWeight", luggWeight);
        req.getSession().setAttribute("isSmoker", isSmoker);

        req.getSession().setAttribute("departureFlightSearchResult", departureFlightSearchResult);
        req.getSession().setAttribute("returnFlightSearchResult", returnFlightSearchResult);
        req.getSession().setAttribute("finalPrice", calculateFinalPrice(flightClass,
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
        //todo URI anzeigen
        final String serviceUri = "http://localhost:8080/flightDataManagement.svc/";
        final String entitySetNameFlights = "Flights";
        final String entitySetNameConnection = "Connection";
        final String entitySetNameCarrier = "Carrier";//TODO specific

        return mODataClient.newURIBuilder(serviceUri)
                           .appendEntitySetSegment(entitySetNameFlights)
                           .appendKeySegment(flightKeyValues)
                           .select("Airfare",
                                   "FlightDate",
                                   "LocalCurrencyOfAirline",
                                   "MaxSeatsEconomyClass",
                                   "OccupiedSeatsInEconomyClass",
                                   "MaxSeatsBusinessClass",
                                   "OccupiedSeatsBusinessClass",
                                   "MaxSeatsFirstClass",
                                   "OccupiedSeatsFirstClass")
                           .expandWithSelect(entitySetNameConnection,
                                             "DepartureCountryKey",
                                             "DepartureCity",
                                             "DepartureAirport",
                                             "ArrivalCountryKey",
                                             "ArrivalCity",
                                             "ArrivalAirport",
                                             "FlightTime",
                                             "DepartureTime",
                                             "ArrivalTime")
                           .expandWithSelect(entitySetNameCarrier, EntityNames.CARRIER_ID, "CarrierName", "URL")
                           .build();
    }

    private double calculateFinalPrice(String flightClass, double airfareDepartureFlight, String currencyDepartureFlight, double airfareReturnFlight,
                                       String currencyReturnFlight) {
        final DataTransformator dataTransformator = new DataTransformator();
        final double depFlightPriceInEuros = dataTransformator.calculateFlightPriceInEuros(airfareDepartureFlight, currencyDepartureFlight);
        final double retFlightPriceInEuros = dataTransformator.calculateFlightPriceInEuros(airfareReturnFlight, currencyReturnFlight);

        return adjustFlightPrice(flightClass, depFlightPriceInEuros) + adjustFlightPrice(flightClass, retFlightPriceInEuros);
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

    private ClientEntity readEntity(URI absoluteUri) {
        ODataEntityRequest<ClientEntity> request = mODataClient.getRetrieveRequestFactory().getEntityRequest(absoluteUri);
        // odata4 sample/server limitation not handling metadata=full
        request.setAccept("application/json;odata.metadata=minimal");
        ODataRetrieveResponse<ClientEntity> response = request.execute();

        return response.getBody();
    }

}

