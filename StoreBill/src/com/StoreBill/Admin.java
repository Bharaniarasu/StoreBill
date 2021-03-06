package com.StoreBill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin {
	private String USER = "root";
	private String PASSWORD = "wolF";
	private String URL = "jdbc:mysql://localhost:3306/store";
	Connection conn;
	Statement st;
	ResultSet rs;
	Scanner sc;
	private String userGet;
	private String passwordGet;

	Admin() {
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

	public void storeName() {
		System.out.println(
				"\n\n**************************************************************************************************");
		System.out.println("                                       ALL IN ALL STORES");
		System.out.println(
				"**************************************************************************************************\n\n");

	}

	private void getUserDetail(String user) {
		String userName = user;
		String query = "select * from user where roll = '" + userName + "';";
		try {
			rs = st.executeQuery(query);
			while (rs.next()) {
				userGet = rs.getString(2);
				passwordGet = rs.getString(3);
			}
		} catch (SQLException e) {
			System.out.println("SQL Exception in Get User Detail");
		}
	}

	private void adminLogin() {
		sc = new Scanner(System.in);
		String roll = "admin";
		getUserDetail(roll);
		System.out.println("User     : " + userGet);
		System.out.println("Password : " + passwordGet);
		System.out.println("Enter Admin User Name");
		String userName = sc.next();
		System.out.println("Enter Admin Password");
		String passWord = sc.next();
		if (userName.equals(userGet) && passWord.equals(passwordGet)) {
			loginOperations();
		} else {
			System.out.println("Invalid User Name or Password");
			adminLogin();
		}
	}

	public void loginOperations() {
		Biller bl = new Biller();
		StockKeeper sk = new StockKeeper();
		sc = new Scanner(System.in);
		System.out.println("\n\nSelect 1 : To Admin \nSelect 2 : To Biller \nSelect 3 : To Stock_Keeper\n");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			adminOperations();
			break;
		case 2:
			getUserDetail("biller");
			bl.billerLogin(userGet, passwordGet);
			break;
		case 3:
			getUserDetail("keeper");
			sk.keeperLogin(userGet, passwordGet);
			break;
		case 0:
			loginOperations();
			break;
		default:
			System.out.println("\n----------------------------------Enter A Valid Key------------------------------\n");
			loginOperations();
		}
	}

	private void adminOperations() {
		sc = new Scanner(System.in);
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("                               WELCOME TO ADMIN PORTAL");
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("\n\nSelect 1 : To View all User");
		System.out.println("Select 2 : To Add an User");
		System.out.println("Select 3 : To Remove an User\n");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			viewUsers();
			adminOperations();
			break;
		case 2:
			addUser();
			adminOperations();
			break;
		case 3:
			removeUser();
			adminOperations();
			break;
		case 0:
			loginOperations();
			break;
		default:
			System.out.println("\n----------------------------------Enter A Valid Key------------------------------\n");
			adminOperations();
		}
	}

	private void removeUser() {
		System.out.println("\n\nselect 1 : To Remove User in Biller\nselect 2 : To Remove User in Keeper\n");
		int choice2 = sc.nextInt();
		switch (choice2) {
		case 1:
			removeUserName("biller");
			break;
		case 2:
			removeUserName("keeper");
			break;
		case 0:
			loginOperations();
			break;
		default:
			System.out.println("\n----------------------------------Enter A Valid Key------------------------------\n");
			removeUser();
		}
	}

	private void removeUserName(String rollName) {
		String roll = rollName;
		String query = "update user set name = " + null + ",password = " + null + " where roll = '" + roll + "';";
		try {
			st.execute(query);
			System.out.println(
					"\n--------------------------------User Removed Successfully----------------------------\n");
		} catch (SQLException e) {
			System.out.println("SQL Exception in Remove UserName");
		}
	}

	public void addUser() {
		sc = new Scanner(System.in);
		System.out.println("\n\nselect 1 : To Add user in Biller\nselect 2 : To Add user in Keeper\n");
		int choice1 = sc.nextInt();
		switch (choice1) {
		case 1:
			addUserName("biller");
			break;
		case 2:
			addUserName("keeper");
			break;
		case 0:
			loginOperations();
			break;
		default:
			System.out.println("\n----------------------------------Enter A Valid Key------------------------------\n");
			adminOperations();
		}
	}

	private void addUserName(String rollName) {
		String roll = rollName;
		sc = new Scanner(System.in);
		System.out.println("Enter New User Name");
		String newUser = sc.next();
		String query = "update user set name = '" + newUser + "' where roll = '" + roll + "';";
		try {
			st.execute(query);
		} catch (SQLException e) {
			System.out.println("SQL Exception in Add User Name");
		} catch (InputMismatchException ime) {
			System.out
					.println("\n----------------------------------Enter A Valid Input------------------------------\n");
			addUserName(roll);
		}
		addUserPassword(roll);
	}

	private void addUserPassword(String roll) {
		String rollName = roll;
		sc = new Scanner(System.in);
		System.out.println("Enter New User Password");
		String newPassword = sc.next();
		String query = "update user set password = '" + newPassword + "' where roll = '" + rollName + "';";
		try {
			st.execute(query);
			System.out
					.println("\n--------------------------------User Added Successfully----------------------------\n");
		} catch (SQLException e) {
			System.out.println("SQL Exception in Add User Password");
		} catch (InputMismatchException ime) {
			System.out
					.println("\n----------------------------------Enter A Valid Input------------------------------\n");
			addUserPassword(roll);
		}
	}

	private void viewUsers() {
		String query = "select * from user;";
		System.out.println(query);
		try {
			rs = st.executeQuery(query);
			System.out.println(
					"======================================================\nROLL                USER              PASSWORD \n======================================================");
			while (rs.next()) {
				System.out.print(rs.getString(1) + "              ");
				System.out.print(rs.getString(2) + "              ");
				System.out.println(rs.getString(3) + "\n------------------------------------------------------");
			}
			System.out.println("\n");
		} catch (SQLException e) {
			System.out.println("SQL Exception in View User");
		}
	}

	public static void main(String[] args) {
		Admin ad = new Admin();
		ad.storeName();
		ad.adminLogin();

	}

}
