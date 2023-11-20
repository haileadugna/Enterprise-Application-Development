package com.itsc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/edit")
public class EditEmployeeServlet extends HttpServlet {

      	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String query = "SELECT * from employees where id=?;";
      		PrintWriter pw = response.getWriter();
		try {
			Connection connection = DBConnection.connect();
			PreparedStatement ps = connection.prepareStatement(query);
			int id = Integer.parseInt(request.getParameter("id"));

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			pw.println("<h1>Employee</h1>");
			pw.println("<form method=\"POST\">");

			String name  = rs.getString(2);
			String designation = rs.getString(3);
			int salary = rs.getInt(4);

			pw.println(" <input hidden type=\"number\" name=\"id\" value=\""+id+"\"/> <br/>");
			pw.println("Name: <input type=\"text\" name=\"name\" value=\""+name+"\"/> <br/>");
			pw.println("Designation: <input type=\"text\" name=\"designation\" value=\""+designation+"\"/> <br/>");
			pw.println("Salary: <input type=\"number\" name=\"salary\" value=\""+salary+"\"/> <br/>");

			pw.println("<button>Save</button>");
			pw.println("</form>");

		} catch (SQLException e) {
			e.printStackTrace();
			pw.println("error: "+ e.getMessage());
		}

	  }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String query = "UPDATE employees set name=?, designation=?, salary=? WHERE id=?";
			PrintWriter pw = response.getWriter();

			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String designation = request.getParameter("designation");
			int salary = Integer.parseInt(request.getParameter("salary"));

			
			try {
				Connection connection = DBConnection.connect();
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, name);
				ps.setString(2, designation);
				ps.setInt(3, salary);
				ps.setInt(4, id);
				
				ps.execute();
				response.sendRedirect("view");
				
			} catch (SQLException e) {
				e.printStackTrace();
				pw.println("error: "+ e.getMessage());
				e.printStackTrace();
			}
	}

}