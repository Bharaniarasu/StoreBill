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
	DetailGetSet dgs;
	Admin ad;

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

	public void showAllStock() {
		String query = "select * from data ;";
		try {
			rs = st.executeQuery(query);
			while (rs.next()) {
				for (int i = 1; i <= 7; i++) {
					System.out.print(rs.getString(i) + "         ");
				}
				System.out.println("\n");
			}

		} catch (SQLException e) {
			System.out.println("SQL Exception in Show All");
		}
	}

	public void addNewStock() {
		dgs = new DetailGetSet();
		String query = "insert into data values(0,'" + dgs.getName() + "'," + dgs.getQty() + ",'" + dgs.getBuyingDate()
				+ "','" + dgs.getSellingDate() + "'," + dgs.getBuyingPrice() + "," + dgs.getSellingPrice() + ");";
		System.out.println(query);
		try {
			st.execute(query);
		} catch (SQLException e) {
			System.out.println("SQL Exception in Add New Stock");
		}
	}

	public void updateStock() {
		sc = new Scanner(System.in);
		ad = new Admin();
		String query = "";
		System.out.println("Enter Product code to Update a Data");
		int roll = sc.nextInt();
		System.out.println("Select 1 : To Update Product Name");
		System.out.println("Select 2 : To Update Product Quantity");
		System.out.println("Select 3 : To Update Product Buying_Date");
		System.out.println("Select 4 : To Update Product Expiry_Date");
		System.out.println("Select 5 : To Update Product Buying_Price");
		System.out.println("Select 6 : To Update Product Selling_Price");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			System.out.println("Enter new Name");
			String name = sc.next();
			query = "update data set name ='" + name + "' where rNo=" + roll + ";";
			break;
		case 2:
			System.out.println("Enter new Quantity");
			double qty = sc.nextDouble();
			query = "update data set qty =" + qty + " where rNo=" + roll + ";";
			break;
		case 3:
			System.out.println("Enter new Buying_Date(DD/MM/YYYY\")");
			String bDate = sc.next();
			query = "update data set buying_date ='" + bDate + "' where rNo=" + roll + ";";
			break;
		case 4:
			System.out.println("Enter Expiry_Date(DD/MM/YYYY\")");
			String eDate = sc.next();
			query = "update data set exp_date ='" + eDate + "' where rNo=" + roll + ";";
			break;
		case 5:
			System.out.println("Enter Buying_Price");
			double bPrice = sc.nextDouble();
			query = "update data set buying_price =" + bPrice + " where rNo=" + roll + ";";
			break;
		case 6:
			System.out.println("Selling_Price");
			double sPrice = sc.nextDouble();
			query = "update data set selling_price =" + sPrice + " where rNo=" + roll + ";";
			break;
		case 0:
			ad.loginOperations();
			break;
		default:
			System.out.println("Enter a valid Key");
			updateStock();
		}
		try {
			System.out.println(query);
			st.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("SQL Exception in Remove Stock");
		}
	}

	public void removeStock() {
		sc = new Scanner(System.in);
		System.out.println("Enter Product Number To Remove Stock Data");
		int roll = sc.nextInt();
		String query = "delete from data where rNo =" + roll + ";";
		System.out.println(query);
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("SQL Exception in Remove Stock");
		}
	}

	public static void main(String[] args) {
		DatabaseStore db = new DatabaseStore();
		db.showAllStock();
		// db.addNewStock();
		// db.removeStock();
		// db.updateStock();
	}
}
