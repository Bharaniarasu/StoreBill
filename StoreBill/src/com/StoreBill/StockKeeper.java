package com.StoreBill;

import java.util.Scanner;

public class StockKeeper {
Scanner sc;

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
		System.out.println("keeper logged in");
		
	}




public static void main(String[] args) {

}
}
