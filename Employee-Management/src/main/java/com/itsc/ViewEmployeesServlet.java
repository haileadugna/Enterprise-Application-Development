package com.itsc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/ViewEmployeesServlet")
public class ViewEmployeesServlet extends HttpServlet {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/firstTryDb";
    private static final String JDBC_USER = "haile";
    private static final String JDBC_PASSWORD = "haile";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM employees");
             ResultSet rs = ps.executeQuery()
        ) {
            pw.println("<html><body><h2>Employee List</h2>");
            pw.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Designation</th><th>Salary</th><th>Edit</th><th>Delete</th></tr>");

            while (rs.next()) {
                pw.println("<tr>");
                pw.println("<td>" + rs.getInt("id") + "</td>");
                pw.println("<td>" + rs.getString("name") + "</td>");
                pw.println("<td>" + rs.getString("designation") + "</td>");
                pw.println("<td>" + rs.getFloat("salary") + "</td>");
                pw.println("<td><a href='EditEmployeeServlet?id=" + rs.getInt("id") + "'>Edit</a></td>");
                pw.println("<td><a href='DeleteEmployeeServlet?id=" + rs.getInt("id") + "'>Delete</a></td>");
                pw.println("</tr>");
            }

            pw.println("</table></body></html>");

        } catch (SQLException e) {
            e.printStackTrace();
            pw.println("Error: " + e.getMessage());
        }
    }
}
