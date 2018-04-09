<%@ page import="mynamespace.web.model.FlightSearchResult" %>
<%@ page import="mynamespace.web.service.DataTransformator" %>
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

<%--<title>Hello, world!</title>--%>
</head>
<body class="bg-light">

<%--<form>--%>
<main role="main" class="container">
<%--<div class="my-3 p-3 bg-white rounded box-shadow">--%>
<body class="text-center">

<div class="py-5 text-center">
<%--<img class="d-block mx-auto mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">--%>

<p class="lead">
<% pageContext.setAttribute("dataTransformator", new DataTransformator()); %>
<% pageContext.setAttribute("flightSearchResult", new FlightSearchResult()); %>

<%--<c:set var="connection" value="${flightSearchResult.carrier(sessionScope.departureFlightSearchResult.carrier)}"/>--%>


<c:set var="finalPrice" value="${sessionScope.finalPrice}"/>
<c:set var="flightClass" value="${sessionScope.flightClass}"/>
<c:set var="luggWeight" value="${sessionScope.luggWeight}"/>

<c:set var="depCity" value="${sessionScope.departureFlightSearchResult.connection.cityFrom}"/>
<c:set var="depCountry" value="${sessionScope.departureFlightSearchResult.connection.countryFrom}"/>
<c:set var="depAirp" value="${sessionScope.departureFlightSearchResult.connection.airpFrom}"/>
<c:set var="depCarrName" value="${sessionScope.departureFlightSearchResult.carrier.carrName}"/>
<c:set var="depCarrId" value="${sessionScope.departureFlightSearchResult.carrier.carrId}"/>
<c:set var="depFlightDate" value="${sessionScope.departureFlightSearchResult.flight.flightDate}"/>
<c:set var="depDepTime" value="${sessionScope.departureFlightSearchResult.connection.depTime}"/>
<c:set var="depFlTime" value="${sessionScope.departureFlightSearchResult.connection.flTime}"/>
<c:set var="depArrTime" value="${sessionScope.departureFlightSearchResult.connection.arrTime}"/>
<c:set var="depCityTo" value="${sessionScope.departureFlightSearchResult.connection.cityTo}"/>
<c:set var="depCountryTo" value="${sessionScope.departureFlightSearchResult.connection.countryTo}"/>
<c:set var="depAirpTo" value="${sessionScope.departureFlightSearchResult.connection.airpTo}"/>


<c:set var="retCity" value="${sessionScope.returnFlightSearchResult.connection.cityFrom}"/>
<c:set var="retCountry" value="${sessionScope.returnFlightSearchResult.connection.countryFrom}"/>
<c:set var="retAirp" value="${sessionScope.returnFlightSearchResult.connection.airpFrom}"/>
<c:set var="retCarrName" value="${sessionScope.returnFlightSearchResult.carrier.carrName}"/>
<c:set var="retCarrId" value="${sessionScope.returnFlightSearchResult.carrier.carrId}"/>
<c:set var="retFlightDate" value="${sessionScope.returnFlightSearchResult.flight.flightDate}"/>
<c:set var="retTime" value="${sessionScope.returnFlightSearchResult.connection.depTime}"/>
<c:set var="retFlTime" value="${sessionScope.returnFlightSearchResult.connection.flTime}"/>
<c:set var="retArrTime" value="${sessionScope.returnFlightSearchResult.connection.arrTime}"/>
<c:set var="retCityTo" value="${sessionScope.returnFlightSearchResult.connection.cityTo}"/>
<c:set var="retCountryTo" value="${sessionScope.returnFlightSearchResult.connection.countryTo}"/>
<c:set var="retAirpTo" value="${sessionScope.returnFlightSearchResult.connection.airpTo}"/>

<c:choose>
<c:when test="${sessionScope.sex=='weiblich'}">
<c:set var="pageSex" value="Frau"/>
</c:when>
<c:otherwise>
<c:set var="pageSex" value="Herr"/>
</c:otherwise>
</c:choose>

<%--<div class="jumbotron">--%>
<%--<h3>Zusammenfassung Ihrer Buchung ${pageSex} ${sessionScope.firstName} ${sessionScope.lastName}</h3>--%>

<%--<p align="left">Sie fliegen von ${dataTransformator.transformRequestCityName(depCity)}, ${depCountry} (${depAirp}) mit der Fluggesellschaft ${depCarrName} (${depCarrId}), am ${depFlightDate},--%>
<%--um ${depDepTime}Uhr los und kommen nach ca. ${depFlTime}min., um ${depArrTime}Uhr, in ${dataTransformator.transformRequestCityName(depCityTo)}, ${depCountryTo} (${depAirpTo}) an.--%>
<%--</p>--%>

<%--<p align="left">--%>
<%--Ihr R&uuml;ckflug geht von ${dataTransformator.transformRequestCityName(retCity)}, ${retCountry} (${retAirp}) mit der Fluggesellschaft ${retCarrName} (${retCarrId}), am ${retFlightDate},--%>
<%--um ${retTime}Uhr los und kommen nach ca. ${retFlTime}min., um ${retArrTime}Uhr, in ${dataTransformator.transformRequestCityName(retCityTo)}, ${retCountryTo} (${retAirpTo}) an.--%>
<%--</p>--%>
<%--</div>--%>
<%--</div>--%>


<div class="alert alert-success" role="alert">
<h3 class="alert-heading" align="center">Zusammenfassung Ihrer Buchung ${pageSex} ${sessionScope.firstName} ${sessionScope.lastName}</h3>

<p></p>

<p align="left">Sie fliegen von ${dataTransformator.transformRequestCityName(depCity)}, ${depCountry} (${depAirp}) mit der Fluggesellschaft ${depCarrName} (${depCarrId}), am ${depFlightDate},
um ${depDepTime}Uhr los und kommen nach ca. ${depFlTime}min., um ${depArrTime}Uhr, in ${dataTransformator.transformRequestCityName(depCityTo)}, ${depCountryTo} (${depAirpTo}) an.
</p>

<p align="left">Ihr R&uuml;ckflug geht von ${dataTransformator.transformRequestCityName(retCity)}, ${retCountry} (${retAirp}) mit der Fluggesellschaft ${retCarrName} (${retCarrId}), am ${retFlightDate},
um ${retTime}Uhr los und kommen nach ca. ${retFlTime}min., um ${retArrTime}Uhr, in ${dataTransformator.transformRequestCityName(retCityTo)}, ${retCountryTo} (${retAirpTo}) an.
</p>

<p align="left">F&uuml;r beide Fl&uuml;ge sind Sitze in der Bef&ouml;rderungsklasse ${flightClass} reserviert.<br>
<b>Beachten Sie, Sie d&uuml;rfen nicht mehr als die angegebenen ${luggWeight}Kg Gep&auml;ck mit auf die Fl&uuml;ge nehmen.</b></p>
<hr>
<strong><p class="mb-0" align="left">Der Gesamtpreis der Reise bel&auml;uft sich auf: ${finalPrice}€</p></strong>

<div class="container">
<a href="/#" class="btn btn-success" role="button">Buchung abschließen</a>
<%--<a href="/booking?connId=${returnFlightSearchResult.connId}&flightDate=${flight.flightDate}&carrId=${carrier.carrId}" class="btn btn-info" role="button">Buchen</a>--%>
</div>
</div>


<%--<c:set var="depAirfare" value="${sessionScope.departureFlightSearchResult.flight.airfair}"/>--%>
<%--<c:set var="depCurrency" value="${sessionScope.departureFlightSearchResult.flight.currency}"/>--%>
<%--<c:set var="retAirfare" value="${sessionScope.departureFlightSearchResult.flight.airfair}"/>--%>
<%--<c:set var="depCurrency" value="${sessionScope.departureFlightSearchResult.flight.currency}"/>--%>


<%--<div class="jumbotron">--%>
<%--&lt;%&ndash;<div class="col-md-8 order-md-1">&ndash;%&gt;--%>
<%--<form action="bookingOverview" method="GET">--%>
<%--<div class="form-row">--%>


<%--<div class="form-group col-md-4">--%>
<%--<label for="inputFirstName">Vorname</label>--%>
<%--<input type="text" class="form-control" id="inputFirstName" placeholder="Vorname">--%>
<%--</div>--%>

<%--<div class="form-group col-md-4">--%>
<%--<label for="inputLastName">Nachname</label>--%>
<%--<input type="text" class="form-control" id="inputLastName" placeholder="Nachname">--%>
<%--</div>--%>

<%--&lt;%&ndash;<div class="form-group col-md-4">&ndash;%&gt;--%>
<%--<div class="form-group col-md-2">--%>
<%--<label for="inputSex">Geschlecht</label>--%>
<%--<select id="inputSex" class="form-control">--%>
<%--<option selected>m&auml;nnlich</option>--%>
<%--<option>weiblich</option>--%>
<%--</select>--%>
<%--</div>--%>


<%--</div>--%>


<%--<div class="form-row">--%>


<%--<div class="form-group col-md-4">--%>
<%--<label for="inputFlightClass">Bef&ouml;rderungsklasse</label>--%>
<%--<select id="inputFlightClass" class="form-control">--%>
<%--<option selected>Economy Class (Basis: kein Aufschlag)</option>--%>
<%--<option>Business Class (1,5-facher Aufschlag)</option>--%>
<%--<option>First Class (3,5-facher Aufschlag)</option>--%>
<%--</select>--%>
<%--</div>--%>


<%--<div class="form-group col-md-2">--%>
<%--<label for="inputLuggWeight">Gewicht Gep&auml;ck in Kg</label>--%>
<%--<input type="text" class="form-control" id="inputLuggWeight">--%>
<%--</div>--%>


<%--</div>--%>


<%--<div class="form-group">--%>
<%--<div class="form-check">--%>
<%--<input class="form-check-input" type="checkbox" id="isSmoker">--%>
<%--<label class="form-check-label" for="isSmoker">Raucher?</label>--%>
<%--</div>--%>
<%--</div>--%>
<%--<button type="submit" class="btn btn-primary">zur Zusammenfassung</button>--%>
<%--</form>--%>
<%--</div>--%>
<%--</div>--%>
</body>
</main>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>