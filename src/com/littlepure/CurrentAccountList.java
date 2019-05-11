package com.littlepure;

import java.util.ArrayList;

public class CurrentAccountList extends DataIO{
    public ArrayList<CurrentAccount> currentAccountList;
    private static final String FILE_LOCATION = "./data/currentAccount.txt";

    public CurrentAccountList() {
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
        for(CurrentAccount currentAccount: currentAccountList) {
            if(currentAccount.getAccNo() == accNo) {
                currentAccountList.remove(currentAccount);
                save(currentAccountList, FILE_LOCATION);
            }
        }
    }

    void resetList() {
        this.currentAccountList = new ArrayList<CurrentAccount>();
        save(currentAccountList, FILE_LOCATION);
    }
}
