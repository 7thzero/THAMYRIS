package com.ithink.utils;

import java.util.Random;
public class RandomNum {
    public static String genRandomNum(int i) {
        char[] cArr = {'a', 'b', 'c', 'd', 'e', 'f', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuffer stringBuffer = new StringBuffer("");
        Random random = new Random();
        int i2 = 0;
        while (i2 < i) {
            int abs = Math.abs(random.nextInt(36));
            if (abs >= 0 && abs < cArr.length) {
                stringBuffer.append(cArr[abs]);
                i2++;
            }
        }
        return stringBuffer.toString().toUpperCase();
    }
}
