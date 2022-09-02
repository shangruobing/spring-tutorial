package com.infoweaver.springtutorial.utils;

import java.util.Random;

/**
 * @author Ruobing Shang 2022-09-02 15:42
 */
public class RandomGenerator {
    public static String getNumberString(int length) {
        String str = "0123456789";
        Random random = new Random();
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(10);
            stringBuffer.append(str.charAt(number));
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(RandomGenerator.getNumberString(20));
    }
}