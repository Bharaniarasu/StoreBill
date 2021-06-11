package com.StoreBill;

import java.util.Scanner;

public class Biller {
	Scanner sc;
	DatabaseStore db;
	Admin ad;

	public void billerLogin(String user, String password) {
		ad = new Admin();
		ad.storeName();
		sc = new Scanner(System.in);
		String userGet = user;
		String passwordGet = password;
		if (userGet == null && passwordGet == null) {
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Please Add an User >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			ad.addUser();
		} else {
			System.out.println("User     : " + userGet);
			System.out.println("Password : " + passwordGet);
			System.out.println("Enter Biller User Name");
			String userName = sc.next();
			System.out.println("Enter Biller Password");
			String passWord = sc.next();
			if (userName.equals(userGet) && passWord.equals(passwordGet)) {
				billerOperations();
			} else {
				System.out.println(
						"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Invalid User Name or Password >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				billerLogin(userGet, passwordGet);
			}
		}

	}

	private void billerOperations() {
		sc = new Scanner(System.in);
		db = new DatabaseStore();
		ad = new Admin();
		System.out.println("\n\nSelect 1 : Create Bill");
		System.out.println("Select 2 : View All Bill Numbers");
		System.out.println("Select 3 : View an Old Bill");
		System.out.println("Select 4 : Delete an Old Bill\n");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			db.createBill();
			billerOperations();
			break;
		case 2:
			db.viewAllOldBill();
			billerOperations();
			break;
		case 3:
			db.viewOldBill();
			billerOperations();
			break;
		case 4:
			db.deleteOldBill();
			billerOperations();
			break;
		case 0:
			ad.loginOperations();
			break;
		default:
			System.out.println("\n----------------------------------Enter A Valid Key------------------------------\n");
			billerOperations();
		}
	}
}
