package com.itsc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	static Connection connect() throws SQLException{

		String url = "jdbc:mysql://localhost:3306/Employees";
		String userName = "haile";
		String password = "haile";
		
		return DriverManager.getConnection(url, userName, password);
	}

}