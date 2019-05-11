package com.littlepure;

import java.util.ArrayList;
import java.util.Iterator;

public class JuniorAccountList extends DataIO{
    public ArrayList<JuniorAccount> juniorAccountList;
    private static final String FILE_LOCATION = "./data/juniorAccount.txt";

    public JuniorAccountList() {
    }

    public void loadList() {
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
        Iterator<JuniorAccount> iter = juniorAccountList.iterator();
        while(iter.hasNext()) {
            JuniorAccount juniorAccount = iter.next();
            if(juniorAccount.getAccNo() == accNo) {
                iter.remove();
                save(juniorAccountList, FILE_LOCATION);
            }
        }
    }

    void resetList() {
        this.juniorAccountList = new ArrayList<JuniorAccount>();
        save(juniorAccountList, FILE_LOCATION);
    }
}
