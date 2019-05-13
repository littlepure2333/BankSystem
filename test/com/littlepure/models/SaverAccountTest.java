package com.littlepure.models;

import com.littlepure.models.SaverAccount;
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

    //todo finish tests
    @Test
    void getWithdrawalIsAllowed() {
    }

    @Test
    void getNoticeDate() {
    }

    @Test
    void applyNotice() {
        SaverAccount saverAccount = new SaverAccount("qw", "as","1998.3.4");
        assertFalse(saverAccount.applyNotice(2));
        assertTrue(saverAccount.applyNotice(3));
    }

    @Test
    void withdraw() {
    }
}