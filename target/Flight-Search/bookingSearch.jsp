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
<a class="nav-link" href="index.jsp">Hin- und R&uuml;ckflug</a>
</li>
<li class="nav-item">
<a class="nav-link active" href="#">Buchungen</a>
</li>
</ul>
</p>

<form action="bookingSearchResult" method="GET">
<div class="form-row">
<%--            First  Row               --%>
<div class="col-md-3 mb-3">
<input type="text" class="form-control" id="inputBookingIdDepFlight" name="inputBookingIdDepFlight" placeholder="Buchungsnummer">
<label for="inputBookingIdDepFlight">Hinflug</label>

<div class="invalid-tooltip">
Bitte eine Buchungsnummer angeben.
</div>
</div>

<div class="col-md-3 mb-3">
<input type="text" class="form-control" id="inputBookingIdRetFlight" name="inputBookingIdRetFlight" aria-describedby="infoDepFlight" placeholder="Buchungsnummer">
<label for="inputBookingIdRetFlight">R&uuml;ckflug</label>
<small id="infoDepFlight" class="form-text text-muted">Die Angabe einer zweiten Buchungsnummer ist optional.</small>
</div>


</div>
<button class="btn btn-primary" type="submit">Suche starten</button>
</div>

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