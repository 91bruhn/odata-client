<%@ page import="mynamespace.web.service.DataTransformator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--Notwendig--%>
<%@ page session="true" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<%--<c:set var = "airportOfDeparture" scope = "session" value = "request.getAttribute("inputAirportOfDeparture")"/>--%>
<%--<c:set var = "airportOfArrival" scope = "session" value = "${2000*2}"/>--%>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<title>Flugsuchergebnis</title>
</head>

<%--<%= ((Person)request.getAttribute("PesronObj"))%>--%>
<body>
<% pageContext.setAttribute("dataTransformator", new DataTransformator()); %>
<c:set var="airportOfDeparture" value="${sessionScope.inputAirportOfDeparture}"/>
<c:set var="airportOfArrival" value="${sessionScope.inputAirportOfArrival}"/>
<%--<c:set var="airportOfDepartureFormatted" value="${dataTransformator.transformRequestCityName(airportOfDeparture)}"/>--%>
<%--<div class="container">--%>
<div class="jumbotron">
<h1 class="text-center"><b>Ergebnisse der Flugsuche von ${dataTransformator.transformRequestCityName(airportOfDeparture)} nach ${dataTransformator.transformRequestCityName(airportOfArrival)}
</b></h1>
</div>
<%--</div>--%>

<div class="container">
<%--<table class="table table-hover">table table-hover table-dark--%>
<table class="table table-hover">
<thead class="thead-dark">
<tr>
<th>Abflugsdatum</th>
<th>Abflugszeit</th>
<th>Ankunftszeit</th>
<th>Flugdauer</th>
<th>Abflugflughafen</th>
<th>Ankunftsflughafen</th>
<th>Flugpreis</th>
<th>Vorh. Sitzplätze</th>
<th>Rückflug hinzufügen</th>
</tr>
</thead>
<tbody>
<c:forEach items="${requestScope.searchResults}" var="searchResult">
<c:forEach items="${searchResult.flights}" var="flight">
<c:set value="${searchResult.carrier}" var="carrier"/>
<tr>
<td>
<c:out value="${flight.flightDate}"/>
</td>
<td>
<c:out value="${searchResult.depTime}"/>
</td>
<td>
<c:out value="${searchResult.arrTime}"/>
</td>
<td>
<c:out value="${searchResult.flTime}"/> min.
</td>
<td>
<c:out value="${searchResult.cityFrom}"/> (<c:out value="${searchResult.airpFrom}"/>)
</td>
<td>
<c:out value="${searchResult.cityTo}"/> (<c:out value="${searchResult.airpTo}"/>)
</td>
<td>
${dataTransformator.calculateFlightPriceInEuros(flight.airfair, flight.currency)} €
</td>
<td>
${dataTransformator.getCombinedAmountOfAvailableSeats(flight.seatsMaxE, flight.seatsMaxB, flight.seatsMaxF, flight.seatsOccupiedE, flight.seatsOccupiedB, flight.seatsOccupiedF)}
</td>
<td>
<a href="/returnFlightSearchResult?connId=${searchResult.connId}&flightDate=${flight.flightDate}&carrId=${carrier.carrId}" class="btn btn-info" role="button">R&uuml;ckflug suchen</a>
</td>
<%--&carrierCode=${carrier.carrierId}--%>
<%--<%= request.setAttribute("fligtz", request.getAttribute("flights"))%>--%>


<%--<a href="/current.jsp?flightDate=${flight.flightDate}" class="btn btn-info" role="button">Details</a>--%>


<%--<a href="current?flightDate=${product.flightDate}">detail</a>--%>
<%--<button type="button" class="btn btn-info">Info</button>--%>
</td>
</tr>
</c:forEach>
</c:forEach>


</tbody>
</table>
<%--</div>--%>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>
<%--<a href="mobile.jsp?id=iphone">Iphone 4S</a>--%>
<%--String clickedPhoneId = request.getParameter("id");--%>
<%--<c:if test="${param.id == 's5'}">S5 has been clicked</c:if>--%>