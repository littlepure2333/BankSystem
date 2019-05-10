package com.littlepure;

public class BankAccountTest {
	public static void main(String[] args) {
		BankAccount acc1 = new BankAccount(23142635, "John Smith");
		System.out.println(acc1);
		acc1.deposit(500);
		acc1.withdraw(100);
		System.out.println(acc1);
		
		BankAccount acc2 = new BankAccount("John Smith", 38472638);
		System.out.println(acc2);
		acc2.deposit(3000);
		acc2.withdraw(400);
		System.out.println(acc2);
		
		CurrentAccount acc3 = new CurrentAccount("Michael Chai", 12345678);
		System.out.println(acc3);
		acc3.deposit(300);
		acc3.withdraw(100);
		System.out.println(acc3);
		acc3.withdraw(500);
		System.out.println(acc3);
		acc3.withdraw(100);
		System.out.println(acc3);
		acc3.withdraw(400);
		System.out.println(acc3);
		
		
	}
}
