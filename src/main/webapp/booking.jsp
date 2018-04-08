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

<h2>Buchungsformular</h2>

<p class="lead">Bitte geben Sie Ihre Daten an, um die Buchung f&uuml;r die Reise abzuschlie&szlig;en.
Auf der n&auml;chsten Seite wird der Gesamtpreis berechnet.
Bitte beachten Sie, dass sich der Preis durch die Bef&ouml;rderungsklasse ver&auml;ndert.</p>
</div>


<div class="jumbotron">
<%--<div class="col-md-8 order-md-1">--%>
<form action="bookingOverview" method="GET">
<div class="form-row">


<div class="form-group col-md-4">
<label for="inputFirstName">Vorname</label>
<input type="text" class="form-control" id="inputFirstName" name="inputFirstName" placeholder="Vorname">
</div>

<div class="form-group col-md-4">
<label for="inputLastName">Nachname</label>
<input type="text" class="form-control" id="inputLastName" name="inputLastName" placeholder="Nachname">
</div>

<%--<div class="form-group col-md-4">--%>
<div class="form-group col-md-2">
<label for="inputSex">Geschlecht</label>
<select id="inputSex" class="form-control" name="inputSex">
<option selected>m&auml;nnlich</option>
<option>weiblich</option>
</select>
</div>


</div>


<div class="form-row">


<div class="form-group col-md-4">
<label for="inputFlightClass">Bef&ouml;rderungsklasse</label>
<select id="inputFlightClass" class="form-control" name="inputFlightClass">
<option selected>Economy Class (Basis: kein Aufschlag)</option>
<option>Business Class (1,5-facher Aufschlag)</option>
<option>First Class (3,5-facher Aufschlag)</option>
</select>
</div>


<div class="form-group col-md-2">
<label for="inputLuggWeight">Gewicht Gep&auml;ck in Kg</label>
<input type="text" class="form-control" id="inputLuggWeight" name="inputLuggWeight">
</div>


</div>


<div class="form-group">
<div class="form-check">
<input class="form-check-input" type="checkbox" id="isSmoker" name="isSmoker">
<label class="form-check-label" for="isSmoker">Raucher?</label>
</div>
</div>
<button type="submit" class="btn btn-primary">zur Zusammenfassung</button>
</form>
</div>
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