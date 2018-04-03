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

<title>Hello, world!</title>
</head>


<body>
<%--<h3> My name is request.getAttribute("name")</h3>--%>
<h3> My name is ${name1}</h3>

<div class="container">
<table class="table table-hover">
<thead class="thead-dark">
<tr>
<th>Flugdatum</th>
<th>Flugpreis</th>
<th>Max Sitze</th>
<th>Details</th>
</tr>
</thead>
<tbody>

<c:forEach items="${sessionScope.flights}" var="flight">
<tr>
<td>
<c:out value="${flight.flightDate}"/>
</td>
<td>
<c:out value="${flight.airfair} €"/>
</td>
<td>
<c:out value="${flight.seatsMaxE}"/>
</td>
<td>
<%--<%= request.setAttribute("fligtz", request.getAttribute("flights"))%>--%>
<a href="/current.jsp?flightDate=${flight.flightDate}" class="btn btn-info" role="button">Details</a>
<%--<a href="current?flightDate=${product.flightDate}">detail</a>--%>
<%--<button type="button" class="btn btn-info">Info</button>--%>
</td>
</tr>
</c:forEach>


</tbody>
</table>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>
