package com.infoweaver.springtutorial.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Ruobing Shang 2022-09-04 21:19
 */
public class KeyGenerator {
    public static String encryption(String key) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] shaBytes = md.digest(key.getBytes());
        return bytesToHexString(shaBytes);
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


}
