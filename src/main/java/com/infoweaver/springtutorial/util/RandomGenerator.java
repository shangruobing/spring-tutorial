package com.infoweaver.springtutorial.util;

import java.util.Random;

/**
 * @author Ruobing Shang 2022-09-02 15:42
 */
public class RandomGenerator {
    private static final Random RANDOM = new Random();

    public static String getNumberString(int length) {
        String str = "0123456789";
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = RANDOM.nextInt(10);
            stringBuffer.append(str.charAt(number));
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(RandomGenerator.getNumberString(20));
    }
}