package com.wtcrmandroid.baidumap;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import static com.baidu.location.LocationClientOption.LOC_SENSITIVITY_HIGHT;

/**
 * Created by wt-pc on 2017/7/29.
 */

public class MyBDLocation implements BDLocationListener {
    Context context;
    public LocationClient mLocationClient = null;
    public MyBDLocation(Context context) {
        this.context=context;
        mLocationClient = new LocationClient(context);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系
        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.disableCache(true);//禁止启用缓存定位
        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要
        option.setOpenAutoNotifyMode(0, 50, LOC_SENSITIVITY_HIGHT);
//        option.setScanSpan(5000);
//        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
//        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
        mLocationClient.registerLocationListener(this);

    }


    @Override
    public void onReceiveLocation(BDLocation bdLocation) {

        if (bdLocation != null && (bdLocation.getLocType() ==
                BDLocation.TypeGpsLocation || bdLocation.getLocType() ==BDLocation.TypeNetWorkLocation)) {
            Intent intent = new Intent();
            intent.setAction("com.wtcrmandroid.myLocation");
            //要发送的内容
            intent.putExtra("location", bdLocation);
            //发送 一个无序广播
            context.sendBroadcast(intent);
        }
    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }

    public void start(){
        if(mLocationClient.isStarted()){
            mLocationClient.requestLocation();
            Toast.makeText(context,"重新定位", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"开始定位", Toast.LENGTH_SHORT).show();
            mLocationClient.start();
        }
    }
}
