package com.littlepure;

public class BankAccount {
	// instance variables
	protected int accNo;
	protected String accName; 
	protected double balance;
	
	// constructors
	public BankAccount(int accNo, String accName) {
		this.accNo = accNo;
		this.accName = accName;
		this.balance = 0;
	}
	
	public BankAccount(String accName, int accNo) {
		this.accNo = accNo;
		this.accName = accName;
		this.balance = 0.0;
	}
	
	// accessors
	public int getAccNo() { return accNo; }
	public String getAccName() { return accName; }
	public double getBalance() { return balance; }
	
	//mutators
	public void setAccountName(String accName) {
		this.accName = accName;
	}
	
	// deposit method
	public void deposit(double amount) {
		balance = balance + amount;
	}
	
	public void deposit(double amount, boolean cheque) {
		if (cheque == false) {
			balance = balance + amount;
		}
		else {
			// code to be added
		}
	}
	
	// withdraw method
	public void withdraw(double amount) {
		if (check(amount)) {
			balance = balance - amount;
			System.out.println("Withdraw " + amount + " successfully.");
		}
	}
	
	// check method
	protected boolean check(double amount) {
		boolean allowed = false;
		if (balance - amount >= 0) {
			allowed = true;
		} else {
			System.out.println("Withdraw " + amount
					+ " unsuccessfully. Do not have enough available funds.");
		}
		return allowed;
	}
	
	//toString
	public String toString() {
		return "Account number:" + accNo + "\n" +
				"Account name:" + accName + "\n" +
				"Balance:" + balance;
	}
}
