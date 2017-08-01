
package com.wtcrmandroid.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferenceUtils {
    public static String getPrefString(Context context,String name, String key, final String defaultValue) {
        final SharedPreferences settings = getDefaultSharedPreferences(context,name);
        return settings.getString(key, defaultValue);
    }

    public static void setPrefString(Context context, String name,final String key, final String value) {
        final SharedPreferences settings = getDefaultSharedPreferences(context,name);
        settings.edit().putString(key, value).commit();
    }

    public static boolean getPrefBoolean(Context context, String name,final String key,
                                         final boolean defaultValue) {
        final SharedPreferences settings = getDefaultSharedPreferences(context,name);
        return settings.getBoolean(key, defaultValue);
    }

    public static boolean hasKey(Context context,String name, final String key) {
        return getDefaultSharedPreferences(context,name).contains(key);
    }

    public static void setPrefBoolean(Context context, String name,final String key, final boolean value) {
        final SharedPreferences settings = getDefaultSharedPreferences(context,name);
        settings.edit().putBoolean(key, value).commit();
    }

    public static void setPrefInt(Context context,String name, final String key, final int value) {
        final SharedPreferences settings = getDefaultSharedPreferences(context,name);
        settings.edit().putInt(key, value).commit();
    }

    public static int getPrefInt(Context context, String name,final String key, final int defaultValue) {
        final SharedPreferences settings = getDefaultSharedPreferences(context,name);
        return settings.getInt(key, defaultValue);
    }

    public static void setPrefFloat(Context context, String name,final String key, final float value) {
        final SharedPreferences settings = getDefaultSharedPreferences(context,name);
        settings.edit().putFloat(key, value).commit();
    }

    public static float getPrefFloat(Context context,String name, final String key, final float defaultValue) {
        final SharedPreferences settings = getDefaultSharedPreferences(context,name);
        return settings.getFloat(key, defaultValue);
    }

    public static void setSettingLong(Context context, String name,final String key, final long value) {
        final SharedPreferences settings = getDefaultSharedPreferences(context,name);
        settings.edit().putLong(key, value).commit();
    }

    public static long getPrefLong(Context context, String name,final String key, final long defaultValue) {
        final SharedPreferences settings = getDefaultSharedPreferences(context,name);
        return settings.getLong(key, defaultValue);
    }

    public static void clearPreference(Context context, final SharedPreferences p) {
        final Editor editor = p.edit();
        editor.clear();
        editor.commit();
    }

    public static SharedPreferences getDefaultSharedPreferences(Context context,String name){
        SharedPreferences pushService = context.getSharedPreferences(name, Context.MODE_MULTI_PROCESS);
        return pushService;
    }
}
