import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/firstTryDb";
    private static final String DB_USER = "haile";
    private static final String DB_PASSWORD = "haile";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Retrieve form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {

                // Prepare the SQL query
                String query = "INSERT INTO users(name, email, password) VALUES (?, ?, ?)";

                // Create a prepared statement
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                    // Set the query parameters
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, email);
                    preparedStatement.setString(3, password);

                    // Execute the update query
                    preparedStatement.executeUpdate();

                    // Close the database connection
                    connection.close();
                }
            }

            // Redirect to the confirmation page
            response.sendRedirect("confirmation.jsp");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
