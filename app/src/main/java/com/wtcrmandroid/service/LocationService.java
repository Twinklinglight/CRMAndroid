package com.wtcrmandroid.service;

import android.app.AlertDialog;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.ctoyo.protect.service.IProgressService;
import com.wtcrmandroid.MyApplication;

import java.util.List;

import static com.baidu.location.LocationClientOption.LOC_SENSITIVITY_HIGHT;

/**
 * 位置服务进程
 */
public class LocationService extends Service implements Runnable {
    private boolean isRun = false;
    private Thread mThread;
    private MyApplication app;
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener;

    @Override
    public void onCreate() {

        myBinder = new MyBinder();
        if (myConn == null) {
            myConn = new MyConn();
        }
        Toast.makeText(LocationService.this, "LocationService正在启动...", Toast.LENGTH_SHORT)
                .show();

    }


    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public void onDestroy() {
        isRun = false;
        mLocationClient.stop();
        mLocationClient.unRegisterLocationListener(myListener);
        super.onDestroy();
    }

    private MyBinder myBinder;

    @Override
    public void run() {
        while (isRun) {
            Log.e("LocationService", "我还活着！");


            try {
                Thread.sleep(1000 * 1);
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
        isRun = true;
        app = (MyApplication) this.getApplicationContext();
        LocationService.this.bindService(new Intent(LocationService.this, GuardianService.class), myConn, Context.BIND_IMPORTANT);
        if (mThread == null) {
            mThread = new Thread(this);
            mThread.start();
        }
//        if (mLocationClient == null) {
//            mLocationClient = new LocationClient(app);
//            initLocation();
//            //声明LocationClient类
//            mLocationClient.registerLocationListener(myListener = new MyLocationListener());
//            mLocationClient.start();
//        }
        Log.e("LocationService", "我又onStartCommand！");
        Toast.makeText(LocationService.this, "LocationService又onStartCommand...", Toast.LENGTH_SHORT)
                .show();
        return START_STICKY;
    }

    private MyConn myConn;

    class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("LocationService", "程序 1 链接 程序2成功");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
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

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

//		int span=1000 * 10;
//		option.setScanSpan(span);
//		//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.disableCache(false);//禁止启用缓存定位
        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setOpenAutoNotifyMode(0, 10, LOC_SENSITIVITY_HIGHT);
//
//		option.setLocationNotify(true);
//		//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
//
//		option.setIsNeedLocationDescribe(true);
//		//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
//
//		option.setIsNeedLocationPoiList(true);
//		//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
//
//		option.setIgnoreKillProcess(false);
//		//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
//
//		option.SetIgnoreCacheException(false);
//		//可选，默认false，设置是否收集CRASH信息，默认收集
//
//		option.setEnableSimulateGps(false);
//		//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

        mLocationClient.setLocOption(option);
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {

            //获取定位结果
            StringBuffer sb = new StringBuffer(256);

            sb.append("time : ");
            sb.append(location.getTime());    //获取定位时间

            sb.append("\nerror code : ");
            sb.append(location.getLocType());    //获取类型类型

            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());    //获取纬度信息

            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());    //获取经度信息

            sb.append("\nradius : ");
            sb.append(location.getRadius());    //获取定位精准度

            if (location.getLocType() == BDLocation.TypeGpsLocation) {

                // GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());    // 单位：公里每小时

                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());    //获取卫星数

                sb.append("\nheight : ");
                sb.append(location.getAltitude());    //获取海拔高度信息，单位米

                sb.append("\ndirection : ");
                sb.append(location.getDirection());    //获取方向信息，单位度

                sb.append("\naddr : ");
                sb.append(location.getAddrStr());    //获取地址信息

                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {

                // 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());    //获取地址信息

                sb.append("\noperationers : ");
                sb.append(location.getOperators());    //获取运营商信息

                sb.append("\ndescribe : ");
                sb.append("网络定位成功");

            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {

                // 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");

            } else if (location.getLocType() == BDLocation.TypeServerError) {

                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");

            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {

                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");

            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {

                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");

            }

            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());    //位置语义化信息

            List<Poi> list = location.getPoiList();    // POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
            Toast.makeText(LocationService.this, sb.toString(), Toast.LENGTH_SHORT)
                    .show();
            Log.i("BaiduLocationApiDem", sb.toString());
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }
}
