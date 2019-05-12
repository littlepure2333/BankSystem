package com.littlepure;

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

	public BankAccount() {

	}

	/**
	 * 子类必须override构造器，因为账号类型决定limit
	 * @param name
	 * @param address
	 * @param DOB
	 */
	BankAccount(String name, String address, String DOB) {
		this.accNo = Bank.generateNo();
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

    /**
     * 对账户进行操作之前必须检查账户有没有被停用
     * 如果被停用了那么不能对这个账户进行其他操作除非重新激活账户
     * @return true/false
     */
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

	public void setAccNo(long accNo) {
		this.accNo = accNo;
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
