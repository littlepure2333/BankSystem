package com.littlepure.util;

import java.util.Date;

public class numberUtil {
    /**
     * Generate unique number.
     * @return unique number
     */
    public static long generateNo() {
        return new Date().getTime();
    }
}
