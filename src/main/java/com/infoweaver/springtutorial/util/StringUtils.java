package com.infoweaver.springtutorial.util;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author Ruobing Shang 2023-09-28 17:34
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
        return list.stream().anyMatch(item -> item.contains(str));
    }

    public static boolean isNotContains(String str, List<String> list) {
        return !isContains(str, list);
    }

    /**
     * 用于RabbitMq的消息解码
     */
    public static String bytesToString(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }
}