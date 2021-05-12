<!DOCTYPE html>
<html>
	<head>
		    <!-- Required meta tags -->
		    <meta charset="utf-8">
		    <meta name="viewport" content="width=device-width, initial-scale=1">
		
			<link href="css/index.css" />
		    <!-- Bootstrap CSS -->
		    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
	
			<title>Flyaway - Airline Ticket Booking Portal</title>
	</head>
	<body class="bg-light">
		<div class="container">
			<div class="py-4">
				<p class="display-4"><strong>Select an option from below</strong></p>
				<br/>
				<form action="AdminMenu" method="post">
					<input class="form-check-input" type="radio" id="changePwd" name="adminOption" value="changePwd">
					<label class="form-check-label" for="changePwd">Change Password</label><br>
					<br/>
					<input class="form-check-input" type="radio" id="src/dest" name="adminOption" value="src/dest">
					<label class="form-check-label" for="src/dest">Get List of all Sources / Destinations</label><br>
					<br/>
					<input class="form-check-input" type="radio" id="airlines" name="adminOption" value="airlines">
					<label class="form-check-label" for="airlines">Get List of all Airlines</label><br>
					<br/>
					<input class="form-check-input" type="radio" id="flights" name="adminOption" value="flights">
					<label class="form-check-label" for="flights">Get List of all Flights</label><br>
					<br/>
					<input class="btn btn-outline-primary" type="submit" value="Submit" />
				</form>
				<br/>
				<%@ page import="java.util.Map, java.util.HashMap, model.*" %>  
			
				<%
						Map<Integer, City> cityMap = (HashMap<Integer, City>)request.getAttribute("cityMap");
						Map<Integer, Airline> airlineMap = (HashMap<Integer, Airline>)request.getAttribute("airlineMap");
						Map<Integer, Flight> flightMap = (HashMap<Integer, Flight>)request.getAttribute("flightMap");
							
						if(cityMap!=null && flightMap==null) {
							String responseString="<table class='table table-hover'><thead class='table-light'><tr><th>#</th><th>City</th></tr></thead>";
							
					        for(Map.Entry<Integer,City> entry : cityMap.entrySet()) {
								City city = (City)entry.getValue();
								responseString+="<tr><td>" + city.getId() + "</td><td>" + city.getName() + "</td></tr>";
							}
				
							responseString+="</table>";
							out.print(responseString);
						}
			
						if(airlineMap!=null && flightMap==null) {
							String responseString="<table class='table table-hover'><thead class='table-light'><tr><th>#</th><th>Airline</th></tr></thead>";
							
					        for(Map.Entry<Integer,Airline> entry : airlineMap.entrySet()) {
					        	Airline airline = (Airline)entry.getValue();
								responseString+="<tr><td>" + airline.getId() + "</td><td>" + airline.getName() + "</td></tr>";
							}
				
							responseString+="</table>";
							out.print(responseString);
						}
			
						if(cityMap!=null && airlineMap!=null && flightMap!=null) {
							String responseString="<table class='table table-hover'><thead class='table-light'><tr><th>#</th><th>Name</th><th>Source</th><th>Destination</th><th>Airline</th><th>Price</th></tr></thead>";
							
							for(Map.Entry<Integer,Flight> entry : flightMap.entrySet()) {
								Flight flight = (Flight)entry.getValue();
								responseString+="<tr><td>" + flight.getId() + "</td><td>"
										+ flight.getName() + "</td><td>"
										+ cityMap.get(flight.getSourceId()).getName() + "</td><td>"
										+ cityMap.get(flight.getDestinationId()).getName() + "</td><td>"
										+ airlineMap.get(flight.getAirlineId()).getName() + "</td><td>" + flight.getPrice() + "</tr>";
							}
				
							responseString+="</table>";
							out.print(responseString);
						}
				%>
			</div>
		</div>
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>
	
	</body>
</html>
