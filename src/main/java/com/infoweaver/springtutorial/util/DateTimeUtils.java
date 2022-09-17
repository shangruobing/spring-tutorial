package com.infoweaver.springtutorial.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Ruobing Shang 2022-09-17 23:06
 */
public class DateTimeUtils {
    private static final String PATTERN_DATE = "yyyy-MM-dd";
    private static final String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public static LocalDateTime getNowDateTime() {
        return LocalDateTime.now();
    }

    public static LocalDate getNowDate() {
        return LocalDate.now();
    }

    public static LocalTime getNowTime() {
        return LocalTime.now();
    }

    public static int getNowYear() {
        return LocalDate.now().getYear();
    }

    public static int getNowMonth() {
        return LocalDate.now().getMonth().getValue();
    }

    public static int getNowDay() {
        return LocalDate.now().getDayOfMonth();
    }

    public static void main(String[] args) {
        System.out.println(DateTimeUtils.getNowDateTime());
        System.out.println(DateTimeUtils.getNowDate());
        System.out.println(DateTimeUtils.getNowTime());
        System.out.println(DateTimeUtils.getNowYear());
        System.out.println(DateTimeUtils.getNowMonth());
        System.out.println(DateTimeUtils.getNowDay());
    }
}
