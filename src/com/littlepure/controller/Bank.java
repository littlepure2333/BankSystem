package com.littlepure.controller;

import com.littlepure.models.*;
import com.littlepure.util.dateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Bank {
    public static final int JUNIOR = 0;
    public static final int CURRENT = 1;
    public static final int SAVER = 2;
    public static final int REGISTER_SUCCESS = 0;
    public static final int INCORRECT_FORMAT = 1;
    public static final int POOR_CREDIT_HISTORY = 2;
    public static final int NOT_JUNIOR = 3;
    public static final int INSUFFICIENT_CREDIT_FIGURE = 4;
    public static final int UNKNOWN_TYPE = 5;

    public static final double MINIMUM_CREDIT_FIGURE = 50;

    private static JuniorAccountList juniorAccountList = new JuniorAccountList();
    private static CurrentAccountList currentAccountList = new CurrentAccountList();
    private static SaverAccountList saverAccountList = new SaverAccountList();
    private static BankAccount account;

    static {
        juniorAccountList.loadList();
        currentAccountList.loadList();
        saverAccountList.loadList();
    }

    public static SaverAccountList getSaverAccountList() {
        return saverAccountList;
    }

    public static CurrentAccountList getCurrentAccountList() {
        return currentAccountList;
    }

    public static JuniorAccountList getJuniorAccountList() {
        return juniorAccountList;
    }

    public static BankAccount getAccount() {
        return account;
    }


    public static void setAccount(BankAccount account) {
        Bank.account = account;
    }

    /**
     * 在账户进行更改后立即调用此函数
     * 把更改保存到本地
     */
    public static void update() {
        saverAccountList.update();
        currentAccountList.update();
        juniorAccountList.update();
    }

//todo 开户需要最小金额
    /**
     * 给用户注册，选择账户类型并返回注册结果
     * 注册成功后会使当前账户变成刚注册的账户
     * 注册成功后要立即调用setPIN
     * @param name
     * @param address
     * @param DOB -Date of birth
     * @param type
     * @return 0 -成功 / 1 -DOB格式错误 / 2 -充值金额不够 / 3 -不是Junior / 4 -信用历史较差不让注册
     */
    public static int register(String name, String address, String DOB, int type, double amount) {
        if(dateUtil.isValidDate(DOB)) {
            if(amount >= MINIMUM_CREDIT_FIGURE) {
                switch (type) {
                    case JUNIOR:
                        if(dateUtil.isJunior(DOB)){
                            if(CreditAgency.hasSatisfacoryCreditHistory(name, address, DOB)) {
                                JuniorAccount juniorAccount = new JuniorAccount(name, address, DOB);
                                juniorAccount.setBalance(amount);
                                juniorAccountList.addJuniorAccount(juniorAccount);
                                setAccount(juniorAccount);
                                return REGISTER_SUCCESS;
                            }
                            else {
                                return POOR_CREDIT_HISTORY;
                            }
                        }
                        else {
                            return NOT_JUNIOR;
                        }
                    case CURRENT:
                        if(CreditAgency.hasSatisfacoryCreditHistory(name, address, DOB)) {
                            CurrentAccount currentAccount = new CurrentAccount(name, address, DOB);
                            currentAccount.setBalance(amount);
                            currentAccountList.addCurrentAccount(currentAccount);
                            setAccount(currentAccount);
                            return REGISTER_SUCCESS;
                        }
                        else {
                            return POOR_CREDIT_HISTORY;
                        }
                    case SAVER:
                        if(CreditAgency.hasSatisfacoryCreditHistory(name, address, DOB)) {
                            SaverAccount saverAccount = new SaverAccount(name, address, DOB);
                            saverAccount.setBalance(amount);
                            saverAccountList.addSaverAccount(saverAccount);
                            setAccount(saverAccount);
                            return REGISTER_SUCCESS;
                        }
                        else {
                            return POOR_CREDIT_HISTORY;
                        }
                    default:
                        return UNKNOWN_TYPE;
                }
            }
            else {
                return INSUFFICIENT_CREDIT_FIGURE;
            }
        }
        else {
            return INCORRECT_FORMAT;
        }
    }

    /**
     * 登陆,并且加载到当前账户，只有账号密码都对才返回正确
     * 账号不存在，密码错误都会返回false
     * 除了注册之外的操作都应先登陆
     * 登陆后应该检查是否账户被停用
     * @param accNo -账号
     * @param PIN -密码
     * @return true/false
     */
    public static boolean logIn(long accNo, int PIN) {
        BankAccount temp = saverAccountList.findAccountByNo(accNo);
        if(temp != null) {
            if(temp.identify(PIN)) {
                setAccount(temp);
                return true;
            }
            else {
                return false;
            }
        }
        temp = currentAccountList.findAccountByNo(accNo);
        if(temp != null) {
            if(temp.identify(PIN)) {
                setAccount(temp);
                return true;
            }
            else {
                return false;
            }
        }
        temp = juniorAccountList.findAccountByNo(accNo);
        if(temp != null) {
            if(temp.identify(PIN)) {
                setAccount(temp);
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    /**
     * 注册成功后紧接着调用这个函数用来设置密码
     * @param PIN -密码
     */
    public static void setPIN(int PIN) {
        getAccount().setPIN(PIN);
        update();
    }

    /**
     * 获取当前账户余额
     * @return balance
     */
    public static double getBalance() {
        return getAccount().getBalance();
    }

    /**
     * 登陆后立即检查是否被停用
     * @return true/false
     */
    public static boolean isSuspended() {
        return getAccount().getSuspended();
    }

    /**
     * 存钱,并且自动更新到本地
     * @param amount -金额
     * @param cleared -是否cleared
     * @return
     */
    public static void deposit(double amount, boolean cleared) {
        getAccount().deposit(amount, cleared);
        update();
    }

    /**
     * 取钱，并自动更新到本地
     * 要在登陆之后调用
     * @param amount
     * @return WITHDRAW_SUCCESS = 0 /
     * EXCEED_OVERDRAFT_LIMIT = 1 /
     * WITHDRAWAL_IS_NOT_ALLOWED = 2 /
     * HAS_NOT_NOTICED = 3
     */
    public static int withdraw(double amount) {
        int result;
        // 如果是saverAccount,withdraw方法不一样
        if(getAccount() instanceof SaverAccount) {
            SaverAccount saverAccount = (SaverAccount) getAccount();
            result = saverAccount.withdraw(amount);
            update();
            return result;
        }
        // 要不然就是current或junior
        else {
            result = getAccount().withdraw(amount);
            update();
        }
        return result;
    }

    /**
     * 登出，所有操作完成后等应该立即调用此函数
     */
    public static void logOut() {
        setAccount(null);
    }

    public static void suspend() {
        getAccount().suspend();
        update();
    }

    public static void reinstate() {
        getAccount().reinstate();
        update();
    }

    /**
     * 注销这个账户，本地将不会存有此账户信息
     * 必须先登录才能调用这个函数
     */
    public static void closeAccount() {
        BankAccount temp = Bank.getAccount();
        if(temp instanceof SaverAccount) {
            SaverAccount saverAccount = (SaverAccount)temp;
            saverAccountList.deleteSaverAccount(saverAccount);
        }
        else if(temp instanceof CurrentAccount) {
            CurrentAccount currentAccount = (CurrentAccount)temp;
            currentAccountList.deleteCurrentAccount(currentAccount);
        }
        else {
            JuniorAccount juniorAccount = (JuniorAccount)temp;
            juniorAccountList.deleteJuniorAccount(juniorAccount);
        }
    }

    /**
     * 申请notice,并保存更改结果到本地
     * 仅当当前账户是saver时才会调用
     * withdraw返回结果是 没有notice 时紧接着调用此函数
     * @param noticeDays -申请几天后取钱
     * @return true/false -是否符合最小日期
     */
    public static boolean applyNotice(int noticeDays) {
        SaverAccount saverAccount = (SaverAccount)Bank.getAccount();
        boolean result = saverAccount.applyNotice(noticeDays);
        update();
        return result;
    }

    /**
     * 获取notice是哪一天
     * 仅当当前账户是saver时才会调用
     * withdraw返回结果是 没到notice 时紧接着调用此函数显示什么时候是notice
     * @return "yyyy-MM-dd" -notice的时间
     */
    public static String getNoticeDate() {
        SaverAccount saverAccount = (SaverAccount)Bank.getAccount();
        Date date = saverAccount.getNoticeDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    /**
     * 按照accNo查找账户，并且会加载到当前账户
     * @param accNo
     * @return
     */
    public static BankAccount findAccountByNo(long accNo) {
        BankAccount temp = juniorAccountList.findAccountByNo(accNo);
        if(temp != null) {
            setAccount(temp);
            return temp;
        }
        temp = saverAccountList.findAccountByNo(accNo);
        if(temp != null) {
            setAccount(temp);
            return temp;
        }
        temp = currentAccountList.findAccountByNo(accNo);
        if(temp != null) {
            setAccount(temp);
            return temp;
        }
        return null;
    }

    /**
     * 判断是否可以注销账号
     * 当有balance或uncleared funds没结清的时候不能注销
     * @return tru/false
     */
    public static boolean canClose() {
        if((getAccount().getBalance() == 0) &&
                (getAccount().getUnclearedFunds() == 0)) {
            return true;
        }
        else {
            return false;
        }
    }

    //todo 检查输入格式 可以在GUI上设置只能接受数字的框
}
