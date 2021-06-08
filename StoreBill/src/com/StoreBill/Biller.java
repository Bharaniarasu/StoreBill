package com.StoreBill;

import java.util.Scanner;

public class Biller {
	Scanner sc;

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
		System.out.println("BIller logged in");
	}

	public static void main(String[] args) {

	}
}
