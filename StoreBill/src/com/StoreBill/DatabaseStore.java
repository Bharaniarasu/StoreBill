package com.StoreBill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DatabaseStore {
	private String USER = "root";
	private String PASSWORD = "wolF";
	private String URL = "jdbc:mysql://localhost:3306/store";
	Connection conn;
	Statement st;
	ResultSet rs;
	Scanner sc;

	DatabaseStore() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			st = conn.createStatement();
		} catch (SQLException e) {
			System.out.println("SQL Exception in Admin");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not Found in Admin");
		}
	}

	public static void main(String[] args) {

	}

}
