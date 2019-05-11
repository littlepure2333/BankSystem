package com.littlepure;

import java.util.ArrayList;
import java.util.Iterator;

public class SaverAccountList extends DataIO{
    public ArrayList<SaverAccount> saverAccountList;
    private static final String FILE_LOCATION = "C:/Users/小纯洁/IdeaProjects/BankSystem/data/saverAccount.xml";

    public SaverAccountList() {
    }

    public void loadList() {
        this.saverAccountList = (ArrayList<SaverAccount>)read(FILE_LOCATION);
    }

    public ArrayList<SaverAccount> getSaverAccountList() {
        return saverAccountList;
    }

    void addSaverAccount(SaverAccount saverAccount) {
        saverAccountList.add(saverAccount);
        save(this.saverAccountList, FILE_LOCATION);
    }

    void deleteSaverAccount(int accNo) {
        Iterator<SaverAccount> iter = saverAccountList.iterator();
        while(iter.hasNext()) {
            SaverAccount saverAccount = iter.next();
            if(saverAccount.getAccNo() == accNo) {
                iter.remove();
                save(saverAccountList, FILE_LOCATION);
            }
        }
    }

    void resetList() {
        this.saverAccountList = new ArrayList<SaverAccount>();
        save(saverAccountList, FILE_LOCATION);
    }
}
