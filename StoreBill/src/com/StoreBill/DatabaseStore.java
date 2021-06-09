package com.StoreBill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
	double grandTotal = 0;
	int rNo = 1;
	int rNum = 1;

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

	private void getDetails(int billNum, int code, double quantity, String mobile) {
		// System.out.println(code + " " + quantity);
		String name = "";
		double totalQty = 0;
		double sPrice = 0;
		double total = 0;
		String query = "select * from data where rNo =" + code + ";";
		// System.out.println(query);
		try {
			rs = st.executeQuery(query);
			while (rs.next()) {
				name = rs.getString(2);
				totalQty = rs.getDouble(3);
				// String bDate=rs.getString(4);
				// String eDate=rs.getString(5);
				sPrice = rs.getDouble(7);
				total = quantity * sPrice;
				grandTotal += total;
				// System.out.println(name + " " + totalQty + " " + sPrice + " " + total);
			}
			printBill(billNum, code, name, totalQty, quantity, sPrice, total, grandTotal, mobile);
		} catch (SQLException e) {
			System.out.println("SQL Exception in Get Details");
		}

	}

	private void printBill(int billNum, int code, String name, double totalQty, double quantity, double sPrice,
			double total, double grandTotal2, String mobile) {
		double balQty = totalQty - quantity;
		System.out.println(" " + rNo + "               " + name + "                " + quantity + "                "
				+ sPrice + "                " + total);
		System.out.println(
				"-----------------------------------------------------------------------------------------------");
		rNo++;
		qtyMaintanance(code, balQty);
		createNewBill(billNum, rNo, code, name, quantity, sPrice, total, mobile);
	}

	private void createNewBillTable(int billNum) {
		String query = "create table C" + billNum
				+ "(rNo int,code int,name varchar(45),qty double,price double,total double,mobile varchar(15)); ";
		System.out.println(query);
		try {
			st.execute(query);
		} catch (SQLException e) {
			System.out.println("SQL Exception in Create New Bill Table");
		}
	}

	private void createNewBill(int billNum, int rNo2, int code, String name, double quantity, double sPrice,
			double total, String mobile) {
		String query = "insert into C" + billNum + " values(" + rNum + "," + code + ",'" + name + "'," + quantity + ","
				+ sPrice + "," + total + "," + mobile + ");";
		System.out.println(query);
		try {
			st.execute(query);
			rNum++;
		} catch (SQLException e) {
			System.out.println("SQL Exception in Create New Bill");
		}
	}

	private void qtyMaintanance(int code, double balQty) {
		String query = "update data set qty =" + balQty + " where rNo=" + code + ";";
		// System.out.println(query);
		try {
			st.execute(query);
		} catch (SQLException e) {
			System.out.println("SQL Exception in QTY Maintanance");
		}
	}

	public void createBill() {
		sc = new Scanner(System.in);
		ArrayList list = new ArrayList();
		int code = 0;
		double quantity = 0;
		System.out.println("Enter Bill Number");
		int billNum = sc.nextInt();
		createNewBillTable(billNum);
		System.out.println("Enter Customer Mobile Number");
		String mobile = sc.next();
		System.out.println("Enter the total Quantity of Things");
		int qty = sc.nextInt();
		for (int i = 0; i < qty; i++) {
			System.out.println("Enter Product Code");
			code = sc.nextInt();
			list.add(code);
			System.out.println("Enter Product Quantity");
			quantity = sc.nextDouble();
			list.add(quantity);
		}
		// System.out.println(list);
		System.out.println(
				"\n Bill.No : " + billNum + "                                                Mobile : " + mobile);
		System.out.println("\n====================================================================================");
		System.out.println(" R.no            NAME                QTY               PRICE/1               TOTAL");
		System.out.println("====================================================================================");

		for (int j = 0; j < list.size(); j += 2) {
			int code1 = (int) list.get(j);
			double quantity1 = (double) list.get(j + 1);
			getDetails(billNum, code1, quantity1, mobile);
		}
		System.out.println(" ====> " + grandTotal);

	}

	public void viewAllOldBill() {
		String query = "show tables;";
		System.out.println(" ================");
		System.out.println("|  Bill Numbers  |");
		System.out.println(" ================");
		try {
			rs = st.executeQuery(query);
			while (rs.next()) {
				System.out.println("|     " + rs.getString(1) + "      |");
				System.out.println(" ----------------");
			}

		} catch (SQLException e) {
			System.out.println("SQL Exception in View All Old Bill");
		}

	}

	public void viewOldBill() {
		sc = new Scanner(System.in);
		double total = 0;
		System.out.println("Enter Bill Number To View Old Bill");
		int billNum = sc.nextInt();
		String mobile = getMobile(billNum);
		System.out.println(
				"\n Bill.No : " + billNum + "                                                Mobile : " + mobile);

		System.out.println("\n====================================================================================");
		System.out.println("R.no        CODE.NO          NAME            QTY           PRICE/1          TOTAL");
		System.out.println("====================================================================================");
		String query = "select * from C" + billNum + ";";
		try {
			rs = st.executeQuery(query);
			while (rs.next()) {
				for (int i = 1; i <= 6; i++) {
					System.out.print(rs.getString(i) + "            ");

				}
				System.out.println(
						"\n------------------------------------------------------------------------------------");
				total += rs.getDouble(6);
			}
			System.out.println("                                                                   TOTAL ==> " + total);
		} catch (SQLException e) {
			System.out.println("SQL Exception in Show All");
		}

	}

	private String getMobile(int billNum) {
		String query = "select * from C" + billNum + ";";
		String mobile = "";
		try {
			rs = st.executeQuery(query);
			while (rs.next()) {
				mobile = rs.getString(7);
			}
		} catch (SQLException e) {
			System.out.println("SQL Exception in Show All");
		}
		return mobile;
	}

	public void deleteOldBill() {
		sc = new Scanner(System.in);
		System.out.println("Enter Bill Number To remove Bill");
		int bNum = sc.nextInt();
		String query = "drop table C" + bNum + ";";
		try {
			st.execute(query);
			System.out.println("Bill Removed Successfully");
		} catch (SQLException e) {
			System.out.println("SQL Exception in QTY Maintanance");
		}

	}

	public static void main(String[] args) {
		DatabaseStore db = new DatabaseStore();
		// db.showAllStock();
		// db.addNewStock();
		// db.removeStock();
		// db.updateStock();
		// db.createBill();
		// db.getDetails(0, 0);
		// db.viewOldBill();
		db.deleteOldBill();
	}
}
