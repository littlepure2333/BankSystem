package com.littlepure;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class CurrentAccountList extends DataIO{
    public ArrayList<CurrentAccount> currentAccountList;
    private static final String FILE_LOCATION = "./data/currentAccount.xml";

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
        save(this.currentAccountList, FILE_LOCATION);
    }

    /**
     * close账户时才使用，把这个账户删掉
     * 使用时
     * @param currentAccount -想要删除的账号
     */
    void deleteCurrentAccount(CurrentAccount currentAccount) {
//        Iterator<CurrentAccount> iter = currentAccountList.iterator();
//        while(iter.hasNext()) {
//            CurrentAccount currentAccount = iter.next();
//            if(currentAccount.getAccNo() == accNo) {
//                iter.remove();
//                save(currentAccountList, FILE_LOCATION);
//            }
//        }
        currentAccountList.remove(currentAccount);
        save(currentAccountList, FILE_LOCATION);
    }

    void resetList() {
        this.currentAccountList = new ArrayList<CurrentAccount>();
        save(currentAccountList, FILE_LOCATION);
    }

    /**
     * 根据accNo找account
     * @param accNo -账号
     * @return currentAccount
     */
    CurrentAccount findAccountByNo(long accNo) {
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
     * 在账户进行更改后立即调用此函数
     * 把更改保存到本地
     */
    void update() {
        save(currentAccountList, FILE_LOCATION);
    }
}
