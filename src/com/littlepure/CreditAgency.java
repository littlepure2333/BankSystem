package com.littlepure;

public class CreditAgency {
    public static boolean hasSatisfacoryCreditHistory(String name, String address, String DOB) {
        CurrentAccountList currentAccountList = Bank.getCurrentAccountList();
        for (CurrentAccount currentAccount : currentAccountList.getCurrentAccountList()) {
            // 如果这个用户注册过别的账户
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
