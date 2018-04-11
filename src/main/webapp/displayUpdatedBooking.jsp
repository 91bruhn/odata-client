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
<c:set var="booking" value="${requestScope.updatedResponseBooking}"/>
<c:set var="connection" value="${sessionScope.departureBookingSearchResult.connection}"/>
<c:set var="depAirport" value="${connection.cityFrom}"/>
<c:set var="retAirport" value="${connection.cityTo}"/>

<div class="alert alert-success">
<h1 class="text-center"><b>Ihre erfolgreich aktualisierte Flugbuchung von ${dataTransformator.transformRequestCityName(depAirport)} nach ${dataTransformator.transformRequestCityName(retAirport)}</b></h1>
</div>

<div class="container">
<div class="alert alert-success">
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
<a href="index.jsp" class="btn btn-success" role="button">zur Startseite</a>
</div>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>