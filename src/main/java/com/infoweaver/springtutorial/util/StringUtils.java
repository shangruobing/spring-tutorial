package com.infoweaver.springtutorial.util;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author Ruobing Shang 2022-09-15 21:47
 */
public class StringUtils {

    public static boolean isBlank(String str) {
        return str.isBlank();
    }

    public static boolean isNotBlank(String str) {
        return !str.isBlank();
    }

    public static boolean isNotEmpty(String str) {
        return !str.isEmpty();
    }

    public static boolean isContains(String str, List<String> list) {
        for (String item : list) {
            if (item.contains(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotContains(String str, List<String> list) {
        return !isContains(str, list);
    }

    public static String bytesToString(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }
}