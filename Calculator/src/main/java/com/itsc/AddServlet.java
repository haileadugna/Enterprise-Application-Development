package com.itsc;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        handleRequest(req, resp);
    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Set the content type of the response
        resp.setContentType("text/html");

        // Retrieve values of num1, sign, and num2 from the request
        String num1Str = req.getParameter("num1");
        String sign = req.getParameter("sign");
        String num2Str = req.getParameter("num2");

        try {
            // Convert the parameters to integers
            int n1 = Integer.parseInt(num1Str);
            int n2 = Integer.parseInt(num2Str);

            // Perform the calculation based on the sign
            int result = 0;
            switch (sign) {
                case "+":
                    result = n1 + n2;
                    break;
                case "-":
                    result = n1 - n2;
                    break;
                case "*":
                    result = n1 * n2;
                    break;
                case "/":
                    result = n1 / n2;
                    break;
            }

            // Get the PrintWriter object to write the response
            PrintWriter out = resp.getWriter();

            // Write the result as an HTML response
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>Calculator</title>");
            out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"container mt-5\">");
            out.println("<h2>Result of Calculation:</h2>");
            out.println("<p>" + n1 + " " + sign + " " + n2 + " = " + result + "</p>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

        } catch (NumberFormatException | ArithmeticException e) {
            // Handle the case where the parameters are not valid or arithmetic exception occurs
            resp.getWriter().println("<html><body><h2>Error: Please provide valid numbers and ensure no division by zero.</h2></body></html>");
        }
    }
}
