package com.wtcrmandroid.utils;

import android.app.ActivityManager;
import android.content.Context;

/**
 * Created by fan on 2016/11/29.
 */

public class CheckServiceUtils {
    private Context context;
    boolean isWutongServiceRunning = false;
    boolean isPushServiceRunning = false;
    public CheckServiceUtils(Context context){
        this.context = context;
    };
    /**
     * 当程序启动后后符合条件的重启发送Gps服务、推送服务
     */
    public void checkService() {

        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager
                .getRunningServices(Integer.MAX_VALUE)) {
            if ("com.wutong.android.WutongService".equals(service.service
                    .getClassName())) {
                isWutongServiceRunning = true;
            }
            if ("com.wutong.android.PushService".equals(service.service
                    .getClassName())) {
                isPushServiceRunning = true;
            }
        }
    }

    public boolean isWutongServiceRunning() {
        return isWutongServiceRunning;
    }

    public boolean isPushServiceRunning() {
        return isPushServiceRunning;
    }
}
