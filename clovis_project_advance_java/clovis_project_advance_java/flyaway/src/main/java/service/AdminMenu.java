package service;
import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Airline;
import model.City;
import model.Flight;

public class AdminMenu extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Database database = new Database();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("index.jsp");  
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Map<Integer, City> cityMap = database.getAllCities();
		Map<Integer, Airline> airlineMap = database.getAllAirlines();
		Map<Integer, Flight> flightMap = database.getAllFlights();

		response.setContentType("text/html");

		String choice = request.getParameter("adminOption");

		RequestDispatcher rd = request.getRequestDispatcher("/adminMenu.jsp");
		rd.include(request, response);

		switch (choice) {

		case "changePwd":
			RequestDispatcher changePasswordDispatcher = request.getRequestDispatcher("/changeAdminPassword.html");
			changePasswordDispatcher.include(request, response);
			break;
		case "src/dest":
			request.setAttribute("cityMap", cityMap);
			rd.forward(request, response);
			break;
		case "airlines":
			request.setAttribute("airlineMap", airlineMap);
			rd.forward(request, response);
			break;
		case "flights":
			request.setAttribute("flightMap", flightMap);
			request.setAttribute("cityMap", cityMap);
			request.setAttribute("airlineMap", airlineMap);
			rd.forward(request, response);
			break;
		}
	}

}
