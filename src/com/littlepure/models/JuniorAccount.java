package com.littlepure.models;

public class JuniorAccount extends BankAccount{

    public JuniorAccount() {

    }

    /**
     * 子类必须override构造器，因为账号类型决定limit
     *
     * @param name
     * @param address
     * @param DOB
     */
    public JuniorAccount(String name, String address, String DOB) {
        super(name, address, DOB);
        this.limit = 0;
    }


}
