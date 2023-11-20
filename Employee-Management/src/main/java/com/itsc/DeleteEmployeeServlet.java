package com.itsc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/delete")
public class DeleteEmployeeServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = "DELETE FROM employees WHERE id = ?;";
		PrintWriter pw = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			Connection connection = DBConnection.connect();
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ps.execute();
			response.sendRedirect("view");
			
		} catch (SQLException e) {
			e.printStackTrace();
			pw.println("error: "+ e.getMessage());
			e.printStackTrace();
		}
	}

}