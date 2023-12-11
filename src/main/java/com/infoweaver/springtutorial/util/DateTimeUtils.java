package com.infoweaver.springtutorial.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author Ruobing Shang 2023-10-18 20:59
 */
public class DateTimeUtils {
    public static final String PATTERN_DATE = "yyyy-MM-dd";
    public static final String PATTERN_TIME = "HH:mm:ss";
    public static final String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_DATE_TIME_STAMP = "yyyyMMdd_HHmmss";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(PATTERN_DATE);
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(PATTERN_TIME);
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(PATTERN_DATE_TIME);
    public static final DateTimeFormatter DATE_TIME_STAMP_FORMATTER = DateTimeFormatter.ofPattern(PATTERN_DATE_TIME_STAMP);
    private static final String NULL = "null";

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

    public static String getNowDateTimeStampStr() {
        return LocalDateTime.now().format(DATE_TIME_STAMP_FORMATTER);
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

    public static boolean isNull(LocalDateTime localDateTime) {
        return NULL.equals(String.valueOf(localDateTime));
    }

    public static boolean isNotNull(LocalDateTime localDateTime) {
        return !NULL.equals(String.valueOf(localDateTime));
    }

    public static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    public static String formatLocalDateTime(LocalDateTime dateTime) {
        return dateTime.format(DATE_TIME_FORMATTER);
    }

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now().isBefore(LocalDateTime.parse("2023-11-02T16:35:41")));

        System.out.println("Now Date Time");
        System.out.println(DateTimeUtils.getNowDateTime());
        System.out.println(DateTimeUtils.getNowDateTimeStr());
        System.out.println(DateTimeUtils.getNowDateTimeStampStr());

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

        System.out.println(isNotNull(null));
        System.out.println(isNull(null));
    }
}
