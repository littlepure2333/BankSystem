package com.littlepure.models;

public class CurrentAccount extends BankAccount {

	public CurrentAccount() {

	}

	/**
	 * 子类必须override构造器，因为账号类型决定limit
	 *
	 * @param name
	 * @param address
	 * @param DOB
	 */
	public CurrentAccount(String name, String address, String DOB) {
		super(name, address, DOB);
		this.limit = 2000;
	}


}
