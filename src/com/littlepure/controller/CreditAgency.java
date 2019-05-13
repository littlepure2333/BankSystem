package com.littlepure.controller;

import com.littlepure.models.CurrentAccount;
import com.littlepure.models.CurrentAccountList;

class CreditAgency {
    /**
     * Credit agency carries out a credit search.
     * Provided that the customer has a satisfactory credit history,
     * a new account is allowed to open.
     * @param name name of customer
     * @param address address of customer
     * @param DOB Date of birthday
     * @return {@code true}/{@code false} has or has not
     */
    static boolean hasSatisfactoryCreditHistory(String name, String address, String DOB) {
        CurrentAccountList currentAccountList = Bank.getCurrentAccountList();
        for (CurrentAccount currentAccount : currentAccountList.getCurrentAccountList()) {
            // if the customer has opened one account
            if ((currentAccount.getName().equals(name))
                    && (currentAccount.getAddress().equals(address))
                    && (currentAccount.getDOB().equals(DOB))) {
                if (currentAccount.getBalance() < 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
