package com.littlepure.controller;

import com.littlepure.models.CurrentAccount;
import com.littlepure.util.dateUtil;
import com.littlepure.util.numberUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    @Test
    void testRegister() {
        String name1 = "fsy";
        String address1 = "bupt";
        String DOB1 = "1997-4-23";
        Bank.register(name1, address1, DOB1, Bank.CURRENT, 100);
        System.out.println("1");

        String name2 = "fsysb";
        String address2 = "IOT";
        String DOB2 = "1998-6-6";
        Bank.register(name2, address2, DOB2, Bank.SAVER, 100);
        System.out.println("2");

        String name3 = "fsyson";
        String address3 = "kindergarten";
        String DOB3 = "2016-6-1";
        Bank.register(name3, address3, DOB3, Bank.JUNIOR, 100);
        System.out.println("3");
    }

    @Test
    void testSetAccount() {
        String name1 = "fsy";
        String address1 = "bupt";
        String DOB1 = "1997-4-23";
        CurrentAccount currentAccount = new CurrentAccount(name1, address1, DOB1);
        Bank.setAccount(currentAccount);
        assertEquals(name1, Bank.getAccount().getName());
    }

    @Test
    void testLogIn() {
        String name1 = "fdslj";
        String address1 = "rubbish";
        String DOB1 = "1990-4-23";
        int PIN1 = 1122;
        String name2 = "fdslj";
        String address2 = "rubbish";
        String DOB2 = "1990-4-23";
        int PIN2 = 2233;
        String name3 = "fdslj";
        String address3 = "rubbish";
        String DOB3 = "2010-4-23";
        int PIN3 = 3344;

        Bank.register(name1, address1, DOB1, Bank.SAVER, 100);
        Bank.setPIN(PIN1);
        assertEquals(PIN1, Bank.getAccount().getPIN());
        assertTrue(Bank.logIn(Bank.getAccount().getAccNo(), PIN1));

        Bank.register(name2, address2, DOB2, Bank.CURRENT, 100);
        Bank.setPIN(PIN2);
        assertEquals(PIN2, Bank.getAccount().getPIN());
        assertTrue(Bank.logIn(Bank.getAccount().getAccNo(), PIN2));

        Bank.register(name3, address3, DOB3, Bank.JUNIOR, 100);
        Bank.setPIN(PIN3);
        assertEquals(PIN3, Bank.getAccount().getPIN());
        assertTrue(Bank.logIn(Bank.getAccount().getAccNo(), PIN3));
    }

    @Test
    void deposit() {
        long accNo = 1557647912110L;
        int PIN = 2233;
        int amount = 1000;
        Bank.logIn(accNo, PIN);
        Bank.deposit(amount, true);
        assertEquals(amount, Bank.getAccount().getBalance());
    }

    @Test
    void withdraw() {
        long accNo = 1557647912110L;
        int PIN = 2233;
        int amount = 10;
        Bank.logIn(accNo, PIN);
        double previousBalance = Bank.getAccount().getBalance();
        int result = Bank.withdraw(amount);
        System.out.println(result);
        assertEquals(amount, (previousBalance - Bank.getAccount().getBalance()));
    }

    @Test
    void testSuspend() {
        long accNo = 1557647912110L;
        int PIN = 2233;
        Bank.logIn(accNo, PIN);
        Bank.suspend();
        assertTrue(Bank.getAccount().getSuspended());
    }

    @Test
    void testReinstate() {
        long accNo = 1557647912110L;
        int PIN = 2233;
        Bank.logIn(accNo, PIN);
        Bank.reinstate();
        assertFalse(Bank.getAccount().getSuspended());
    }

    @Test
    void testLogOut() {
        long accNo = 1557647912110L;
        int PIN = 2233;
        Bank.logIn(accNo, PIN);
        Bank.logOut();
        assertTrue(Bank.getAccount() == null);
    }

    @Test
    void testCloseAccount() {
        String name1 = "测试删除账号";
        String address1 = "荒蛮之地";
        String DOB1 = "1990-4-23";
        int PIN1 = 5566;
        Bank.register(name1, address1, DOB1, Bank.SAVER, 100);
        long testAccNo = Bank.getAccount().getAccNo();
        Bank.setPIN(PIN1);
        Bank.logOut();
        Bank.logIn(testAccNo, PIN1);
        Bank.closeAccount();
        assertTrue(Bank.getSaverAccountList().findAccountByNo(testAccNo) == null);

    }

    @Test
    void testNotice() {
        String name1 = "测试notice账号";
        String address1 = "haha home";
        String DOB1 = "1970-4-23";
        int PIN1 = 6677;
        int noticeDays1 = 2;
        int noticeDays2 = 3;
        Bank.register(name1, address1, DOB1, Bank.SAVER, 100);
        assertFalse(Bank.applyNotice(noticeDays1));
        assertTrue(Bank.applyNotice(noticeDays2));
        System.out.println(Bank.getNoticeDate());

    }
}