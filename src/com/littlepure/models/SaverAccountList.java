package com.littlepure.models;

import java.util.ArrayList;
import java.util.Iterator;

public class SaverAccountList extends DataIO{
    public ArrayList<SaverAccount> saverAccountList;
    private static final String FILE_LOCATION = "./data/saverAccount.xml";

    public SaverAccountList() {
    }

    /**
     * Load local data to active account list.
     */
    public void loadList() {
        this.saverAccountList = (ArrayList<SaverAccount>)read(FILE_LOCATION);
    }

    /**
     * Access active account list
     * @return account list
     */
    public ArrayList<SaverAccount> getSaverAccountList() {
        return saverAccountList;
    }

    /**
     * Add account to active account list
     * @param saverAccount account wanted to be added
     */
    public void addSaverAccount(SaverAccount saverAccount) {
        saverAccountList.add(saverAccount);
        save(this.saverAccountList, FILE_LOCATION);
    }

    /**
     * Delete a account from active list.
     * @param saverAccount account wanted to be deleted
     */
    public void deleteSaverAccount(SaverAccount saverAccount) {
        saverAccountList.remove(saverAccount);
        save(saverAccountList, FILE_LOCATION);
    }

    /**
     * Reset active account list.
     */
    public void resetList() {
        this.saverAccountList = new ArrayList<SaverAccount>();
        save(saverAccountList, FILE_LOCATION);
    }

    /**
     * Find account by account number
     * @param accNo account number
     * @return saverAccount
     */
    public SaverAccount findAccountByNo(long accNo) {
        Iterator<SaverAccount> iter = saverAccountList.iterator();
        while(iter.hasNext()) {
            SaverAccount saverAccount = iter.next();
            if(saverAccount.getAccNo() == accNo) {
                return saverAccount;
            }
        }
        return null;
    }

    /**
     * Save the change to local text file
     */
    public void update() {
        save(saverAccountList, FILE_LOCATION);
    }
}
