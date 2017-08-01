package com.wtcrmandroid.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.wtcrmandroid.R;
import com.wtcrmandroid.baidumap.MyBDLocation;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

import butterknife.ButterKnife;

/**
 * 地图Activity基类
 * 申中佳 2017-05-31
 *
 * @param <T> 网络返回实体类对象
 */


public abstract class BaseMapActivity<T extends BasePresenter, T1> extends AppCompatActivity implements AllView<T1>, SensorEventListener {
    protected T presenter;
    public MyBDLocation myBDLocation;
    protected MapView mMapView;
    protected BaiduMap mBaiduMap;
    private MyLocationConfiguration.LocationMode mCurrentMode;
    BitmapDescriptor mCurrentMarker;
    private SensorManager mSensorManager;
    private Double lastX = 0.0;
    private int mCurrentDirection = 0;
    private double mCurrentLat = 0.0;
    private double mCurrentLon = 0.0;
    private float mCurrentAccracy;
    boolean isFirstLoc = true; // 是否首次定位
    private MyLocationData locData;
    private float direction;
    MapStatus.Builder builder = null;
    protected float zoom = 15.0f;
    MyBroadcastReciver myBroadcastReciver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBundle(savedInstanceState);
        setContentView(layout());
        ButterKnife.bind(this);
        // 地图初始化
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        // 隐藏百度的LOGO
        View child = mMapView.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)) {
            child.setVisibility(View.INVISIBLE);
        }
        // 不显示地图上比例尺
        mMapView.showScaleControl(false);
        myBDLocation = MyApplication.application.myBDLocation;

        initview();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.wtcrmandroid.myLocation");
        this.registerReceiver(myBroadcastReciver=new MyBroadcastReciver(), intentFilter);
    }

    private class MyBroadcastReciver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("com.wtcrmandroid.myLocation")) {
                BDLocation bdLocation = intent.getParcelableExtra("location");
                initMap(bdLocation);


            }
        }
    }

    /**
     * 定位成功标注地图
     * @param bdLocation
     */
    public void initMap(BDLocation bdLocation){
        mCurrentLat = bdLocation.getLatitude();
        mCurrentLon = bdLocation.getLongitude();
        mCurrentAccracy = bdLocation.getRadius();
        locData = new MyLocationData.Builder()
                .accuracy(bdLocation.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(mCurrentDirection).latitude(bdLocation.getLatitude())
                .longitude(bdLocation.getLongitude()).build();
        mBaiduMap.setMyLocationData(locData);
        LatLng ll = new LatLng(bdLocation.getLatitude(),
                bdLocation.getLongitude());
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(ll).zoom(zoom);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        getAddress(bdLocation);
    }
    protected void Location() {
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);//获取传感器管理服务
        mCurrentMode = MyLocationConfiguration.LocationMode.FOLLOWING;
        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(
                mCurrentMode, true, mCurrentMarker));

    }

    public void startLocation() {
        myBDLocation.start();

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        double x = sensorEvent.values[SensorManager.DATA_X];
        if (Math.abs(x - lastX) > 1.0) {
            mCurrentDirection = (int) x;
            locData = new MyLocationData.Builder()
                    .accuracy(mCurrentAccracy)
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(mCurrentLat)
                    .longitude(mCurrentLon).build();
            mBaiduMap.setMyLocationData(locData);
        }
        lastX = x;

    }


    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
        //为系统的方向传感器注册监听器
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_UI);
        if (!isLocationEnabled()) {
            dialog();
        } else {
            BDLocation bdLocation=myBDLocation.mLocationClient.getLastKnownLocation();
            if(bdLocation!=null) {
                initMap(bdLocation);
            }else {
                myBDLocation.start();
            }
        }
    }

    protected void dialog() {
        AlertDialog.Builder builder = null;
        if (builder == null) {
            builder = new AlertDialog.Builder(this);
            builder.setMessage("定位服务未开启!请开启定位服务!否则该页面功能无法正常使用！");

            builder.setTitle("提示");

            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
            });

            builder.setNegativeButton("退出该页面", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
        }
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

    @Override
    protected void onStop() {
        //取消注册传感器监听
        mSensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {

        //取消注册传感器监听
        mSensorManager.unregisterListener(this);
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        unregisterReceiver(myBroadcastReciver);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }

    /**
     * 加载布局
     *
     * @return
     */
    protected abstract int layout();

    /**
     * 初始化方法
     */
    protected abstract void initview();

    protected abstract void getAddress(BDLocation location);

    @Override
    public void showShortToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();

    }

    public void getBundle(Bundle savedInstanceState) {

    }


}