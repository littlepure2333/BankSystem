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
        assertTrue(Bank.isValidDate(str1));
        assertFalse(Bank.isValidDate(str2));
    }
}