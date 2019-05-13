package com.littlepure.models;

public class JuniorAccount extends BankAccount{

    public JuniorAccount() {

    }

    /**
     * Create a junior account
     * @param name -name of customer
     * @param address -address of customer
     * @param DOB d-date of birth
     */
    public JuniorAccount(String name, String address, String DOB) {
        super(name, address, DOB);
        this.limit = 0;
    }


}
