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
import android.widget.RemoteViews;

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
		//自定义显示布局
		RemoteViews contentViews = new RemoteViews(getPackageName(),R.layout.notification_view);
		//创建一个通知
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
		mBuilder.setAutoCancel(true)
				.setDeleteIntent(deletePendingIntent)
//				.setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
				.setPriority(Notification.PRIORITY_DEFAULT) //设置该通知优先级
				.setOngoing(true)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
				.setSmallIcon(R.mipmap.ic_app)
				.setContent(contentViews);//设置通知小ICON
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
