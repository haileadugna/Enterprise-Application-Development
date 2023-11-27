package com.itsc;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.sql.DataSource;





@WebServlet("/AddEmployeeServlet")
public class AddEmployeeServlet extends HttpServlet {
	
	private static final String query = "INSERT INTO employees(name, designation, salary) VALUES(?, ?, ?)";
	
	@Resource(name = "jdbc/firstTryDb")
    private DataSource dataSource;

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");

        String name = req.getParameter("name");
        String designation = req.getParameter("designation");
        int salary = Integer.parseInt(req.getParameter("salary"));
        System.out.println(name);

        PrintWriter pw = res.getWriter();

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstTryDb", "haile", "haile");
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, designation);
            ps.setFloat(3, salary);
            System.out.println("success");
            int count = ps.executeUpdate();

            if (count == 1) {
                pw.println("<h2> Employee registered successfully.</h2");
            } else {
                pw.println("<h2> Employee Not registered successfully.</h2");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            pw.println("error: " + e.getMessage());
            System.out.println("not success");
        }
	}

}