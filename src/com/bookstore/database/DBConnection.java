package com.bookstore.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	private static Connection conn = null;
	private static DBConfiguration config = new DBConfiguration();
	
	public DBConnection() {}
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					config.getUrl(),
					config.getUsername(),
					config.getPassowrd()
					);
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return conn;
	}
	
	@Override
	public String toString() {
		return "ConnectionDB Class";
	}
}
