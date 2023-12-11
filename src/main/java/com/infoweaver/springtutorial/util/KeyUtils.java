package com.infoweaver.springtutorial.util;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Ruobing Shang 2023-09-28 16:56
 */
@Slf4j
public class KeyUtils {
    public static String encryption(String key) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] shaBytes = md.digest(key.getBytes());
            return bytesToHexString(shaBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException SHA-256");
        }
    }

    public static String decryption(String key) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] shaBytes = md.digest(key.getBytes());
            return bytesToHexString(shaBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException SHA-256");
        }
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        for (byte aByte : bytes) {
            int v = aByte & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(encryption("RaccoonHome"));
    }
}