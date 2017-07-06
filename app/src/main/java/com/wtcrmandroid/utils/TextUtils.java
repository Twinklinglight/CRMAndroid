package com.wtcrmandroid.utils;

/**
 * Created by wt-pc on 2017/7/5.
 * 文本处理工具
 */

public class TextUtils {
    public static String NameText(String text) {
        int length = text.length();
        if (length > 2) {
            return text.substring(length - 2);
        } else {
            return text;
        }
    }

    public static boolean isNull(String text) {
        if (text.length() > 0) {
            return true;
        } else {
            return false;
        }

    }
}
