package com.itsc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	private static final String query = "update books set bookname=?, bookedition=?, bookprice=? where id = ?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		//get PrintWriter
		PrintWriter pw = resp.getWriter();
		//set content type
		resp.setContentType("text/html");
		// get the id of record
		int id = Integer.parseInt(req.getParameter("id"));
		//get the edited data
		String bookName = req.getParameter("bookName");
		String bookEdition = req.getParameter("bookEdition");
		float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));

		//load the jdbc driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		
		//generate the connection
		try {
			Connection conn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/firstTryDb", "haile", "haile");

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, bookName);
			ps.setString(2, bookEdition);
			ps.setFloat(3, bookPrice);
			ps.setInt(4, id);
			int count = ps.executeUpdate();
			if(count == 1) {
				pw.println("<h2>Record is edited successfully.</h2>");
			}else {
				pw.println("<h2>Record not edited.</h2>");
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
			pw.println("<h1>" + se.getMessage() + "</h1>");
		} catch (Exception e) {
			e.printStackTrace();
			pw.println("<h1>" + e.getMessage() + "</h1>");
		}
		pw.println("<a href='home.html'>Home</a>");
		pw.print("<br>");
		pw.println("<a href='booklist'>Book List</a>");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
			