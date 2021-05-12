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
				<%@ page import="java.util.*, model.*" %>  
			
				<p class="display-4 text-center"><strong>Here are the details of your booking :</strong></p>
				<br/>
				<br/>
	
				<%
					List<String> passengerNames = (List<String>)request.getAttribute("passengerNames");
					Flight flight = (Flight)request.getAttribute("flight");
					Map<Integer,City> cityMap = (Map<Integer,City>)request.getAttribute("cityMap");
					Map<Integer,Airline> airlineMap = (Map<Integer,Airline>)request.getAttribute("airlineMap");
					if(flight!=null && cityMap!=null && airlineMap!=null) {
							String responseString="<table class='table table-hover'><thead class='table-light'><tr><th>Passenger Name</th><th>Source</th><th>Destination</th><th>TravelDate</th><th>Price</th><th>Airline</th></tr></thead>";
							
					        for(String passengerName : passengerNames) {
					        	responseString+="<tr><td>"+passengerName+"</td><td>"+cityMap.get(flight.getSourceId()).getName()+"</td><td>"+cityMap.get(flight.getDestinationId()).getName()+"</td><td>"+flight.getTravelDate()+"</td><td>"+flight.getPrice()+"</td><td>"+airlineMap.get(flight.getAirlineId()).getName()+"</td></tr>";
					        }
				
							responseString+="</table>";
							out.print(responseString);
						}
				%>
				
				<br/>
				<br/>
				<br/>
				<p class="display-6">Your total amount is <strong><% out.print(flight.getPrice()*passengerNames.size()); %></strong></p>
				<br/>
				<br/>
				<p class="display-5 text-success"><strong>Congratulations!!</strong></p>
				<p class="display-5 text-success"><strong>Your tickets have been booked</strong></p>
			</div>
		</div>
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>

	</body>
</html>
