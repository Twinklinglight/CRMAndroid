package com.wtcrmandroid.base;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;


/**
 * 获取UserAgent，获取IMEI
 * Created by Mr-Zhang on 2016/3/12.
 */
public class WtHeader {
    public interface OnGetUserAgentListener {
        void getUserAgentSuccess(String userAgent);

        void getUserAgentFailed();
    }

    private Context context;
//    private IWtKeyModule wtKeyBiz;
    private String wKey;
    private String ciphertext;

    public WtHeader(Context context) {
        this.context = context;
//        wtKeyBiz = new WtKeyImpl(context);
    }

    public void getUserAgent(final OnGetUserAgentListener onGetUserAgentListener) {
//        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        String deviceId = tm.getDeviceId();
//        ciphertext = "Android&" + deviceId;
//        wKey = wtKeyBiz.readFromShare();
//        if (!wKey.equals("")) {
//            onGetUserAgentListener.getUserAgentSuccess(DESUtils.encrypt(ciphertext,
//                    wKey));
//        } else {
//            wtKeyBiz.getFromServer(new WtKeyImpl.OnGetKeyFromServerListener() {
//                @Override
//                public void onGetKeyFromServerSuccess(String key) {
//                    wKey = key;
//                    wtKeyBiz.writeToShare(wKey);
//                    onGetUserAgentListener.getUserAgentSuccess(DESUtils.encrypt(ciphertext,
//                            wKey));
//                }
//
//                @Override
//                public void onGetKeyFromServerFailed() {
//                    wKey = "";
//                    onGetUserAgentListener.getUserAgentFailed();
//                }
//            });
//        }
    }

    public static String getIMEI(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    /**
     * 用于Application中初始化UserAgent；
     *
     * @param context context;
     * @return 从share中获取的 useragent
     */
    public static String getUserAgentOnlyByShare(Context context) {
//        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        String deviceId = tm.getDeviceId();
//        String ciphertext = "Android&" + deviceId;
//        IWtKeyModule wtKeyBiz = new WtKeyImpl(context);
//        String wKey = wtKeyBiz.readFromShare();
//        if (!wKey.equals("")) {
//            return DESUtils.encrypt(ciphertext,
//                    wKey);
//        }
        return "";
    }

    //版本名
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    //版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;
        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }

    public static boolean versionDif(Context context) {
        boolean versionDif = false;
        String versionCodeShare = "wuTongVersionCodeShare";
        SharedPreferences sharedPreferences = context.getSharedPreferences(versionCodeShare, Context.MODE_PRIVATE);
        String versionName = sharedPreferences.getString(versionCodeShare, "");
        String nowVersionName = WtHeader.getVersionName(context);
        if (versionName.equals("") || !versionName.equals(nowVersionName)) {
            //执行保存版本号，加载动画
            versionDif = true;
        } else {
            versionDif = false;
        }
        return versionDif;
    }

    public static void writeVersionToShare(Context context) {
        String versionCodeShare = "wuTongVersionCodeShare";
        SharedPreferences sharedPreferences = context.getSharedPreferences(versionCodeShare, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(versionCodeShare, WtHeader.getVersionName(context));
        editor.apply();
    }
}
