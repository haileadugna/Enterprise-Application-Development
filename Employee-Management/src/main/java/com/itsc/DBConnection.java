package com.itsc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	public static void main(String[] args){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	
			String url = "jdbc:mysql://localhost:3306/firstTryDb";
			String userName = "haile";
			String password = "haile";
			
			try (Connection connection = DriverManager.getConnection(url, userName, password);
	             Statement statement = connection.createStatement()) {
	
	            // Create table query
	            String createTableSQL = "CREATE TABLE IF NOT EXISTS employees ("
	                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
	                    + "name VARCHAR(255),"
	                    + "designation VARCHAR(255),"
	                    + "salary DECIMAL(10, 2))";
	
	            // Execute the query to create the table
	            statement.executeUpdate(createTableSQL);
	
	            System.out.println("Table 'employees' created successfully.");
	
			}catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
		
	}

}