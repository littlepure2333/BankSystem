package com.littlepure.controller;

import com.littlepure.models.*;

import java.util.Calendar;

public class CleanSystem {

    public static int SUNDAY = 1;
    public static int MONDAY = 2;
    public static int TUESDAY = 3;
    public static int WEDNESDAY = 4;
    public static int THURSDAY = 5;
    public static int FRIDAY = 6;
    public static int SATURDAY = 7;

    /**
     *  An external bank clearing system periodically clears un-cleared funds.
     *  Once cleared, they are immediately credited to the account.
     *  The clear period is one week, and when the clear-day can be definite.
     */
    public static void clear() {
        Calendar today = Calendar.getInstance();
        int weekday = today.get(Calendar.DAY_OF_WEEK);

        if(weekday == MONDAY) {
            CurrentAccountList currentAccountList = Bank.getCurrentAccountList();
            for (CurrentAccount currentAccount : currentAccountList.getCurrentAccountList()) {
                currentAccount.clearFunds();
            }

            SaverAccountList saverAccountList = Bank.getSaverAccountList();
            for (SaverAccount saverAccount : saverAccountList.getSaverAccountList()) {
                saverAccount.clearFunds();
            }

            JuniorAccountList juniorAccountList = Bank.getJuniorAccountList();
            for (JuniorAccount juniorAccount : juniorAccountList.getJuniorAccountList()) {
                juniorAccount.clearFunds();
            }

            Bank.update();
        }

    }
}
