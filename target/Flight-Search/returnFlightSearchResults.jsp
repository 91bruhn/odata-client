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

<title>Flugsuchergebnis</title>
</head>


<body>

<%--<div class="container">--%>
<div class="jumbotron">
<h1 class="text-center"><b> Ergebnisse der Flugsuche von <%= request.getParameter("inputAirportOfDeparture")%> nach <%= request.getParameter("inputAirportOfArrival")%>
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
<th>Rueckflug</th>
</tr>
</thead>
<tbody>
<c:forEach items="${sessionScope.returnFlightSearchResults}" var="returnFlightSearchResult">
<c:forEach items="${returnFlightSearchResult.flights}" var="flight">
<tr>
<td>
<c:out value="${flight.flightDate}"/>
</td>
<td>
<c:out value="${returnFlightSearchResult.depTime}"/>
</td>
<td>
<c:out value="${returnFlightSearchResult.arrTime}"/>
</td>
<td>
<c:out value="${returnFlightSearchResult.flTime}"/> min.
</td>
<td>
<c:out value="${returnFlightSearchResult.cityFrom}"/> (<c:out value="${returnFlightSearchResult.airpFrom}"/>)
</td>
<td>
<c:out value="${returnFlightSearchResult.cityTo}"/> (<c:out value="${returnFlightSearchResult.airpTo}"/>)
</td>
<td>
<%--<c:out value="${flight.airFair}"/>--%>
0
</td>
<td>
<%--<c:out value="${returnFlightSearchResult.seatsMaxE}"/>--%>
0
</td>
<td>
<a href="/returnFlightSearchResult.jsp?flightDate=${flight.flightDate}&connId=${searchResult.connId}" class="btn btn-info" role="button">Rueckflug suchen</a>
</td>
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