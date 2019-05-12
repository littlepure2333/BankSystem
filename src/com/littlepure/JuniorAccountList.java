package com.littlepure;

import java.util.ArrayList;
import java.util.Iterator;

public class JuniorAccountList extends DataIO{
    public ArrayList<JuniorAccount> juniorAccountList;
    private static final String FILE_LOCATION = "C:/Users/小纯洁/IdeaProjects/BankSystem/data/juniorAccount.xml";

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
        save(this.juniorAccountList, FILE_LOCATION);
    }

    /**
     * close账户时才使用，把这个账户删掉
     * 使用时
     * @param juniorAccount -想要删除的账号
     */
    void deleteJuniorAccount(JuniorAccount juniorAccount) {
//        Iterator<JuniorAccount> iter = juniorAccountList.iterator();
//        while(iter.hasNext()) {
//            JuniorAccount juniorAccount = iter.next();
//            if(juniorAccount.getAccNo() == accNo) {
//                iter.remove();
//                save(juniorAccountList, FILE_LOCATION);
//            }
//        }
        juniorAccountList.remove(juniorAccount);
        save(juniorAccountList, FILE_LOCATION);
    }

    void resetList() {
        this.juniorAccountList = new ArrayList<JuniorAccount>();
        save(juniorAccountList, FILE_LOCATION);
    }

    /**
     * 根据accNo找account
     * @param accNo -账号
     * @return juniorAccount
     */
    JuniorAccount findAccountByNo(long accNo) {
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
     * 在账户进行更改后立即调用此函数
     * 把更改保存到本地
     */
    void update() {
        save(juniorAccountList, FILE_LOCATION);
    }
}
