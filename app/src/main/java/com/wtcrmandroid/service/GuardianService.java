package com.wtcrmandroid.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.ctoyo.protect.service.IProgressService;
import com.wtcrmandroid.R;


/**
 * 守护进程
 */
public class GuardianService extends Service{
    private MyConn myConn;
    private MyBinder myBinder;
    private Thread mThread;
    private boolean isRun = false;
	@SuppressLint("NewApi")
	public void onCreate() {
		myBinder=new MyBinder();

		if(myConn==null){
			myConn=new MyConn();
		}

	}



	@Override
	public IBinder onBind(Intent intent) {
//		return (IBinder) startS1;
		return  myBinder;
	}
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(myConn==null){
            myConn=new MyConn();
        }
        GuardianService.this.startService(new Intent(this,LocationService.class));
        GuardianService.this.bindService(new Intent(GuardianService.this,LocationService.class),myConn, Context.BIND_IMPORTANT );
		CreateInform();
        return START_STICKY;
    }

	//创建通知
	public void CreateInform() {
		Intent deleteIntent = new Intent(this, GuardianService.class);
		int deleteCode = (int) SystemClock.uptimeMillis();
		PendingIntent deletePendingIntent = PendingIntent.getService(this,
				deleteCode, deleteIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		//创建一个通知
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
		mBuilder.setContentTitle("测试标题")//设置通知栏标题
				.setContentText("测试内容") //设置通知栏显示内容
				.setAutoCancel(true)
				.setDeleteIntent(deletePendingIntent)
//    .setContentIntent(app.getDefalutIntent(Notification.FLAG_AUTO_CANCEL)) //设置通知栏点击意图
//  .setNumber(number) //设置通知集合的数量
				.setTicker("测试通知来啦") //通知首次出现在通知栏，带上升动画效果的
				.setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
				.setPriority(Notification.PRIORITY_DEFAULT) //设置该通知优先级
//  .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
				.setOngoing(true)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
//                .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
				//Notification.DEFAULT_ALL  Notification.DEFAULT_SOUND 添加声音 // requires VIBRATE permission
				.setSmallIcon(R.drawable.ic_arrow_back);//设置通知小ICON
		mNotificationManager.notify(100, mBuilder.build());


	}
	@Override
	public void onDestroy() {
        isRun = false;
        GuardianService.this.startService(new Intent(GuardianService.this,LocationService.class));
        GuardianService.this.bindService(new Intent(GuardianService.this,LocationService.class),myConn, Context.BIND_IMPORTANT );
        super.onDestroy();
		Log.e("s","onDestroy2");
	}



    class MyBinder extends IProgressService.Stub{

		@Override
		public String getServiceName() throws RemoteException {
			return "GuardianService";
		}
	}


	class MyConn implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.e("Service2","程序 2 链接 程序1成功");
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.e("Service2","链接断开");
			GuardianService.this.startService(new Intent(GuardianService.this,LocationService.class));
			GuardianService.this.bindService(new Intent(GuardianService.this,LocationService.class),myConn, Context.BIND_IMPORTANT );
		}
	}
}
