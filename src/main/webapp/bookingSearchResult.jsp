<%@ page import="odataservice.flightsearch.util.DataTransformator" %>
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

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<title>Buchungssuchergebnis</title>
</head>

<body>
<% pageContext.setAttribute("dataTransformator", new DataTransformator()); %>
<c:set var="connection" value="${sessionScope.departureBookingSearchResult.connection}"/>
<c:set var="depCarrier" value="${sessionScope.departureBookingSearchResult.carrier}"/>
<c:set var="booking" value="${sessionScope.departureBookingSearchResult.booking}"/>
<c:set var="depAirportFrom" value="${connection.cityFrom}"/>
<c:set var="depAirportTo" value="${connection.cityTo}"/>

<c:set var="retConnection" value="${sessionScope.returnBookingSearchResult.connection}"/>
<c:set var="retCarrier" value="${sessionScope.returnBookingSearchResult.carrier}"/>
<c:set var="retBooking" value="${sessionScope.returnBookingSearchResult.booking}"/>
<c:set var="retAirportFrom" value="${retConnection.cityFrom}"/>
<c:set var="retAirportTo" value="${retConnection.cityTo}"/>

<div class="jumbotron">
<h1 class="text-center"><b>Ihre Flugbuchung von ${dataTransformator.transformRequestCityName(depAirportFrom)} nach ${dataTransformator.transformRequestCityName(depAirportTo)}</b></h1>
</div>

<div class="container">
<div class="jumbotron">
<h3 align="center">Fluginformationen</h3>
<table class="table table-striped table-dark">
<thead class="thead-dark">
<tr>
<th>Abflugsdatum</th>
<th>Abflugszeit</th>
<th>Ankunftszeit</th>
<th>Flugdauer</th>
<th>Abflugflughafen</th>
<th>Ankunftsflughafen</th>
<th>Fluggesellschaft</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<c:out value="${booking.flightId}"/>
</td>
<td>
<c:out value="${connection.depTime}"/>
</td>
<td>
<c:out value="${connection.arrTime}"/>
</td>
<td>
<c:out value="${connection.flTime}"/>min.
</td>
<td>
<c:out value="${connection.cityFrom}"/> (<c:out value="${connection.airpFrom}"/>)
</td>
<td>
<c:out value="${connection.cityTo}"/> (<c:out value="${connection.airpTo}"/>)
</td>
<td>
<c:out value="${depCarrier.carrName}"/> (<c:out value="${depCarrier.carrId}"/>)
</td>
</tr>

</tbody>
</table>
<p></p>

<h3 align="center">Buchungsinformationen</h3>
<table class="table table-hover table-dark">
<thead class="thead-dark">
<tr>
<th>Buchungsnummer</th>
<th>Geschlecht</th>
<th>Raucher</th>
<th>Gewicht Gep&auml;ck</th>
<th>Bef&ouml;rderungsklasse</th>
<th>Datum der Buchung</th>
<th>Flug gestrichen?</th>
</tr>
</thead>
<tbody>

<tr>
<td>
<c:out value="${booking.bookId}"/>
</td>
<td>
<c:out value="${booking.custType}"/>
</td>
<td>
<c:out value="${booking.smoker}"/>
</td>
<td>
<c:out value="${booking.luggWeight}"/> (<c:out value="${booking.WUnit}"/>)
</td>
<td>
<c:out value="${booking.flightClass}"/>
</td>
<td>
<c:out value="${booking.orderDate}"/>
</td>
<td>
<c:out value="${booking.cancelled}"/>
</td>
</tr>

</tbody>
</table>
<a href="/updateBooking?bookingId=${booking.bookId}" class="btn btn-warning" role="button">Buchung Hinflug bearbeiten</a>
</div>
</div>

<div class="jumbotron">
<h1 class="text-center"><b>Ihre Flugbuchung von ${dataTransformator.transformRequestCityName(retAirportFrom)} nach ${dataTransformator.transformRequestCityName(retAirportTo)}</b></h1>
</div>

<div class="container">
<div class="jumbotron">
<h3 align="center">Fluginformationen</h3>
<table class="table table-striped table-dark">
<thead class="thead-dark">
<tr>
<th>Abflugsdatum</th>
<th>Abflugszeit</th>
<th>Ankunftszeit</th>
<th>Flugdauer</th>
<th>Abflugflughafen</th>
<th>Ankunftsflughafen</th>
<th>Fluggesellschaft</th>
</tr>
</thead>
<tbody>
<tr>
<td>
<c:out value="${retBooking.flightId}"/>
</td>
<td>
<c:out value="${retConnection.depTime}"/>
</td>
<td>
<c:out value="${retConnection.arrTime}"/>
</td>
<td>
<c:out value="${retConnection.flTime}"/>min.
</td>
<td>
<c:out value="${retConnection.cityFrom}"/> (<c:out value="${retConnection.airpFrom}"/>)
</td>
<td>
<c:out value="${retConnection.cityTo}"/> (<c:out value="${retConnection.airpTo}"/>)
</td>
<td>
<c:out value="${retCarrier.carrName}"/> (<c:out value="${retCarrier.carrId}"/>)
</td>
</tr>


</tbody>
</table>
<p></p>

<h3 align="center">Buchungsinformationen</h3>
<table class="table table-hover table-dark">
<thead class="thead-dark">
<tr>
<th>Buchungsnummer</th>
<th>Geschlecht</th>
<th>Raucher</th>
<th>Gewicht Gep&auml;ck</th>
<th>Bef&ouml;rderungsklasse</th>
<th>Datum der Buchung</th>
<th>Flug gestrichen?</th>
</tr>
</thead>
<tbody>

<tr>
<td>
<c:out value="${retBooking.bookId}"/>
</td>
<td>
<c:out value="${retBooking.custType}"/>
</td>
<td>
<c:out value="${retBooking.smoker}"/>
</td>
<td>
<c:out value="${retBooking.luggWeight}"/> (<c:out value="${retBooking.WUnit}"/>)
</td>
<td>
<c:out value="${retBooking.flightClass}"/>
</td>
<td>
<c:out value="${retBooking.orderDate}"/>
</td>
<td>
<c:out value="${retBooking.cancelled}"/>
</td>
</tr>

</tbody>
</table>
<a href="/updateBooking?bookingId=${retBooking.bookId}" class="btn btn-warning" role="button">Buchung R&uuml;ckflug bearbeiten</a>
</div>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>