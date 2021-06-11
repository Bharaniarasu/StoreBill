package com.StoreBill;

import java.util.Scanner;

public class StockKeeper {
	Scanner sc;
	DatabaseStore db;
	Admin ad;
	DetailGetSet dgs;

	public void keeperLogin(String user, String password) {
		try {
			sc = new Scanner(System.in);
			ad = new Admin();
			ad.storeName();
			String userGet = user;
			String passwordGet = password;
			if (userGet == null && passwordGet == null) {
				System.out
						.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Please Add an User >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				ad.addUser();
			} else {
				System.out.println("User     : " + userGet);
				System.out.println("Password : " + passwordGet);
				System.out.println("Enter Keeper User Name");
				String userName = sc.next();
				System.out.println("Enter Keeper Password");
				String passWord = sc.next();
				if (userName.equals(userGet) && passWord.equals(passwordGet)) {
					keeperOperations();
				} else {
					System.out.println(
							"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Invalid User Name or Password >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					keeperLogin(userGet, passwordGet);
				}
			}
		} catch (NullPointerException npe) {
			System.out.println(
					"\n----------------------------------Please Create A Valid User------------------------------\n");
			keeperLogin(user, password);
		}
	}

	private void keeperOperations() {
		sc = new Scanner(System.in);
		db = new DatabaseStore();
		ad = new Admin();
		System.out.println("\n\nSelect 1 : To View All Stocks");
		System.out.println("Select 2 : To Add  New Stock");
		System.out.println("Select 3 : To Update a Stock");
		System.out.println("Select 4 : To Remove a Stock\n");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			db.showAllStock();
			keeperOperations();
			break;
		case 2:
			db.addNewStock();
			keeperOperations();
			break;
		case 3:
			db.updateStock();
			keeperOperations();
			break;
		case 4:
			db.removeStock();
			keeperOperations();
			break;
		case 0:
			ad.loginOperations();
			break;
		default:
			System.out.println("\n----------------------------------Enter A Valid Key------------------------------\n");
			keeperOperations();
		}
	}
}
