package com.littlepure;

public abstract class BankAccount {
	// instance variables
	int accNo;
	String name;
	String address;
	String DOB;
	double balance;
	double unclearedFunds;
	double limit;
	private int PIN;
	private boolean suspended;

	public static final int WITHDRAW_SUCCESS = 0;
	public static final int EXCEED_OVERDRAFT_LIMIT = 1;

	/**
	 * 子类必须override构造器，因为账号类型决定limit
	 * @param name
	 * @param address
	 * @param DOB
	 */
	BankAccount(String name, String address, String DOB) {
		//todo 生成账号
		this.name = name;
		this.address = address;
		this.DOB = DOB;
		this.balance = 0;
		this.unclearedFunds = 0;
		this.suspended = false;
	}

	/**
	 * getters
	 */
	public int getAccNo() {
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

	/**
	 * setters
	 */
	void setBalance(double balance) {
		this.balance = balance;
	}

	void setUnclearedFunds(double unclearedFunds) {
		this.unclearedFunds = unclearedFunds;
	}

	void setPIN(int PIN) {
		this.PIN = PIN;
	}

	void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

	// deposit method
//	public void deposit(double amount) {
//		setBalance(this.getBalance() + amount);
//	}
	
	public void deposit(double amount, boolean cleared) {
		if (cleared == true) {
			setBalance(this.getBalance() + amount);
		}
		else {
			setUnclearedFunds(this.getUnclearedFunds() + amount);
		}
	}

	/**
	 * clear funds
	 * 等着外部清理系统调用
	 */
	public void clearFunds() {
		setBalance(getBalance() + getUnclearedFunds());
		setUnclearedFunds(0);
	}

	// withdraw method
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
	 * 验证PIN是否正确
	 * @param PIN
	 * @return 正确与否
	 */
	protected boolean identify(int PIN) {
		if(getPIN() == PIN) {
			return true;
		}
		else {
			return false;
		}
	}

	public void suspend() {
		setSuspended(true);
	}

	public void reinstate() {
		setSuspended(false);
	}
}
