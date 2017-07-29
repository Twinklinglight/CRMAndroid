package com.wtcrmandroid.service;

import android.annotation.SuppressLint;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


import com.wtcrmandroid.service.utils.ServiceUtils;


@SuppressLint("NewApi")
public class JobScheduleService extends JobService {
    private int kJobId = 0;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("job", "jobService启动");
        scheduleJob(getJobInfo());
        return START_NOT_STICKY;
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.i("job", "onStartJob");
        boolean isLocalServiceWork = ServiceUtils.isServiceWork(this, "com.wtcrmandroid.service.LocationService");
        boolean isRemoteServiceWork = ServiceUtils.isServiceWork(this, "com.wtcrmandroid.service.GuardianService");
        if(!isLocalServiceWork||
                !isRemoteServiceWork){
//            this.startService(new Intent(this,LocationService.class));
            this.startService(new Intent(this,GuardianService.class));
            Toast.makeText(this, "进程复活", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i("job", "onStopJob");
        scheduleJob(getJobInfo());
        return true;
    }
    //将任务作业发送到作业调度中去
    public void scheduleJob(JobInfo t) {
        Log.i("job ", "scheduleJob");
        JobScheduler tm =
                (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        tm.schedule(t);
    }

    public JobInfo getJobInfo(){
        JobInfo.Builder builder = new JobInfo.Builder(kJobId++, new ComponentName(this, JobScheduleService.class));
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        //重启后是否还要继续执行，此时需要声明权限RECEIVE_BOOT_COMPLETED
        //否则会报错“java.lang.IllegalArgumentException: Error: requested job be persisted without holding RECEIVE_BOOT_COMPLETED permission.”
        //而且RECEIVE_BOOT_COMPLETED需要在安装的时候就要声明，如果一开始没声明，在升级时才声明，那么依然会报权限不足的错误
        builder.setPersisted(true);
        builder.setRequiresCharging(true);//是否在充电时执行
        builder.setRequiresDeviceIdle(true);//是否在空闲时执行

        builder.setPeriodic(100);//设置时间间隔，单位毫秒
        return builder.build();
    }


}