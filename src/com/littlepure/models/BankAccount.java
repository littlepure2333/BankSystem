package com.littlepure.models;

import com.littlepure.util.numberUtil;

public abstract class BankAccount {
	// instance variables
	public long accNo;
	public String name;
	public String address;
	public String DOB;
	public double balance;
	public double unclearedFunds;
	public double limit;
	public int PIN;
	public boolean suspended;

	public static final int WITHDRAW_SUCCESS = 0;
	public static final int EXCEED_OVERDRAFT_LIMIT = 1;

	BankAccount() {

	}

	/**
	 * Create a Bank account, need to be overridden by subclass
	 * @param name -name of customer
	 * @param address -address of customer
	 * @param DOB -date of birthday
	 */
	BankAccount(String name, String address, String DOB) {
		this.accNo = numberUtil.generateNo();
		this.name = name;
		this.address = address;
		this.DOB = DOB;
		this.balance = 0;
		this.unclearedFunds = 0;
		this.suspended = false;
	}

	// getters
	public long getAccNo() {
		return this.accNo;
	}

	public String getName() {
		return this.name;
	}

	public String getAddress() {
		return this.address;
	}

	public String getDOB() {
		return this.DOB;
	}

	public double getBalance() {
		return this.balance;
	}

	public double getUnclearedFunds() {
		return this.unclearedFunds;
	}

	public double getLimit() {
		return this.limit;
	}

	public int getPIN() {
		return this.PIN;
	}

	public boolean getSuspended() {
		return this.suspended;
	}

	// setters
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setUnclearedFunds(double unclearedFunds) {
		this.unclearedFunds = unclearedFunds;
	}

	public void setPIN(int PIN) {
		this.PIN = PIN;
	}

	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}


	/**
	 * Deposit
	 * @param amount -amount to deposit
	 * @param cleared -cash or cheque
	 */
	public void deposit(double amount, boolean cleared) {
		if (cleared) {
			setBalance(this.getBalance() + amount);
		}
		else {
			setUnclearedFunds(this.getUnclearedFunds() + amount);
		}
	}

	/**
	 * Clear uncleared funds
	 */
	public void clearFunds() {
		setBalance(getBalance() + getUnclearedFunds());
		setUnclearedFunds(0);
	}

	/**
	 * Withdraw and feedback result of withdrawal
	 * @param amount -amount to withdraw
	 * @return result of withdrawal
	 */
	public int withdraw(double amount) {
		if (getBalance() - amount >= -limit) {
			setBalance(getBalance() - amount);
			return WITHDRAW_SUCCESS;
		}
		else {
			return EXCEED_OVERDRAFT_LIMIT;
		}
	}

	/**
	 * Check if PIN is correct
	 * @param PIN -personal identification number
	 * @return {@code true}/{@code false} correct or incorrect
	 */
	public boolean identify(int PIN) {
		return getPIN() == PIN;
	}

	/**
	 * Suspend account.
	 * And no further transactions may occur until the account is re-instated.
	 */
	public void suspend() {
		setSuspended(true);
	}

	/**
	 * Reinstate account, to allow account to continue transactions.
	 */
	public void reinstate() {
		setSuspended(false);
	}
}
