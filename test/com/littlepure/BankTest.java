package com.littlepure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    @Test
    void testGenerateNo() {
        System.out.println(Bank.generateNo());
    }

    @Test
    void testIsValidDate() {
        String str1 = "2013-04-4";
        String str2 = "2013-2-30";
        String str3 = "2023-4-15";
        assertTrue(Bank.isValidDate(str1));
        assertFalse(Bank.isValidDate(str2));
        assertFalse(Bank.isValidDate(str3));
    }

    @Test
    void testIsJunior() {
        String str1 = "2013-04-4";
        String str2 = "2000-04-4";
        assertTrue(Bank.isJunior(str1));
        assertFalse(Bank.isJunior(str2));
    }

    @Test
    void testRegister() {
        String name1 = "fsy";
        String address1 = "bupt";
        String DOB1 = "1997-4-23";
        Bank.register(name1, address1, DOB1, Bank.CURRENT);
        System.out.println("1");

        String name2 = "fsysb";
        String address2 = "IOT";
        String DOB2 = "1998-6-6";
        Bank.register(name2, address2, DOB2, Bank.SAVER);
        System.out.println("2");

        String name3 = "fsyson";
        String address3 = "kindergarten";
        String DOB3 = "2016-6-1";
        Bank.register(name3, address3, DOB3, Bank.JUNIOR);
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

        Bank.register(name1, address1, DOB1, Bank.SAVER);
        Bank.setPIN(PIN1);
        assertEquals(PIN1, Bank.getAccount().getPIN());
        assertTrue(Bank.logIn(Bank.getAccount().getAccNo(), PIN1));

        Bank.register(name2, address2, DOB2, Bank.CURRENT);
        Bank.setPIN(PIN2);
        assertEquals(PIN2, Bank.getAccount().getPIN());
        assertTrue(Bank.logIn(Bank.getAccount().getAccNo(), PIN2));

        Bank.register(name3, address3, DOB3, Bank.JUNIOR);
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
    void logOut() {
        long accNo = 1557647912110L;
        int PIN = 2233;
        Bank.logIn(accNo, PIN);
        Bank.logOut();
        assertTrue(Bank.getAccount() == null);
    }

    @Test
    void closeAccount() {
        String name1 = "测试删除账号";
        String address1 = "荒蛮之地";
        String DOB1 = "1990-4-23";
        int PIN1 = 5566;
        Bank.register(name1, address1, DOB1, Bank.SAVER);
        long testAccNo = Bank.getAccount().getAccNo();
        Bank.setPIN(PIN1);
        Bank.logOut();
        Bank.logIn(testAccNo, PIN1);
        Bank.closeAccount();
        assertTrue(Bank.getSaverAccountList().findAccountByNo(testAccNo) == null);

    }
}