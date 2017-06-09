package com.wtcrmandroid.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 类说明 SharedPreferences封装类
 * Created by zxd on 2017/6/9
 */

public class SharedPreferencesUtil {

    /**
     * @param  mContext
     *            上下文，来区别哪一个activity调用的
     * @param  whichSp
     *            使用的SharedPreferences的名字
     * @param  field
     *            SharedPreferences的哪一个字段
     */
    private static final String SP_NAME = "wutong";


    // 取出whichSp中field字段对应的string类型的值
    public static String getSharePreStr(Context mContext, String whichSp, String field) {
        SharedPreferences sp = (SharedPreferences) mContext.getSharedPreferences(whichSp, 0);
        return sp.getString(field, "");
    }

    // 取出whichSp中field字段对应的int类型的值
    public static int getSharePreInt(Context mContext, String whichSp, String field) {
        SharedPreferences sp = (SharedPreferences) mContext.getSharedPreferences(whichSp, 0);
        return sp.getInt(field, 0);
    }

    // 取出whichSp中field字段对应的boolean类型的值
    public static boolean getSharePreBoolean(Context mContext, String whichSp, String field) {
        SharedPreferences sp = (SharedPreferences) mContext.getSharedPreferences(whichSp, 0);
        return sp.getBoolean(field, false);
    }

    // 保存string类型的value到whichSp中的field字段
    public static void putSharePre(Context mContext, String whichSp, String field, String value) {
        SharedPreferences sp = (SharedPreferences) mContext.getSharedPreferences(whichSp, 0);
        sp.edit().putString(field, value).apply();
    }

    // 保存int类型的value到whichSp中的field字段
    public static void putSharePre(Context mContext, String whichSp, String field, int value) {
        SharedPreferences sp = (SharedPreferences) mContext.getSharedPreferences(whichSp, 0);
        sp.edit().putInt(field, value).apply();
    }

    // 保存booble类型的value到whichSp中的field字段
    public static void putSharePre(Context mContext, String whichSp, String field, Boolean value) {
        SharedPreferences sp = (SharedPreferences) mContext.getSharedPreferences(whichSp, 0);
        sp.edit().putBoolean(field, value).apply();
    }

    public static void saveToken(String token, Context context){
        putSharePre(context,SP_NAME,"TOKEN",token);
    }


}
