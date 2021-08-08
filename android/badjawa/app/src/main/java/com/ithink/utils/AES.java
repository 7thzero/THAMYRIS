package com.ithink.utils;

import android.util.Base64;
import java.io.PrintStream;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
public class AES {
    public static String Encrypt(String str, String str2) throws Exception {
        if (str2 == null) {
            System.out.print("Key为空null");
            return null;
        } else if (str2.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        } else {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes("utf-8"), "AES");
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
            instance.init(1, secretKeySpec);
            return Base64.encodeToString(instance.doFinal(str.getBytes("utf-8")), 0);
        }
    }

    public static String Decrypt(String str, String str2) throws Exception {
        if (str2 == null) {
            try {
                System.out.print("Key为空null");
                return null;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } else if (str2.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        } else {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes("utf-8"), "AES");
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
            instance.init(2, secretKeySpec);
            try {
                return new String(instance.doFinal(Base64.decode(str, 2)), "utf-8");
            } catch (Exception e2) {
                System.out.println(e2.toString());
                return null;
            }
        }
    }

    public static void main(String[] strArr) throws Exception {
        String Decrypt = Decrypt("zZmqTgiHENQuI+/wE6APFpzU+tb8mfH2Aqu28EEvgHQ=", "0123456789abcdef");
        PrintStream printStream = System.out;
        printStream.println("解密后的字串是：" + Decrypt);
    }
}
