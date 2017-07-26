package com.wtcrmandroid.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.ctoyo.protect.service.IProgressService;


/**
 * 守护进程
 */
public class GuardianService extends Service {


	@SuppressLint("NewApi")
	public void onCreate() {
		myBinder=new MyBinder();

		if(myConn==null){
			myConn=new MyConn();
		}
		Toast.makeText(GuardianService.this, "GuardianService启动中...", Toast.LENGTH_SHORT)
				.show();
	}



	@Override
	public IBinder onBind(Intent intent) {
//		return (IBinder) startS1;
		return  myBinder;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.e("s","onDestroy2");
	}

	private MyBinder myBinder;

	class MyBinder extends IProgressService.Stub{

		@Override
		public String getServiceName() throws RemoteException {
			return "GuardianService";
		}
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		GuardianService.this.bindService(new Intent(GuardianService.this,LocationService.class),myConn, Context.BIND_IMPORTANT );
		Toast.makeText(GuardianService.this, "GuardianService又onStartCommand...", Toast.LENGTH_SHORT)
				.show();
		return START_STICKY;
	}

	private MyConn myConn;
	class MyConn implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.i("Service2","程序 2 链接 程序1成功");
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Toast.makeText(GuardianService.this, "Service1 链接断开...", Toast.LENGTH_SHORT).show();
			GuardianService.this.startService(new Intent(GuardianService.this,LocationService.class));
			GuardianService.this.bindService(new Intent(GuardianService.this,LocationService.class),myConn, Context.BIND_IMPORTANT );
		}
	}
}
