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
    private static final int UNKNOWN_TYPE = 5;

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

    static SaverAccountList getSaverAccountList() {
        return saverAccountList;
    }

    static CurrentAccountList getCurrentAccountList() {
        return currentAccountList;
    }

    static JuniorAccountList getJuniorAccountList() {
        return juniorAccountList;
    }

    public static BankAccount getAccount() {
        return account;
    }


    public static void setAccount(BankAccount account) {
        Bank.account = account;
    }

    /**
     * Used for save changes to local text file.
     * Should be called immediately after the account been changed.
     */
    static void update() {
        saverAccountList.update();
        currentAccountList.update();
        juniorAccountList.update();
    }

    /**
     * Register a new account, and feedback register result.
     * If success, this new account will be the active account.
     * @param name name of customer
     * @param address address of customer
     * @param DOB Date of birth
     * @param type account type
     * @return 0 -register success / 1 -incorrect format of date / 2 -insufficient credit figure
     * / 3 -is not a junior / 4 -has a poor credit history
     */
    public static int register(String name, String address, String DOB, int type, double amount) {
        if(dateUtil.isValidDate(DOB)) {
            if(amount >= MINIMUM_CREDIT_FIGURE) {
                switch (type) {
                    case JUNIOR:
                        if(dateUtil.isJunior(DOB)){
                            if(CreditAgency.hasSatisfactoryCreditHistory(name, address, DOB)) {
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
                        if(CreditAgency.hasSatisfactoryCreditHistory(name, address, DOB)) {
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
                        if(CreditAgency.hasSatisfactoryCreditHistory(name, address, DOB)) {
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
     * Log in, and active account is loaded meanwhile.
     * Most operations need to log in first.
     * @param accNo Account number
     * @param PIN Personal identification number
     * @return {@code true}/{@code false} success or fail
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
     * Set PIN for new account.
     * @param PIN Personal identification number
     */
    public static void setPIN(int PIN) {
        getAccount().setPIN(PIN);
        update();
    }

    /**
     * Access active account balance.
     * @return balance({@code double})
     */
    public static double getBalance() {
        return getAccount().getBalance();
    }

    /**
     * Check if active account is suspended.
     * @return {@code true}/{@code false} suspended or not suspended
     */
    public static boolean isSuspended() {
        return getAccount().getSuspended();
    }

    /**
     * Deposit
     * @param amount the amount to deposit
     * @param cleared if need to be cleared
     */
    public static void deposit(double amount, boolean cleared) {
        getAccount().deposit(amount, cleared);
        update();
    }

    /**
     * Withdraw, and feedback withdraw result.
     * @param amount the amount to deposit
     * @return WITHDRAW_SUCCESS = 0 /
     * EXCEED_OVERDRAFT_LIMIT = 1 /
     * WITHDRAWAL_IS_NOT_ALLOWED = 2 /
     * HAS_NOT_NOTICED = 3
     */
    public static int withdraw(double amount) {
        int result;
        // if saver account, there is a different withdraw method
        if(getAccount() instanceof SaverAccount) {
            SaverAccount saverAccount = (SaverAccount) getAccount();
            result = saverAccount.withdraw(amount);
            update();
            return result;
        }
        // current or junior account otherwise
        else {
            result = getAccount().withdraw(amount);
            update();
        }
        return result;
    }

    /**
     * Log out active account.
     * @see #logIn(long, int)
     */
    static void logOut() {
        setAccount(null);
    }

    /**
     * Suspend active account.
     * @see #logIn(long, int)
     */
    public static void suspend() {
        getAccount().suspend();
        update();
    }

    /**
     * Reinstate active account.
     * @see #logIn(long, int)
     */
    public static void reinstate() {
        getAccount().reinstate();
        update();
    }

    /**
     * Close active account. All information about this account is eliminated in local text file.
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
     * Apply a notice, only being called when active account is a saver account.
     * @param noticeDays how many days later which active account apply a notice
     * @return {@code true}/{@code false} success or fail
     */
    public static boolean applyNotice(int noticeDays) {
        SaverAccount saverAccount = (SaverAccount)Bank.getAccount();
        boolean result = saverAccount.applyNotice(noticeDays);
        update();
        return result;
    }

    /**
     * Access the day active account can withdraw.
     * Only Being called when active is a saver account.
     * @return "yyyy-MM-dd"
     */
    public static String getNoticeDate() {
        SaverAccount saverAccount = (SaverAccount)Bank.getAccount();
        Date date = saverAccount.getNoticeDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    /**
     * Find account by account number, and load to active account.
     * @param accNo account number
     * @return BankAccount
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
     * Check if active account balance is cleared to zero.
     * Be called to check if the account can be closed.
     * @return {@code true}/{@code false} can or can not
     */
    public static boolean canClose() {
        return (getAccount().getBalance() == 0) &&
                (getAccount().getUnclearedFunds() == 0);
    }

}
