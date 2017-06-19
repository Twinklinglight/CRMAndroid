package com.wtcrmandroid.activity.field;

import android.content.Intent;
import android.hardware.Sensor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.BaseMapActivity;
import com.wtcrmandroid.custompricing.TitleBar;
import com.wtcrmandroid.utils.DensityUtils;
import com.wtcrmandroid.utils.L;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/6/15.
 * 外勤打卡界面
 */

public class FieldClockActivity extends BaseMapActivity {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.bmapView)
    MapView bmapView;

    @Override
    public void returnData(int key, Object data) {

    }

    @Override
    protected int layout() {
        return R.layout.activity_field_clock;
    }

    @Override
    protected void initview() {
        L.e("fasdfa");
        titlebar.setTitletext("外勤打卡");
        titlebar.setRightText("打卡记录");
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titlebar.setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FieldClockActivity.this,ClockRecordActivity.class));
            }
        });

    }

    @Override
    protected void getAddress(BDLocation location) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.text_location_box, null);
        ViewHolder holder = new ViewHolder(contentView);
        holder.tvAddress.setText(location.getLocationDescribe());
//定义用于显示该InfoWindow的坐标点
        LatLng pt = new LatLng(location.getLatitude(), location.getLongitude());
//创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
        InfoWindow mInfoWindow = new InfoWindow(contentView, pt, -DensityUtils.dp2px(this,15));
//显示InfoWindow
        mBaiduMap.showInfoWindow(mInfoWindow);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }



    static class ViewHolder {
        @BindView(R.id.tv_address)
        TextView tvAddress;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
