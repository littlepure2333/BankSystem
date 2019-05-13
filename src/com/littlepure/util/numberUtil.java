package com.littlepure.util;

import java.util.Date;

public class numberUtil {
    /**
     * 生成全局独一无二的No
     * @return
     */
    public static long generateNo() {
        long nowDate = new Date().getTime();
        return nowDate;
    }
}
