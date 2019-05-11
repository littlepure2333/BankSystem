package com.littlepure;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bank {
    public static final int JUNIOR = 0;
    public static final int CURRENT = 1;
    public static final int SAVER =2;

    public static long generateNo() {
        long nowDate = new Date().getTime();
        return nowDate;
    }

    public static boolean register(String name, String address, String DOB, int type) {
        return true;
    }

    /**
     * 判断时间格式 格式必须为“YYYY-MM-dd”
     * @param str
     * @return
     */
    public static boolean isValidDate(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try{
            formatter.setLenient(false);
            formatter.parse(str);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    //todo 创建 Junior Account时检查年龄
    //todo 创建账户时让用户设置密码
}
