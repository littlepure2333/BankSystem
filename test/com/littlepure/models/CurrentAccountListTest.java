package com.littlepure.models;

import com.littlepure.models.CurrentAccount;
import com.littlepure.models.CurrentAccountList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class CurrentAccountListTest {

    @BeforeEach
    void setUp() {
//        CurrentAccount currentAccount1 = new CurrentAccount("littlepure","BUPT","1998.6.3");
//        CurrentAccount currentAccount2 = new CurrentAccount("bigpure","Rizhao","2008.6.3");
    }

    @Test
    void testCreateCurrentAccountList() {
        CurrentAccountList list = new CurrentAccountList();
        list.resetList();
        System.out.println("1");
    }

    @Test
    void testGetCurrentAccountList() {
        CurrentAccountList list = new CurrentAccountList();
        ArrayList<CurrentAccount> l =  list.getCurrentAccountList();
        System.out.println("2");
    }

    @Test
    void addCurrentAccount() {
        CurrentAccount currentAccount2 = new CurrentAccount("hahahapure","Rizhao","2008.6.3");
        currentAccount2.setAccNo(222222);
        currentAccount2.setPIN(1234);
        currentAccount2.setSuspended(true);
        CurrentAccountList list = new CurrentAccountList();
        list.loadList();
        list.addCurrentAccount(currentAccount2);
        System.out.println("3");
    }

    @Test
    void testDeleteCurrentAccount() {
        CurrentAccountList list = new CurrentAccountList();
        list.loadList();
        list.deleteCurrentAccount(list.findAccountByNo(222222));
        System.out.println("4");
    }
}