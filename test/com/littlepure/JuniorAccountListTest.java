package com.littlepure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JuniorAccountListTest {

    @BeforeEach
    void setUp() {
//        JuniorAccount juniorAccount1 = new JuniorAccount("littlepure","BUPT","1998.6.3");
//        JuniorAccount juniorAccount2 = new JuniorAccount("bigpure","Rizhao","2008.6.3");
    }

    @Test
    void testCreateJuniorAccountList() {
        JuniorAccountList list = new JuniorAccountList();
        list.resetList();
        System.out.println("1");
    }

    @Test
    void testGetJuniorAccountList() {
        JuniorAccountList list = new JuniorAccountList();
        ArrayList<JuniorAccount> l =  list.getJuniorAccountList();
        System.out.println("2");
    }

    @Test
    void addJuniorAccount() {
        JuniorAccount juniorAccount2 = new JuniorAccount("hahahapure","Rizhao","2008.6.3");
        juniorAccount2.setAccNo(222222);
        juniorAccount2.setPIN(1234);
        juniorAccount2.setSuspended(true);
        JuniorAccountList list = new JuniorAccountList();
        list.loadList();
        list.addJuniorAccount(juniorAccount2);
        System.out.println("3");
    }

    @Test
    void testDeleteJuniorAccount() {
        JuniorAccountList list = new JuniorAccountList();
        list.loadList();
        list.deleteJuniorAccount(222222);
        System.out.println("4");
    }
}