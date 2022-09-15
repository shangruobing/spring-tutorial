package com.infoweaver.springtutorial.util;

/**
 * @author Ruobing Shang 2022-09-15 21:47
 */
public class StringUtils {


    public static boolean isNotBlank(String str) {
        return !str.isBlank();
    }

    public static boolean isNotEmpty(String str) {
        return !str.isEmpty();
    }
}
