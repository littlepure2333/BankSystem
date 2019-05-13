package com.littlepure.models;

public class CurrentAccount extends BankAccount {

	public CurrentAccount() {

	}

	/**
	 * Create a current account
	 * @param name name of customer
	 * @param address address of customer
	 * @param DOB date of birth
	 */
	public CurrentAccount(String name, String address, String DOB) {
		super(name, address, DOB);
		this.limit = 2000;
	}


}
