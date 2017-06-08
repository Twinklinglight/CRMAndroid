package com.wtcrmandroid;

import android.app.Application;

import com.iflytek.cloud.SpeechUtility;

/**
 * Created by 1363655717 on 2017-06-07.
 */

public class MyApplication extends Application{
    public String userAgent;
    @Override
    public void onCreate() {
        super.onCreate();
        // 应用程序入口处调用，避免手机内存过小，杀死后台进程后通过历史intent进入Activity造成SpeechUtility对象为null
        SpeechUtility.createUtility(MyApplication.this, "appid=" +"5937b406");

    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
