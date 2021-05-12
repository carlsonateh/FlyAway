package service;


import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Airline;
import model.City;
import model.Flight;

public class BookFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Database database = new Database();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("index.jsp");  
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> passengerNames = new ArrayList<>();
		List<String> parameterNamesList =   Collections.list(request.getParameterNames());
		Boolean flag = false;
		for(String parameter : parameterNamesList) {
			String parameterValue = request.getParameter(parameter);
			if(parameterValue==null || "".equals(parameterValue)) {
				flag=true;
			} else if(!"flightDropDown".equals(parameter)) {
				passengerNames.add(parameterValue);
			}
		}
		if(flag) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("Please enter all fields.");
			RequestDispatcher rd = request.getRequestDispatcher("availableFlights.jsp");
			rd.include(request, response);
		} else {
			Integer flightId = Integer.parseInt(request.getParameter("flightDropDown"));
			reviewDetails(flightId, passengerNames, request, response);
		}
	}
	
	private void reviewDetails(Integer flightId, List<String> passengerNames, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Map<Integer, Flight> flightMap = database.getAllFlights();
		Map<Integer, City> cityMap = database.getAllCities();
		Map<Integer,Airline> airlineMap = database.getAllAirlines();
		Flight flight = flightMap.get(flightId);
		request.setAttribute("passengerNames", passengerNames);
		request.setAttribute("flight", flight);
		request.setAttribute("cityMap", cityMap);
		request.setAttribute("airlineMap", airlineMap);
		request.getRequestDispatcher("summary.jsp").forward(request, response);
	}

}
