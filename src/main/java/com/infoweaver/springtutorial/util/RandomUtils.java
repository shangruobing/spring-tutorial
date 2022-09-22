package com.infoweaver.springtutorial.util;

import java.util.Random;

/**
 * @author Ruobing Shang 2022-09-02 15:42
 */
public class RandomUtils {
    private static final Random RANDOM = new Random();

    public static String getNumberString(int length) {
        String str = "0123456789";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = RANDOM.nextInt(10);
            stringBuilder.append(str.charAt(number));
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(RandomUtils.getNumberString(20));
    }
}