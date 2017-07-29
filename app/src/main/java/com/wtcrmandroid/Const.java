package com.wtcrmandroid;

import android.os.Environment;

import java.io.File;

/**
 * 配置
 * Created by Mr-Zhang on 2016/3/12.
 */
public class Const {
    public final static  String http ="http://192.168.0.7/api/";  //金磊
    public final static  String WT_CRM ="WT_CRM";
    public final static  String TOKEN ="TOKEN";
    public final static  String USERID ="USERID";
    public final static  String IMEI ="IMEI";
    //    public final static  String http ="http://192.168.0.33/api/";   //黄埔
    public final static String httpString = "http://";
    public final static String httpsString = "http://";
    public static final String DOWN_KEY_URL = httpsString + "android.chinawutong.com/GetKey.ashx";// 下载密钥口令
    public static final String DOWNKEY = "WtAndroid123";

    // apk存储路径
    public static final String SDCARD_PATH = Environment
            .getExternalStorageDirectory().getAbsolutePath()
            + File.separatorChar + "wutong" + File.separatorChar + "crm" + File.separatorChar;
    public static final String PHOTO_PATH = SDCARD_PATH + "wtphoto" + File.separatorChar;// 拍照,调用相册,下载的缩略图
    public static final String NEW_APK = SDCARD_PATH + "wtdownload" + File.separatorChar;//apk下载地址


}
