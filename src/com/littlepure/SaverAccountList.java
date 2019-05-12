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

    /**
     * close账户时才使用，把这个账户删掉
     * 使用时
     * @param saverAccount -想要删除的账号
     */
    void deleteSaverAccount(SaverAccount saverAccount) {
//        Iterator<SaverAccount> iter = saverAccountList.iterator();
//        while(iter.hasNext()) {
//            SaverAccount saverAccount = iter.next();
//            if(saverAccount.getAccNo() == accNo) {
//                iter.remove();
//                save(saverAccountList, FILE_LOCATION);
//            }
//        }
        saverAccountList.remove(saverAccount);
        save(saverAccountList, FILE_LOCATION);
    }

    void resetList() {
        this.saverAccountList = new ArrayList<SaverAccount>();
        save(saverAccountList, FILE_LOCATION);
    }

    /**
     * 根据accNo找account
     * @param accNo -账号
     * @return saverAccount
     */
    SaverAccount findAccountByNo(long accNo) {
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
     * 在账户进行更改后立即调用此函数
     * 把更改保存到本地
     */
    void update() {
        save(saverAccountList, FILE_LOCATION);
    }
}
