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
			<div class="py-5">
				<p class="display-5"><strong>Choose the flight that you would like to book</strong></p>
				<br/>
				<br/>
				<form action="BookFlight" method="post">
			
					<%@ page import="java.util.Set, model.Flight, java.util.Iterator, java.util.Map, model.Airline" %>  
	
					<%
			    		Set<Flight> flightSet = (Set<Flight>)request.getAttribute("flightData");	
			    		int numFlights = flightSet.size();
		    			int numPassengers = (int)request.getAttribute("numPassengers");
						Map<Integer, Airline> airlineMap = (Map<Integer, Airline>)request.getAttribute("airlineMap");
							
						if(airlineMap!=null && flightSet!=null) {
							Iterator iterator = flightSet.iterator();
							
							String responseString="<table class='table table-hover'><thead class='table-light'><tr><th></th><th>Name</th><th>Airline</th><th>Price</th></tr></thead>";
							
							while (iterator.hasNext()) {
								Flight flight = (Flight)iterator.next();
								responseString+="<tr><td><input type='radio' name='flightDropDown' id='flight"+flight.getId()+"' value='"+flight.getId()+"' required/></td><td>"+flight.getName()+"</td><td>"+airlineMap.get(flight.getAirlineId()).getName()+"</td><td>"+flight.getPrice()+"</td></tr>";
					        }
				
							responseString+="</table><br/><br/><p class='display-6'><strong>Enter passenger details</strong><p>";
							
							for(int i=0;i<numPassengers;i++){
								responseString+="<label class='form-label' for=''name"+(i+1)+"''>Passenger "+(i+1)+" Name</label><br/><input class='form-control' type='text' id='passenger"+(i+1)+"' name='name"+(i+1)+"' required/><br/><br/>";
							}
				
							
							out.print(responseString);
						}
					%>			
			
					<input class="btn btn-outline-primary" type="submit" value="Submit" />
			
				</form>
			</div>
		</div>	
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>
	
	</body>
</html>
