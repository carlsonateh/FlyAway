package service;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeAdminPassword extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final Database database = new Database();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("index.jsp");  
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String newPassword = request.getParameter("newpass");

		if (database.changeAdminPassword(newPassword)) {
			out.print("Password updated successfully");
		} else {
			out.print("Entered Credentials are incorrect.");
		}

		RequestDispatcher rd = request.getRequestDispatcher("adminLogin.html");
		rd.include(request, response);

		out.close();
	}
}
