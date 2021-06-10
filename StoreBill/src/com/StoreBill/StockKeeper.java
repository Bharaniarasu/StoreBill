package com.StoreBill;

import java.util.Scanner;

public class StockKeeper {
	Scanner sc;
	DatabaseStore db;
	Admin ad;
	DetailGetSet dgs;

	public void keeperLogin(String user, String password) {
		sc = new Scanner(System.in);
		String userGet = user;
		String passwordGet = password;
		System.out.println("Enter Keeper User Name");
		String userName = sc.next();
		System.out.println("Enter Keeper Password");
		String passWord = sc.next();
		if (userName.equals(userGet) && passWord.equals(passwordGet)) {
			keeperOperations();
		} else {
			System.out.println("Invalid User Name or Password");
			keeperLogin(userGet, passwordGet);
		}
	}

	private void keeperOperations() {
		sc = new Scanner(System.in);
		db = new DatabaseStore();
		ad = new Admin();
		System.out.println("Select 1 : To View All Stocks");
		System.out.println("Select 2 : To Add  New Stock");
		System.out.println("Select 3 : To Update a Stock");
		System.out.println("Select 4 : To Remove a Stock");
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
			System.out.println("Enter a valid Key");
			keeperOperations();
		}
	}
}
