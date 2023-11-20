package com.itsc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/view")
public class ViewEmployeesServlet extends HttpServlet {
	String query = "SELECT id, name, designation, salary FROM employees;";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		try {
			Connection connection = DBConnection.connect();
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			pw.println("<h1>Employees</h1>");
			pw.println("<a href=\"add\" >Add New Employee</a>");
			pw.println("<table>");
			pw.println("<tr>");
			pw.println("<th>Id</th> <th>Name</th> <th>Designation</th> <th>Salary</th> <th>---</th>");
			pw.println("</tr>");
			while (rs.next()) {
				int id = rs.getInt(1);
				String name  = rs.getString(2);
				String designation = rs.getString(3);
				int salary = rs.getInt(4);

				pw.println("<tr>");

				pw.printf("<td>%d</td> <td>%s</td> <td>%s</td> <td>%d</td>", id, name, designation, salary);
				pw.println("<td> <a href=\"edit?id="+id+"\">edit</a>");
				pw.println("<a href=\"delete?id="+id+"\" >delete</a> </td>");

				pw.println("</tr>");
			}
			pw.println("</table>");

		} catch (SQLException e) {
			e.printStackTrace();
			pw.println("error: "+ e.getMessage());
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}