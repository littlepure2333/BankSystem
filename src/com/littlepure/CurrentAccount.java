package com.littlepure;

public class CurrentAccount extends BankAccount {

	/**
	 * 子类必须override构造器，因为账号类型决定limit
	 *
	 * @param name
	 * @param address
	 * @param DOB
	 */
	CurrentAccount(String name, String address, String DOB) {
		super(name, address, DOB);
		this.limit = 2000;
	}


}
