package com.ithink.utils;

import java.io.PrintStream;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import u.aly.TTypeStaticByteValueAllocationCl;

public class AESEncryptor {
    private static final String HEX = "0123456789ABCDEF";

    public static String encrypt(String str, String str2) throws Exception {
        return toHex(encrypt(getRawKey(str.getBytes()), str2.getBytes()));
    }

    public static String decrypt(String str, String str2) throws Exception {
        if (str == null || "".equals(str) || str2 == null || "".equals(str2)) {
            return null;
        }
        return new String(decrypt(getRawKey(str.getBytes()), toByte(str2)));
    }

    private static byte[] getRawKey(byte[] bArr) throws Exception {
        KeyGenerator instance = KeyGenerator.getInstance("AES");
        SecureRandom instance2 = SecureRandom.getInstance("SHA1PRNG", "Crypto");
        instance2.setSeed(bArr);
        instance.init(128, instance2);
        return instance.generateKey().getEncoded();
    }

    private static byte[] encrypt(byte[] bArr, byte[] bArr2) throws Exception {
        if (bArr == null || bArr2 == null) {
            return null;
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher instance = Cipher.getInstance("AES");
        instance.init(1, secretKeySpec);
        return instance.doFinal(bArr2);
    }

    private static byte[] decrypt(byte[] bArr, byte[] bArr2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher instance = Cipher.getInstance("AES");
        instance.init(2, secretKeySpec);
        return instance.doFinal(bArr2);
    }

    public static String toHex(String str) {
        return toHex(str.getBytes());
    }

    public static String fromHex(String str) {
        return new String(toByte(str));
    }

    public static byte[] toByte(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = Integer.valueOf(str.substring(i2, i2 + 2), 16).byteValue();
        }
        return bArr;
    }

    public static String toHex(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b : bArr) {
            appendHex(stringBuffer, b);
        }
        return stringBuffer.toString();
    }

    private static void appendHex(StringBuffer stringBuffer, byte b) {
        stringBuffer.append(HEX.charAt((b >> 4) & 15));
        stringBuffer.append(HEX.charAt(b & TTypeStaticByteValueAllocationCl.m));
    }

    public static void main(String[] strArr) {
        try {
            String encrypt = encrypt("1234", "13760133492");
            PrintStream printStream = System.out;
            printStream.println("加密后：" + encrypt);
            decrypt("1234", encrypt);
            PrintStream printStream2 = System.out;
            printStream2.println("解密后：" + decrypt("1234", encrypt));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
