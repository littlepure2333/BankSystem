package com.littlepure.models;

import java.util.ArrayList;
import java.util.Iterator;

public class CurrentAccountList extends DataIO{
    public ArrayList<CurrentAccount> currentAccountList;
    private static final String FILE_LOCATION = "./data/currentAccount.xml";

    public CurrentAccountList() {
    }

    /**
     * Load local data to active account list.
     */
    public void loadList() {
        this.currentAccountList = (ArrayList<CurrentAccount>)read(FILE_LOCATION);
    }

    /**
     * Access active account list
     * @return account list
     */
    public ArrayList<CurrentAccount> getCurrentAccountList() {
        return currentAccountList;
    }

    /**
     * Add account to active account list
     * @param currentAccount account wanted to be added
     */
    public void addCurrentAccount(CurrentAccount currentAccount) {
        currentAccountList.add(currentAccount);
        save(this.currentAccountList, FILE_LOCATION);
    }

    /**
     * Delete a account from active list.
     * @param currentAccount account wanted to be deleted
     */
    public void deleteCurrentAccount(CurrentAccount currentAccount) {
        currentAccountList.remove(currentAccount);
        save(currentAccountList, FILE_LOCATION);
    }

    /**
     * Reset active account list.
     */
    public void resetList() {
        this.currentAccountList = new ArrayList<CurrentAccount>();
        save(currentAccountList, FILE_LOCATION);
    }

    /**
     * Find account by account number
     * @param accNo account number
     * @return currentAccount
     */
    public CurrentAccount findAccountByNo(long accNo) {
        Iterator<CurrentAccount> iter = currentAccountList.iterator();
        while(iter.hasNext()) {
            CurrentAccount currentAccount = iter.next();
            if(currentAccount.getAccNo() == accNo) {
                return currentAccount;
            }
        }
        return null;
    }

    /**
     * Save the change to local text file
     */
    public void update() {
        save(currentAccountList, FILE_LOCATION);
    }
}
