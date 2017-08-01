package com.wtcrmandroid.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.wtcrmandroid.service.utils.ServiceUtils;
import com.wtcrmandroid.utils.L;

/**
 * Created by wt-pc on 2017/7/26.
 * 监听开机和解锁
 *
 */

public class RadioReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isLocalServiceWork = ServiceUtils.isServiceWork(context, "com.wtcrmandroid.service.LocationService");
        boolean isRemoteServiceWork = ServiceUtils.isServiceWork(context, "com.wtcrmandroid.service.GuardianService");
        if(!isLocalServiceWork||
                !isRemoteServiceWork){
            context.startService(new Intent(context,GuardianService.class));

        }

        if(android.os.Build.VERSION.SDK_INT>=21){
            if(ServiceUtils.isServiceWork(context, "com.wtcrmandroid.service.JobScheduleService")) {
                context. startService(new Intent(context, JobScheduleService.class));
            }
        }
        L.e("gb","手机状态改变");
    }
}
