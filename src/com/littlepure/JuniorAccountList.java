package com.littlepure;

import java.util.ArrayList;

public class JuniorAccountList extends DataIO{
    public ArrayList<JuniorAccount> juniorAccountList;
    private static final String FILE_LOCATION = "./data/juniorAccount.txt";

    public JuniorAccountList() {
        this.juniorAccountList = (ArrayList<JuniorAccount>)read(FILE_LOCATION);
    }

    public ArrayList<JuniorAccount> getJuniorAccountList() {
        return juniorAccountList;
    }

    void addJuniorAccount(JuniorAccount juniorAccount) {
        juniorAccountList.add(juniorAccount);
        save(juniorAccountList, FILE_LOCATION);
    }

    void deleteJuniorAccount(int accNo) {
        for(JuniorAccount juniorAccount:juniorAccountList) {
            if(juniorAccount.getAccNo() == accNo) {
                juniorAccountList.remove(juniorAccount);
                save(juniorAccountList, FILE_LOCATION);
            }
        }
    }
}
