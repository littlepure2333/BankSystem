package com.littlepure;

public class CurrentAccount extends BankAccount {
	// instance variables
	private double overdraftLimit;
	
	// constructors
	public CurrentAccount(int accNo, String accName) {
		super(accNo, accName);
		overdraftLimit = 500;
	}
	
	public CurrentAccount(String accName, int accNo) {
		super(accName, accNo);
		overdraftLimit = 500;
	}
	
	public CurrentAccount(int accNo, String accName, double overdraftLimit) {
		super(accNo, accName);
		setOverdraftLimit(overdraftLimit);
	}
	
	// accessors
	public double getOverdraftLimit() { return overdraftLimit; }
	
	// mutators
	public void setOverdraftLimit(double overdraftLimit) {
		this.overdraftLimit = overdraftLimit;
	}
	
	// check method
	protected boolean check(double amount) {
		boolean allowed = false;
		if (this.getBalance() - amount >= - overdraftLimit) {
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
				"Account name:" + name + "\n" +
				"Balance:" + balance + "\n" +
				"overdraftLimit:" + overdraftLimit;
	}
}
