package com.littlepure;

public class JuniorAccount extends BankAccount{


    /**
     * 子类必须override构造器，因为账号类型决定limit
     *
     * @param name
     * @param address
     * @param DOB
     */
    JuniorAccount(String name, String address, String DOB) {
        super(name, address, DOB);
        this.limit = 0;
    }


}
