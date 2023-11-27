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
import java.sql.SQLException;

@WebServlet("/DeleteEmployeeServlet")
public class DeleteEmployeeServlet extends HttpServlet {

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
                 PreparedStatement ps = conn.prepareStatement("DELETE FROM employees WHERE id = ?");
            ) {
                ps.setInt(1, Integer.parseInt(employeeId));
                int count = ps.executeUpdate();

                if (count == 1) {
                    pw.println("<h2>Employee deleted successfully.</h2>");
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
