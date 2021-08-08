package com.ithink.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtil {
    public static final String PREFERENCE_FILE_NAME = "ithink_share";
    private static Context mContext;

    private SpUtil() {
    }

    public static void config(Context context) {
        mContext = context;
    }

    public static String getShareData(String str) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREFERENCE_FILE_NAME, 0);
        String string = sharedPreferences.getString(str, "");
        if (!"".equals(string)) {
            try {
                AESEncryptor.decrypt(com.ithink.utils.UtilParam.SHA1KEY, string);
            } catch (Exception unused) {
            }
        }
        return sharedPreferences.getString(str, "");
    }

    public static String getShareData(String str, String str2) {
        String string = mContext.getSharedPreferences(PREFERENCE_FILE_NAME, 0).getString(str, str2);
        if ("".equals(string)) {
            return string;
        }
        try {
            return AESEncryptor.decrypt(com.ithink.utils.UtilParam.SHA1KEY, string);
        } catch (Exception unused) {
            return string;
        }
    }

    public static int getIntShareData(String str, int i) {
        return mContext.getSharedPreferences(PREFERENCE_FILE_NAME, 0).getInt(str, i);
    }

    public static int getIntShareData(String str) {
        return mContext.getSharedPreferences(PREFERENCE_FILE_NAME, 0).getInt(str, 0);
    }

    public static boolean getBooleanShareData(String str) {
        return mContext.getSharedPreferences(PREFERENCE_FILE_NAME, 0).getBoolean(str, false);
    }

    public static boolean getBooleanShareData(String str, boolean z) {
        return mContext.getSharedPreferences(PREFERENCE_FILE_NAME, 0).getBoolean(str, z);
    }

    public static void putShareData(String str, String str2) {
        SharedPreferences.Editor edit = mContext.getSharedPreferences(PREFERENCE_FILE_NAME, 0).edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public static void putIntShareData(String str, int i) {
        SharedPreferences.Editor edit = mContext.getSharedPreferences(PREFERENCE_FILE_NAME, 0).edit();
        edit.putInt(str, i);
        edit.commit();
    }

    public static void putBooleanShareData(String str, boolean z) {
        SharedPreferences.Editor edit = mContext.getSharedPreferences(PREFERENCE_FILE_NAME, 0).edit();
        edit.putBoolean(str, z);
        edit.commit();
    }
}
