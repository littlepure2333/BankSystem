package com.littlepure;

import java.util.ArrayList;
import java.util.Iterator;

public class CurrentAccountList extends DataIO{
    public ArrayList<CurrentAccount> currentAccountList;
    private static final String FILE_LOCATION = "./data/currentAccount.txt";

    public CurrentAccountList() {
    }

    public void loadList() {
        this.currentAccountList = (ArrayList<CurrentAccount>)read(FILE_LOCATION);
    }

    public ArrayList<CurrentAccount> getCurrentAccountList() {
        return currentAccountList;
    }

    void addCurrentAccount(CurrentAccount currentAccount) {
        currentAccountList.add(currentAccount);
        save(currentAccountList, FILE_LOCATION);
    }

    void deleteCurrentAccount(int accNo) {
        Iterator<CurrentAccount> iter = currentAccountList.iterator();
        while(iter.hasNext()) {
            CurrentAccount currentAccount = iter.next();
            if(currentAccount.getAccNo() == accNo) {
                iter.remove();
                save(currentAccountList, FILE_LOCATION);
            }
        }
    }

    void resetList() {
        this.currentAccountList = new ArrayList<CurrentAccount>();
        save(currentAccountList, FILE_LOCATION);
    }
}
