package com.littlepure.models;

import com.littlepure.models.SaverAccount;
import com.littlepure.models.SaverAccountList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class SaverAccountListTest {

    @BeforeEach
    void setUp() {
//        SaverAccount saverAccount1 = new SaverAccount("littlepure","BUPT","1998.6.3");
//        SaverAccount saverAccount2 = new SaverAccount("bigpure","Rizhao","2008.6.3");
    }

    @Test
    void testCreateSaverAccountList() {
        SaverAccountList list = new SaverAccountList();
        list.resetList();
        System.out.println("1");
    }

    @Test
    void testGetSaverAccountList() {
        SaverAccountList list = new SaverAccountList();
        ArrayList<SaverAccount> l =  list.getSaverAccountList();
        System.out.println("2");
    }

    @Test
    void addSaverAccount() {
        SaverAccount saverAccount2 = new SaverAccount("hahahapure","Rizhao","2008.6.3");
        saverAccount2.setAccNo(222222);
        saverAccount2.setPIN(1234);
        saverAccount2.setSuspended(true);
        SaverAccountList list = new SaverAccountList();
        list.loadList();
        list.addSaverAccount(saverAccount2);
        System.out.println("3");
    }

    @Test
    void testDeleteSaverAccount() {
        SaverAccountList list = new SaverAccountList();
        list.loadList();
        list.deleteSaverAccount(list.findAccountByNo(222222));
        System.out.println("4");
    }
}