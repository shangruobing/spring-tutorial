package com.infoweaver.springtutorial.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Ruobing Shang 2022-09-17 23:06
 */
public class DateTimeUtils {
    public static final String PATTERN_DATE = "yyyy-MM-dd";
    public static final String PATTERN_TIME = "HH:mm:ss";
    public static final String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(PATTERN_DATE);
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(PATTERN_TIME);
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(PATTERN_DATE_TIME);

    public static LocalDate getNowDate() {
        return LocalDate.now();
    }

    public static String getNowDateStr() {
        return LocalDate.now().format(DATE_FORMATTER);
    }

    public static LocalTime getNowTime() {
        return LocalTime.now();
    }

    public static String getNowTimeStr() {
        return LocalTime.now().format(TIME_FORMATTER);
    }

    public static LocalDateTime getNowDateTime() {
        return LocalDateTime.now();
    }

    public static String getNowDateTimeStr() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER);
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
        System.out.println("Now Date Time");
        System.out.println(DateTimeUtils.getNowDateTime());
        System.out.println(DateTimeUtils.getNowDateTimeStr());

        System.out.println("Now Date");
        System.out.println(DateTimeUtils.getNowDate());
        System.out.println(DateTimeUtils.getNowDateStr());

        System.out.println("Now Time");
        System.out.println(DateTimeUtils.getNowTime());
        System.out.println(DateTimeUtils.getNowTimeStr());

        System.out.println("Now Year Month Day");
        System.out.println(DateTimeUtils.getNowYear());
        System.out.println(DateTimeUtils.getNowMonth());
        System.out.println(DateTimeUtils.getNowDay());
    }
}
