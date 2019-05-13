package com.littlepure.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class dateUtilTest {

    @Test
    void testIsValidDate() {
        String str1 = "2013-04-4";
        String str2 = "2013-2-30";
        String str3 = "2023-4-15";
        assertTrue(dateUtil.isValidDate(str1));
        assertFalse(dateUtil.isValidDate(str2));
        assertFalse(dateUtil.isValidDate(str3));
    }

    @Test
    void testIsJunior() {
        String str1 = "2013-04-4";
        String str2 = "2000-04-4";
        assertTrue(dateUtil.isJunior(str1));
        assertFalse(dateUtil.isJunior(str2));
    }

}