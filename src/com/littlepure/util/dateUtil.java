package com.littlepure.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class dateUtil {
    /**
     * Check if is junior.
     * @param str Date of birth
     * @return {@code true}/{@code false} yes or not
     */
    public static boolean isJunior(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar now = Calendar.getInstance();
        int nowYear = now.get(Calendar.YEAR);
        formatter.setLenient(false);
        try {
            Date DOB = formatter.parse(str);
            // change "DOB" to "now", cause Calendar class can extract year
            now.setTime(DOB);
            // if under 16
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
     * Check if time format is correct, must be “YYYY-MM-dd”.
     * And time can't be greater than present time.
     * @param str date of birth
     * @return {@code true}/{@code false} yes or not
     */
    public static boolean isValidDate(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try{
            formatter.setLenient(false);
            Date DOB = formatter.parse(str);
            // DOB can't after present time
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
