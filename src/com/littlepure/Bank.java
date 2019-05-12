package com.littlepure;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Bank {
    public static final int JUNIOR = 0;
    public static final int CURRENT = 1;
    public static final int SAVER = 2;
    public static final int REGISTER_SUCCESS = 0;
    public static final int INCORRECT_FORMAT = 1;
    public static final int NOT_JUNIOR = 2;
    public static final int POOR_CREDIT_HISTORY = 3;
    public static final int UNKNOWN_TYPE = 4;

    private static JuniorAccountList juniorAccountList;
    private static CurrentAccountList currentAccountList;
    private static SaverAccountList saverAccountList;
    private static BankAccount account;

    static {
        juniorAccountList = new JuniorAccountList();
        currentAccountList = new CurrentAccountList();
        saverAccountList = new SaverAccountList();
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

    /**
     * 生成全局独一无二的No
     * @return
     */
    public static long generateNo() {
        long nowDate = new Date().getTime();
        return nowDate;
    }

    /**
     * 给用户注册，选择账户类型并返回注册结果
     * 注册成功后要立即调用
     * @param name
     * @param address
     * @param DOB -Date of birth
     * @param type
     * @return 0 -成功 / 1 -DOB格式错误 / 2 -不是Junior / 3 -信用历史较差不让注册
     */
    public static int register(String name, String address, String DOB, int type) {
        if(isValidDate(DOB)) {
            switch (type) {
                case JUNIOR:
                    if(isJunior(DOB)){
                        if(CreditAgency.hasSatisfacoryCreditHistory(name, address, DOB)) {
                            JuniorAccount juniorAccount = new JuniorAccount(name, address, DOB);
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
            return INCORRECT_FORMAT;
        }
    }

    /**
     * 判断时间格式 格式必须为“YYYY-MM-dd”
     * 并且DOB不能大于现在时间
     * @param str
     * @return true/false
     */
    public static boolean isValidDate(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try{
            formatter.setLenient(false);
            Date DOB = formatter.parse(str);
            //DOB不能超过当前时间
            if(DOB.before(new Date())){
                return true;
            }
            else {
                return false;
            }
        }catch(Exception e){
            return false;
        }
    }

    /**
     * 判断是不是Junior
     * @param str -Date of birth
     * @return true/false
     */
    public static boolean isJunior(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar now = Calendar.getInstance();
        int nowYear = now.get(Calendar.YEAR);
        formatter.setLenient(false);
        try {
            Date DOB = formatter.parse(str);
            // 把DOB转换到now里面，因为Calendar类才可以提取年份
            now.setTime(DOB);
            // 如果小于16岁
            if(nowYear - now.get(Calendar.YEAR) < 16) {
                return true;
            }
            else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static BankAccount getAccount() {
        return account;
    }

    public static void setAccount(BankAccount account) {
        Bank.account = account;
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
     * 在账户进行更改后立即调用此函数
     * 把更改保存到本地
     */
    public static void update() {
        saverAccountList.update();
        currentAccountList.update();
        juniorAccountList.update();
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
            // 如果申请过notice
            if(saverAccount.getWithdrawalIsAllowed()) {
                result = saverAccount.withdraw(amount);
                update();
            }
            else {
                result = SaverAccount.HAS_NOT_NOTICED;
                update();
            }

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

    //todo close
    //todo 检查输入格式 可以在GUI上设置只能接受数字的框
}
