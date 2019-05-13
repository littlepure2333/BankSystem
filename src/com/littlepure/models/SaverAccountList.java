package com.littlepure.models;

import java.util.ArrayList;
import java.util.Iterator;

public class SaverAccountList extends DataIO{
    public ArrayList<SaverAccount> saverAccountList;
    private static final String FILE_LOCATION = "./data/saverAccount.xml";

    public SaverAccountList() {
    }

    public void loadList() {
        this.saverAccountList = (ArrayList<SaverAccount>)read(FILE_LOCATION);
    }

    public ArrayList<SaverAccount> getSaverAccountList() {
        return saverAccountList;
    }

    public void addSaverAccount(SaverAccount saverAccount) {
        saverAccountList.add(saverAccount);
        save(this.saverAccountList, FILE_LOCATION);
    }

    /**
     * close账户时才使用，把这个账户删掉
     * 使用时
     * @param saverAccount -想要删除的账号
     */
    public void deleteSaverAccount(SaverAccount saverAccount) {
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

    public void resetList() {
        this.saverAccountList = new ArrayList<SaverAccount>();
        save(saverAccountList, FILE_LOCATION);
    }

    /**
     * 根据accNo找account
     * @param accNo -账号
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
     * 在账户进行更改后立即调用此函数
     * 把更改保存到本地
     */
    public void update() {
        save(saverAccountList, FILE_LOCATION);
    }
}
