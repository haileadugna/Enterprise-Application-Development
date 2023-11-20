package com.itsc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final String query = "insert into books(bookname, bookedition, bookprice) values(?, ?, ?)";
@Override
	protected void doGet( HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("text/html");

		String bookName = req.getParameter("bookName");
		String bookEdition = req.getParameter("bookEdition");
		float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));

		PrintWriter pw = res.getWriter();
	
		try {
			Connection conn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/firstTryDb", "haile", "haile");
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, bookName);
			ps.setString(2, bookEdition);
			ps.setFloat(3, bookPrice);
			int count = ps.executeUpdate();
			if(count == 1) {
				pw.println("<h2> Book registered successfully.</h2");
			}else {
				pw.println("<h2> Book Not registered successfully.</h2");
			}
		
		}catch (SQLException se) {
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		doGet(req, resp);
	}
}