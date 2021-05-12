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
				<p class="display-4"><strong>Enter your travel details</strong></p>
				<br/>
				<br/>
				<%@ page import="java.util.Map, java.util.HashMap, model.*,service.Database" %>  
				
				<form action="BookTickets" method="post">
				
					<label class="form-label" for="travelDate">Travel Date:</label>
					<input class="form-control" type="date" name="travelDate" value="2021-04-09" max="2025-01-01" min="2021-01-01"/> <br /> <br /> 
					<label class="form-label" for="source">From:</label>
					<select class="form-select" name="source" id="source">
					<%
					Database db = new Database();
							Map<Integer, City> cityMap =  (Map<Integer, City>)db.getAllCities();
							for (Map.Entry<Integer, City> entry : cityMap.entrySet()) {
								City city = (City)entry.getValue();
					            		out.print("<option value='"+city.getId()+"'>"+city.getName()+"</option>");
					    			}
					%>
					</select><br /> <br />
					
					<label class="form-label" for="destination">To:</label>
					<select class="form-select" name="destination" id="destination">
					<%
						for (Map.Entry<Integer, City> entry : cityMap.entrySet()) {
							City city = (City)entry.getValue();
		            		out.print("<option value='"+city.getId()+"'>"+city.getName()+"</option>");
		    			}
					%>
					</select><br /> <br />
		
					<label class="form-label" for="seats">Seats</label>
					<input class="form-control" type="number" name="seats" min=1 placeholder="1" value="1"/><br /> <br /> 
					<input class="btn btn-outline-primary"	type="submit" value="Submit" />
				</form>
			</div>
		</div>		
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>
		

	</body>
</html>
