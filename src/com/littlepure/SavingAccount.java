package com.littlepure;

import java.util.Timer;
import java.util.TimerTask;

public class SavingAccount extends BankAccount{
	// constructors
	public SavingAccount(int accNo, String accName) {
		super(accNo, accName);
	}
	
	public SavingAccount(String accName,int accNo) {
		super(accName, accNo);
	}
	
	public void withdraw(double amount) {
		if (check(amount)) {
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				public void run() {}   
				}, 1500);  
			balance = balance - amount;
			System.out.println("Withdraw " + amount + " successfully.");
		}
	}
}
