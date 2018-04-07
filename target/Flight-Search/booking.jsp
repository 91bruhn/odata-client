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
<span class="glyphicon glyphicon-plane"></span>
<h2>Checkout form</h2>

<p class="lead">Below is an example form built entirely with Bootstrap's form controls. Each required form group has a validation state that can be triggered by attempting to submit the form without completing it.</p>
</div>


<div class="jumbotron">
<%--<div class="col-md-8 order-md-1">--%>
<form action="bookingOverview" method="GET">
<div class="form-row">


<div class="form-group col-md-6">
<label for="inputEmail4">Vorname</label>
<input type="email" class="form-control" id="inputEmail4" placeholder="Email">
</div>

<div class="form-group col-md-6">
<label for="inputPassword4">Nachname</label>
<input type="password" class="form-control" id="inputPassword4" placeholder="Password">
</div>


</div>


<div class="form-row">


<div class="form-group col-md-4">
<label for="inputFlightClass">Befoerderungsklasse</label>
<select id="inputFlightClass" class="form-control">
<option selected>Economy Class (Basis: kein Aufschlag)</option>
<option>Business Class (1,5 facher Aufschlag)</option>
<option>First Class (3,5 facher Aufschlag)</option>
</select>
</div>

<div class="form-group col-md-4">
<label for="inputSex">Geschlecht</label>
<select id="inputSex" class="form-control">
<option selected>maennlich</option>
<option>weiblich</option>
</select>
</div>

<div class="form-group col-md-2">
<label for="inputZip">Gewicht Gepaeck in Kg</label>
<input type="text" class="form-control" id="inputZip">
</div>


</div>


<div class="form-group">
<div class="form-check">
<input class="form-check-input" type="checkbox" id="gridCheck">
<label class="form-check-label" for="gridCheck">Raucher?</label>
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