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

@WebServlet("/EditEmployeeServlet")
public class EditEmployeeServlet extends HttpServlet {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/firstTryDb";
    private static final String JDBC_USER = "haile";
    private static final String JDBC_PASSWORD = "haile";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        String employeeId = req.getParameter("id");

        if (employeeId != null && !employeeId.isEmpty()) {
            try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
                 PreparedStatement ps = conn.prepareStatement("SELECT * FROM employees WHERE id = ?");
            ) {
                ps.setInt(1, Integer.parseInt(employeeId));
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    // Display the form to edit the employee
                    pw.println("<html><body><h2>Edit Employee</h2>");
                    pw.println("<form action='UpdateEmployeeServlet' method='post'>");
                    pw.println("ID: <input type='text' name='id' value='" + rs.getInt("id") + "' readonly><br>");
                    pw.println("Name: <input type='text' name='name' value='" + rs.getString("name") + "'><br>");
                    pw.println("Designation: <input type='text' name='designation' value='" + rs.getString("designation") + "'><br>");
                    pw.println("Salary: <input type='text' name='salary' value='" + rs.getFloat("salary") + "'><br>");
                    pw.println("<input type='submit' value='Update'>");
                    pw.println("</form></body></html>");
                } else {
                    pw.println("<h2>Employee not found with ID: " + employeeId + "</h2>");
                }

            } catch (SQLException e) {
                e.printStackTrace();
                pw.println("Error: " + e.getMessage());
            }
        } else {
            pw.println("<h2>Invalid Employee ID</h2>");
        }
    }
}
