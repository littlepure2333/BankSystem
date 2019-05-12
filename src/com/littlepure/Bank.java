package com.littlepure;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Bank {
    public static final int JUNIOR = 0;
    public static final int CURRENT = 1;
    public static final int SAVER = 2;
    public static final int RIGISTER_SUCCESS = 0;
    public static final int INCORRECT_FORMAT = 1;
    public static final int NOT_JUNIOR = 2;
    public static final int POOR_CREDIT_HISTORY = 3;
    public static final int UNKNOWN_TYPE = 4;

    private static JuniorAccountList juniorAccountList = new JuniorAccountList();
    private static CurrentAccountList currentAccountList = new CurrentAccountList();
    private static SaverAccountList saverAccountList = new SaverAccountList();

    static {
        juniorAccountList.loadList();
        currentAccountList.loadList();
        saverAccountList.loadList();
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
     * 给用户注册，选择类型
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
                            return RIGISTER_SUCCESS;
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
                        return RIGISTER_SUCCESS;
                    }
                    else {
                        return POOR_CREDIT_HISTORY;
                    }
                case SAVER:
                    if(CreditAgency.hasSatisfacoryCreditHistory(name, address, DOB)) {
                        SaverAccount saverAccount = new SaverAccount(name, address, DOB);
                        saverAccountList.addSaverAccount(saverAccount);
                        return RIGISTER_SUCCESS;
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

    //todo 创建 Junior Account时检查年龄
    //todo 创建账户时让用户设置密码
}
