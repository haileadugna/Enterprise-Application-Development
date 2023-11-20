package com.itsc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;





@WebServlet("/add")
public class AddEmployeeServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      		PrintWriter pw = response.getWriter();

			pw.println("<h1>New Employee</h1>");
			pw.println("<form method=\"POST\">");

			pw.println(" <input hidden type=\"number\" name=\"id\"/> <br/>");
			pw.println("Name: <input type=\"text\" name=\"name\" /> <br/>");
			pw.println("Designation: <input type=\"text\" name=\"designation\" /> <br/>");
			pw.println("Salary: <input type=\"number\" name=\"salary\" /> <br/>");

			pw.println("<button>Register</button>");
			pw.println("</form>");

		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String query = "INSERT INTO employees (name, designation, salary) VALUES (?, ?, ?);";
			PrintWriter pw = response.getWriter();

			String name = request.getParameter("name");
			String designation = request.getParameter("designation");
			int salary = Integer.parseInt(request.getParameter("salary"));

			
			try {
				Connection connection = DBConnection.connect();
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, name);
				ps.setString(2, designation);
				ps.setInt(3, salary);
				
				ps.execute();
				response.sendRedirect("view");
				
			} catch (SQLException e) {
				e.printStackTrace();
				pw.println("error: "+ e.getMessage());
				e.printStackTrace();
			}
	}

}