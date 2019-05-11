package com.littlepure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaverAccountTest {

    @Test
    void testCreate() {
        SaverAccount saverAccount = new SaverAccount("qw", "as","1998.3.4");
        assertEquals("qw", saverAccount.getName());
        assertEquals("as", saverAccount.getAddress());
        assertEquals("1998.3.4", saverAccount.getDOB());
    }
}