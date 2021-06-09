package com.StoreBill;

import java.util.Scanner;

public class Biller {
	Scanner sc;
	DatabaseStore db;
	Admin ad;

	public void billerLogin(String user, String password) {
		sc = new Scanner(System.in);
		String userGet = user;
		String passwordGet = password;
		System.out.println("Enter Biller User Name");
		String userName = sc.next();
		System.out.println("Enter Biller Password");
		String passWord = sc.next();
		if (userName.equals(userGet) && passWord.equals(passwordGet)) {
			billerOperations();
		} else {
			System.out.println("Invalid User Name or Password");
			billerLogin(userGet, passwordGet);
		}

	}

	private void billerOperations() {
		sc = new Scanner(System.in);
		db = new DatabaseStore();
		ad = new Admin();
		System.out.println("Select 1 : Create Bill");
		System.out.println("Select 2 : View All Bill Numbers");
		System.out.println("Select 3 : View an Old Bill");
		System.out.println("Select 4 : Delete an Old Bill");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			db.createBill();
			break;
		case 2:
			db.viewAllOldBill();
			break;
		case 3:
			db.viewOldBill();
			break;
		case 4:
			db.deleteOldBill();
			break;
		case 0:
			ad.loginOperations();
			break;
		default:
		}
	}

	public static void main(String[] args) {

	}
}
