package com.ithink.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;

public class WLANUtil {
    public static String mac = createRandom(false, 18);

    public static String getMacAddress() {
        return mac;
    }

    public static String createRandom(boolean z, int i) {
        String str;
        String str2 = z ? "1234567890" : "1234567890abcdefghijkmnopqrstuvwxyz";
        int length = str2.length();
        boolean z2 = true;
        do {
            str = "";
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                double random = Math.random();
                double d = (double) length;
                Double.isNaN(d);
                int floor = (int) Math.floor(random * d);
                char charAt = str2.charAt(floor);
                if ('0' <= charAt && charAt <= '9') {
                    i2++;
                }
                str = str + str2.charAt(floor);
            }
            if (i2 >= 2) {
                z2 = false;
                continue;
            }
        } while (z2);
        return str;
    }

    public static String getMacAddress2() {
        String str;
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address ").getInputStream()));
            String str2 = "";
            while (true) {
                if (str2 != null) {
                    str2 = lineNumberReader.readLine();
                    if (str2 != null) {
                        str = str2.trim();
                        break;
                    }
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        str = "";
        if (str == null || "".equals(str)) {
            try {
                return loadFileAsString("/sys/class/net/eth0/address").toUpperCase().substring(0, 17);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return str;
    }

    private static String loadFileAsString(String str) throws Exception {
        FileReader fileReader = new FileReader(str);
        String loadReaderAsString = loadReaderAsString(fileReader);
        fileReader.close();
        return loadReaderAsString;
    }

    private static String loadReaderAsString(Reader reader) throws Exception {
        StringBuilder sb = new StringBuilder();
        char[] cArr = new char[4096];
        int read = reader.read(cArr);
        while (read >= 0) {
            sb.append(cArr, 0, read);
            read = reader.read(cArr);
        }
        return sb.toString();
    }

    public static String getInfoName(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        if (connectionInfo.getSSID() == null) {
            return "";
        }
        return connectionInfo.getSSID().toString().replaceAll("\"", "");
    }
}
