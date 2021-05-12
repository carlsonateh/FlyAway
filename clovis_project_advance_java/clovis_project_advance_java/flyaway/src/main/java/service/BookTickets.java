package service;
import java.io.IOException;
import java.time.LocalDate;
import model.Airline;
import model.Flight;

import java.io.PrintWriter;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BookTickets extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final Database database = new Database();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("index.jsp");  
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String inputTravelDate = request.getParameter("travelDate");
		int sourceCityId = Integer.parseInt(request.getParameter("source"));
		int destinationCityId = Integer.parseInt(request.getParameter("destination"));
		int passengers = Integer.parseInt( request.getParameter("seats"));

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		formatter = formatter.withLocale(Locale.US);
		LocalDate travelDate = LocalDate.parse(inputTravelDate, formatter);

		// validate source and destination
		if (sourceCityId == destinationCityId) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("<div class='container'>Source and Destinatison cannot be same.</div>");
			RequestDispatcher rd = request.getRequestDispatcher("bookTickets.jsp");
			rd.include(request, response);
		} else {
			showAvailableFlights(travelDate, sourceCityId, destinationCityId, passengers, request, response);				
		}
	}

	private void showAvailableFlights(LocalDate travelDate, Integer sourceId, Integer destinationId,
			Integer passengers, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Date date = java.sql.Date.valueOf(travelDate);
		Set<Flight> flightSet = new HashSet<>();

		AtomicReference<Integer> suitableFlights = new AtomicReference<>(0);
		Map<Integer, Flight> flightMap = database.getAllFlights();
		Map<Integer, Airline> airlineMap = database.getAllAirlines();
		flightMap.values().forEach(flight -> {
			if (flight.getSourceId().equals(sourceId) && flight.getDestinationId().equals(destinationId)
					&& flight.getTravelDate().equals(date)) {
				suitableFlights.getAndSet(suitableFlights.get() + 1);
				flightSet.add(flight);
			}
		});

		if (suitableFlights.get() == 0) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("No flights are available. Please try for another itinerary.");
			RequestDispatcher rd = request.getRequestDispatcher("bookTickets.html");
			rd.include(request, response);
			out.close();
		} else {
			request.setAttribute("numPassengers", passengers);
			request.setAttribute("flightData", flightSet);
			request.setAttribute("airlineMap", airlineMap);
			request.getRequestDispatcher("availableFlights.jsp").forward(request, response);
		}
	}
}
