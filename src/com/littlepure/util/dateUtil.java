package com.littlepure.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class dateUtil {
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


}
