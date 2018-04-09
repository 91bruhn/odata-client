<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link href="/resources/css/offcanvas.css" rel="stylesheet">
</head>

<body class="bg-light">

<main role="main" class="container">

<div class="d-flex align-items-center p-3 my-3 text-white-50 bg-purple rounded box-shadow">
<img class="mr-3" src="https://getbootstrap.com/assets/brand/bootstrap-outline.svg" alt="" width="48" height="48">
<div class="lh-100">
<h6 class="mb-0 text-white lh-100">Flugsuchmaschine</h6>
<small>By Benjamin Bruhn</small>
</div>
</div>


<div class="my-3 p-3 bg-white rounded box-shadow">
<p>
<ul class="nav nav-tabs">
<li class="nav-item">
<a class="nav-link active" href="#">Hin- und R&uuml;ckflug</a>
</li>
<li class="nav-item">
<a class="nav-link" href="bookingSearch.jsp">Buchungen</a>
</li>
</ul>
</p>

<form action="searchResult" method="GET">
<div class="form-row">
<%--            First  Row               --%>
<div class="col-md-3 mb-3">
<%--            Airport of Departure               --%>
<%--<input type="airportOfDeparture" flightDate="inputAirportOfDeparture" name="inputAirportOfDeparture" class="form-control" placeholder="Abflughafen" required autofocus>--%>

<select class="form-control" id="inputAirportOfDeparture" name="inputAirportOfDeparture" required autofocus>
<option value="NEWYORK">New York</option>
<option value="FRANKFURT">Frankfurt</option>
<option value="SANFRANCISCO">San Francisco</option>
<option value="ROME">Rome</option>
<option value="TOKYO">Tokyo</option>
<option value="OSAKA">Osaka</option>
<option value="BERLIN">Berlin</option>
<option value="SINGAPORE">Singapore</option>
<option value="BANGKOK">Bangkok</option>
<option value="KUALALUMPUR">Kualalumpur</option>
<option value="JAKARTA">Jakarta</option>
<option value="HONGKONG">Hongkong</option>
</select>
<label for="inputAirportOfDeparture">Abflughafen</label>

<div class="invalid-tooltip">
Bitte einen Abflughafen angeben.
</div>
</div>


<div class="col-md-3 mb-3">
<%--            Airport of Arrival               --%>
<select class="form-control" id="inputAirportOfArrival" name="inputAirportOfArrival" required autofocus>
<option value="SANFRANCISCO">San Francisco</option>
<option value="NEWYORK">New York</option>
<option value="FRANKFURT">Frankfurt</option>
<option value="ROME">Rome</option>
<option value="TOKYO">Tokyo</option>
<option value="OSAKA">Osaka</option>
<option value="BERLIN">Berlin</option>
<option value="SINGAPORE">Singapore</option>
<option value="BANGKOK">Bangkok</option>
<option value="KUALALUMPUR">Kualalumpur</option>
<option value="JAKARTA">Jakarta</option>
<option value="HONGKONG">Hongkong</option>
</select>
<label for="inputAirportOfArrival">Ankunftsflughafen</label>
</div>
<div class="invalid-tooltip">
Bitte einen Abflughafen angeben.
</div>
</div>


<div class="form-row">
<%--            Second  Row               --%>
<div class="col-md-3 mb-3">
<%--            Departure Flight Date               --%>
<input class="form-control" type="date" value="2017-10-01" id="inputDepartureFlightDate" name="inputDepartureFlightDate" required autofocus>
<label for="inputDepartureFlightDate">Abflugdatum (default: fr&uuml;hester)</label>

<div class="invalid-tooltip">
Bitte ein Abflugdatum angeben.
</div>
</div>


<div class="col-md-3 mb-3">
<%--            Return Flight Date               --%>
<input class="form-control" type="date" value="2018-05-18" id="inputReturnFlightDate" name="inputReturnFlightDate" required autofocus>
<label for="inputReturnFlightDate">R&uuml;ckflugdatum (default: sp&auml;tester)</label>

<div class="invalid-tooltip">
Bitte ein R&uuml;ckflugdatum angeben.
</div>
</div>

</div

<%--            Start Search               --%>
<br>
<button class="btn btn-primary" type="submit">Suche starten</button>
</form>
</div>
</main>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>