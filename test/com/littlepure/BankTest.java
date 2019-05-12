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
        int PIN = 1122;
        Bank.register(name1, address1, DOB1, Bank.SAVER);
        Bank.setPIN(PIN);
        assertEquals(PIN, Bank.getAccount().getPIN());

        assertTrue(Bank.logIn(Bank.getAccount().getAccNo(), PIN));
    }
}