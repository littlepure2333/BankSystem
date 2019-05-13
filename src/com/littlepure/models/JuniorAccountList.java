package com.littlepure.models;

import java.util.ArrayList;
import java.util.Iterator;

public class JuniorAccountList extends DataIO{
    public ArrayList<JuniorAccount> juniorAccountList;
    private static final String FILE_LOCATION = "./data/juniorAccount.xml";

    public JuniorAccountList() {
    }

    /**
     * Load local data to active account list.
     */
    public void loadList() {
        this.juniorAccountList = (ArrayList<JuniorAccount>)read(FILE_LOCATION);
    }

    /**
     * Access active account list
     * @return account list
     */
    public ArrayList<JuniorAccount> getJuniorAccountList() {
        return juniorAccountList;
    }

    /**
     * Add account to active account list
     * @param juniorAccount account wanted to be added
     */
    public void addJuniorAccount(JuniorAccount juniorAccount) {
        juniorAccountList.add(juniorAccount);
        save(this.juniorAccountList, FILE_LOCATION);
    }

    /**
     * Delete a account from active list.
     * @param juniorAccount account wanted to be deleted
     */
    public void deleteJuniorAccount(JuniorAccount juniorAccount) {
        juniorAccountList.remove(juniorAccount);
        save(juniorAccountList, FILE_LOCATION);
    }

    /**
     * Reset active account list.
     */
    public void resetList() {
        this.juniorAccountList = new ArrayList<JuniorAccount>();
        save(juniorAccountList, FILE_LOCATION);
    }

    /**
     * Find account by account number
     * @param accNo account number
     * @return juniorAccount
     */
    public JuniorAccount findAccountByNo(long accNo) {
        Iterator<JuniorAccount> iter = juniorAccountList.iterator();
        while(iter.hasNext()) {
            JuniorAccount juniorAccount = iter.next();
            if(juniorAccount.getAccNo() == accNo) {
                return juniorAccount;
            }
        }
        return null;
    }

    /**
     * Save the change to local text file
     */
    public void update() {
        save(juniorAccountList, FILE_LOCATION);
    }
}
