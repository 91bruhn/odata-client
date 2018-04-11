<%@ page import="odataservice.flightsearch.model.FlightSearchResult" %>
<%@ page import="odataservice.flightsearch.util.DataTransformator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body class="bg-light">

<main role="main" class="container">

<p class="lead">
<% pageContext.setAttribute("dataTransformator", new DataTransformator()); %>
<% pageContext.setAttribute("flightSearchResult", new FlightSearchResult()); %>

<c:set var="depBookingId" value="${requestScope.departureFlightBookingId}"/>
<c:set var="retBookingId" value="${requestScope.returnFlightBookingId}"/>

<c:choose>
<c:when test="${sessionScope.sex=='weiblich'}">
<c:set var="pageSex" value="Frau"/>
</c:when>
<c:otherwise>
<c:set var="pageSex" value="Herr"/>
</c:otherwise>
</c:choose>


<div class="alert alert-success" role="alert">
<h3 class="alert-heading" align="center">Glückwunsch, Ihre Buchungen wurde erfolgreich gespeichert ${pageSex} ${sessionScope.firstName} ${sessionScope.lastName}!</h3>

<p></p>

<p align="center">Sie können nun von der Startseite aus unter dem Reiter "Buchungen" Ihre Buchungen einsehen und bearbeiten.
</p>
<br>
<hr>
<strong><p class="mb-0" align="left">Bitte notieren Sie sich unbedingt Ihre Buchungsnummern:</p>
<ul>
<li>Hinflug: ${depBookingId}</li>
<li>R&uuml;ckflug: ${retBookingId}</li>
</ul>
</strong>

<div class="container">
<a href="/index.jsp" class="btn btn-success" role="button">zur Startseite</a>
</div>
</main>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>