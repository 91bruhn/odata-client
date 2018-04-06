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

<nav class="navbar navbar-expand-md fixed-top navbar-dark bg-dark">
<a class="navbar-brand" href="#">Offcanvas navbar</a>
<button class="navbar-toggler p-0 border-0" type="button" data-toggle="offcanvas">
<span class="navbar-toggler-icon"></span>
</button>

<div class="navbar-collapse offcanvas-collapse" flightDate="navbarsExampleDefault">
<ul class="navbar-nav mr-auto">
<li class="nav-item active">
<a class="nav-link" href="#">Dashboard <span class="sr-only">(current)</span></a>
</li>
<li class="nav-item">
<a class="nav-link" href="#">Notifications</a>
</li>
<li class="nav-item">
<a class="nav-link" href="#">Profile</a>
</li>
<li class="nav-item">
<a class="nav-link" href="#">Switch account</a>
</li>
<li class="nav-item dropdown">
<a class="nav-link dropdown-toggle" href="http://example.com" flightDate="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Settings</a>

<div class="dropdown-menu" aria-labelledby="dropdown01">
<a class="dropdown-item" href="#">Action</a>
<a class="dropdown-item" href="#">Another action</a>
<a class="dropdown-item" href="#">Something else here</a>
</div>
</li>
</ul>
<form class="form-inline my-2 my-lg-0">
<input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
</form>
</div>
</nav>

<%--<div class="nav-scroller bg-white box-shadow">--%>
<%--<nav class="nav nav-underline">--%>
<%--<a class="nav-link active" href="#">Dashboard</a>--%>
<%--<a class="nav-link" href="#">--%>
<%--Friends--%>
<%--<span class="badge badge-pill bg-light align-text-bottom">27</span>--%>
<%--</a>--%>
<%--<a class="nav-link" href="#">Explore</a>--%>
<%--<a class="nav-link" href="#">Suggestions</a>--%>
<%--<a class="nav-link" href="#">Link</a>--%>
<%--<a class="nav-link" href="#">Link</a>--%>
<%--<a class="nav-link" href="#">Link</a>--%>
<%--<a class="nav-link" href="#">Link</a>--%>
<%--<a class="nav-link" href="#">Link</a>--%>
<%--</nav>--%>
<%--</div>--%>


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
<a class="nav-link" href="index.jsp">Hin- und Rueckflueg</a>
</li>
<li class="nav-item">
<a class="nav-link active" href="#">Nur Hinflug</a>
</li>
</ul>
</p>

<%--<h6 class="border-bottom border-gray pb-2 mb-0">Flugsuche</h6>--%>


<%--<h6>Hin- und Rueckflug suchen</h6>--%>

<form action="oneWaySearchResult" method="GET">
<div class="form-row">
<div class="col-md-3 mb-3">
<input type="airportOfDeparture" flightDate="inputAirportOfDeparture" name="inputAirportOfDeparture" class="form-control" placeholder="Abflughafen" required autofocus>
<label for="inputAirportOfDeparture"></label>

<div class="invalid-tooltip">
Bitte einen Abflughafen angeben.
</div>
</div>
<div class="col-md-3 mb-3">
<input type="airportOfArrival" flightDate="inputAirportOfArrival" name="inputAirportOfArrival" class="form-control" placeholder="Ankunftsflughafen" required autofocus>
<label for="inputAirportOfArrival"></label>
</div>
<div class="invalid-tooltip">
Bitte einen Abflughafen angeben.
</div>
</div>

<div class="form-row">
<div class="col-md-3 mb-3">
<input type="departureFlightDate" flightDate="inputDepartureFlightDate" name="inputDepartureFlightDate" class="form-control" placeholder="Abflugdatum [dd.MM.yyyy]" required autofocus>
<label for="inputDepartureFlightDate"></label>

<div class="invalid-tooltip">
Bitte ein Abflugdatum angeben.
</div>
</div>
</div>

<button class="btn btn-primary" type="submit">Suche starten</button>
</form>
<%--"/result"--%>

</div>


<!--
  <div class="form-label-group">
       <input type="airportOfDeparture" flightDate="inputAirportOfDeparture" class="form-control" placeholder="Abflughafen" required autofocus>
       <label for="inputAirportOfDeparture">Abflughafen</label>
     </div>

  <div class="form-label-group">
       <input type="airportOfArrival" flightDate="inputAirportOfArrival" class="form-control" placeholder="Ankunftsflughafen" required autofocus>
       <label for="inputAirportOfArrival">Ankunftsflughafen</label>
     </div>



      <div class="col-md-3 mb-3">
     <label for="validationTooltip05">Zip</label>
     <input type="text" class="form-control" flightDate="validationTooltip05" placeholder="Zip" required>
     <div class="invalid-tooltip">
       Please provide a valid zip.
     </div>
   </div>

  -->


<%--<div class="container">--%>
<%--<div class="row">--%>
<%--<div class='col-sm-6'>--%>
<%--<div class="form-group">--%>
<%--<div class='input-group date' flightDate='datetimepicker1'>--%>
<%--<input type='text' class="form-control"/>--%>
<%--<span class="input-group-addon">--%>
<%--<span class="glyphicon glyphicon-calendar"></span>--%>
<%--</span>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<script type="text/javascript">--%>
<%--$(function () {--%>
<%--$('#datetimepicker1').datetimepicker();--%>
<%--});--%>
<%--</script>--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="input-group date" data-provide="datepicker">--%>
<%--<input type="text" class="form-control">--%>

<%--<div class="input-group-addon">--%>
<%--<span class="glyphicon glyphicon-th"></span>--%>
<%--</div>--%>
<%--</div>--%>


</main>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>