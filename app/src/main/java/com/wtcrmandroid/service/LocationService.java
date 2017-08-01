package com.wtcrmandroid.service;

import android.app.AlertDialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.ctoyo.protect.service.IProgressService;
import com.google.gson.Gson;
import com.wtcrmandroid.Const;
import com.wtcrmandroid.MyApplication;
import com.wtcrmandroid.baidumap.MyBDLocation;
import com.wtcrmandroid.model.requestdata.PlaceSaveRQ;
import com.wtcrmandroid.utils.L;
import com.wtcrmandroid.utils.PreferenceUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 位置服务进程
 */
public class LocationService extends Service implements Runnable {
    private boolean isRun = false;
    private Thread mThread;
    private MyApplication app;
    private MyBDLocation myBDLocation;
    MyBroadcastReciver myBroadcastReciver;

    @Override
    public void onCreate() {
        myBinder = new MyBinder();
        if (myConn == null) {
            myConn = new MyConn();
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public void onDestroy() {
        isRun = false;
        if (myBroadcastReciver != null) {
            unregisterReceiver(myBroadcastReciver);
            myBroadcastReciver = null;
        }

        mThread.interrupt();
        mThread = null;
        LocationService.this.startService(new Intent(LocationService.this, GuardianService.class));
        LocationService.this.bindService(new Intent(LocationService.this, GuardianService.class), myConn, Context.BIND_IMPORTANT);
        super.onDestroy();
    }

    private MyBinder myBinder;


    @Override
    public void run() {
        while (isRun) {
            Log.e("LocationService", "我还活着！");
            try {
                Thread.sleep(1000 * 600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class MyBinder extends IProgressService.Stub {

        @Override
        public String getServiceName() throws RemoteException {
            return "LocationService";
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("LocationService", "我又onStartCommand！");
        if (app == null)
            app = (MyApplication) this.getApplicationContext();
        if (myBDLocation == null)
            myBDLocation = app.myBDLocation;
        if (myConn == null) {
            myConn = new MyConn();
        }
        LocationService.this.bindService(new Intent(LocationService.this, GuardianService.class), myConn, Context.BIND_IMPORTANT);
        isRun = true;
        if (mThread == null) {
            mThread = new Thread(this);
            mThread.start();
        }
        if (myBroadcastReciver != null) {
            unregisterReceiver(myBroadcastReciver);
            myBroadcastReciver = null;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.wtcrmandroid.myLocation");
        this.registerReceiver(myBroadcastReciver = new MyBroadcastReciver(), intentFilter);
        myBDLocation.start();
        return START_STICKY;
    }

    private class MyBroadcastReciver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("com.wtcrmandroid.myLocation")) {
                BDLocation bdLocation = intent.getParcelableExtra("location");
                setData(bdLocation);
            }
        }
    }

    private MyConn myConn;

    class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("LocationService", "程序 1 链接 程序2成功");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("LocationService", "链接断开");
            Toast.makeText(LocationService.this, "LocationService 链接断开...", Toast.LENGTH_SHORT).show();
            LocationService.this.startService(new Intent(LocationService.this, GuardianService.class));
            LocationService.this.bindService(new Intent(LocationService.this, GuardianService.class), myConn, Context.BIND_IMPORTANT);
        }
    }

    protected void dialog() {
        AlertDialog.Builder builder = null;
        if (builder == null) {
            builder = new AlertDialog.Builder(LocationService.this);
            builder.setMessage("定位服务未开启!请开启定位服务!否则该页面功能无法正常使用！");

            builder.setTitle("提示");

            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    app.startActivity(intent);
                }
            });

            builder.setNegativeButton("退出该页面", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        }
        builder.create().getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        builder.create().show();
    }

    /**
     * 判断定位服务是否开启
     *
     * @param
     * @return true 表示开启
     */
    public boolean isLocationEnabled() {
        int locationMode = 0;
        String locationProviders;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(getContentResolver(), Settings.Secure.LOCATION_MODE);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }
            return locationMode != Settings.Secure.LOCATION_MODE_OFF;
        } else {
            locationProviders = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }
    }


    private void setData(BDLocation location) {
        String userId = PreferenceUtils.getPrefString(app, Const.WT_CRM, Const.USERID, "");
        PlaceSaveRQ data = new PlaceSaveRQ();
        data.setLng(location.getLongitude());
        data.setLat(location.getLatitude());
        data.setPositionType("2");
        data.setUserId(userId);
        Gson gson = new Gson();
        String json = gson.toJson(data);
        String path = Const.http + "OutSide/savePosition";
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .header("token", PreferenceUtils.getPrefString(app, Const.WT_CRM, Const.TOKEN, ""))
                .addHeader("userid", userId)
                .addHeader("imei", PreferenceUtils.getPrefString(app, Const.WT_CRM, Const.IMEI, ""))
                .url(path)
                .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json))
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                L.e("失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                L.e(response.body().string());
            }
        });
    }
}
