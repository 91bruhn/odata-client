<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<c:set var="booking" value="${sessionScope.departureBookingSearchResult.booking}"/>
<body class="bg-light">
<div class="jumbotron">
<h2 align="center">Buchungsformular</h2>

<p class="lead" align="center">In diesem Formular haben Sie die M&ouml;glichkeit Ihre Buchung zu ver&auml;ndern.</p>
</div>
<%--<form>--%>
<main role="main" class="container">
<%--<div class="my-3 p-3 bg-white rounded box-shadow">--%>
<%--<body class="text-center">--%>

<%--<div class="py-5 text-center">--%>


<%--<img class="d-block mx-auto mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">--%>


<%--</div>--%>


<div class="jumbotron">
<%--<div class="col-md-8 order-md-1">--%>
<form action="bookingUpdateSuccessful" method="GET">
<%--<div class="form-row">--%>

<%--<div class="form-group col-md-4">--%>
<%--<div class="form-group col-md-2">--%>
<div class="form-group row">
<label for="inputSex">Geschlecht</label><select id="inputSex" class="form-control" name="inputSex">
<%--<div class="col-10">--%>
<option selected>m&auml;nnlich</option>
<option>weiblich</option>
</select>
</div>


<%--</div>--%>


<%--<div class="form-row">--%>


<%--<div class="form-group col-md-4">--%>
<div class="form-group row">
<label for="inputFlightClass">Bef&ouml;rderungsklasse</label>
<%--<div class="col-10">--%>
<select id="inputFlightClass" class="form-control" name="inputFlightClass">
<option selected>Economy Class (Basis: kein Aufschlag)</option>
<option>Business Class (1,5-facher Aufschlag)</option>
<option>First Class (3,5-facher Aufschlag)</option>
</select>
</div>
<%--</div>--%>

<%--<div class="form-group col-md-2">--%>
<div class="form-group row">
<label for="inputLuggWeight">Gewicht Gep&auml;ck in Kg</label>
<input type="text" class="form-control" id="inputLuggWeight" name="inputLuggWeight" value="${booking.luggWeight}">
</div>

<%--</div>--%>


<%--<div class="form-group col-md-2">--%>
<div class="form-group row">
<%--<div class="col-10">--%>
<label for="isSmoker">Raucher?</label>
<select id="isSmoker" class="form-control" name="isSmoker">
<option selected>nein</option>
<option>ja</option>
</select>
</div>
<p></p>
<button type="submit" class="btn btn-primary">Korrigierte Daten speichern</button>
</form>
</div>
<%--</div>--%>
<%--</body>--%>
</main>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>