package com.itsc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet {
    private static final String query ="select bookname, bookedition, bookprice, id from books where id = ?";

    @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{

    //get PrintWriter
    PrintWriter pw = resp.getWriter();
    //set content type
    resp.setContentType("text/html");
    // get the id of record
    int id = Integer.parseInt(req.getParameter("id"));
    //load the jdbc driver

    //generate the connection
    try {

		Connection conn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/firstTryDb", "haile", "haile");

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        pw.println("<form action=\"edit\" method=\"post\">");
        pw.println("<table>");
        pw.println("<tr>");
        pw.println("<td>Book Name</td>");
        pw.println("<td><input type = 'text', name = 'bookName', value = '" + rs.getString(1)+"'</td>");
        pw.println("<td><input hidden type ='number', name = 'id', value = '" + rs.getString(4)+"'</td>");

        pw.println("</tr>");
        pw.println("<tr>");
        pw.println("<td>Book Edition</td>");
        pw.println("<td><input type = 'text', name = 'bookEdition', value = '" + rs.getString(2)+"'</td>");

        pw.println("</tr>");
        pw.println("<tr>");
        pw.println("<td>Book Price</td>");
        pw.println("<td><input type = 'text', name = 'bookPrice', value = '" + rs.getFloat(3)+"'</td>");

        pw.println("</tr>");
        pw.println("</table>");
        pw.println("<button>save</button>");
        pw.println("</form>");

        } catch (SQLException se) {
            se.printStackTrace();
            pw.println("<h1>" + se.getMessage() + "</h1>");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h1>" + e.getMessage() + "</h1>");
        }
        pw.println("<a href='home.html'>Home</a>");
    }
    @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
            doGet(req, resp);
        }
    }