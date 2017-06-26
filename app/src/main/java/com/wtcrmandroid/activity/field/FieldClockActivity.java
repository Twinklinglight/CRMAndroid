package com.wtcrmandroid.activity.field;

import android.content.Intent;
import android.hardware.Sensor;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.BaseMapActivity;
import com.wtcrmandroid.adapter.recycleview.FieldClockAdapter;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.presenter.activity.FieldClockPresenter;
import com.wtcrmandroid.utils.DensityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/6/15.
 * 外勤打卡界面
 */

public class FieldClockActivity extends BaseMapActivity<FieldClockPresenter, Object> {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.bmapView)
    MapView bmapView;
    @BindView(R.id.rv_view)
    RecyclerView rvView;
    private Double latitude;
    private Double longitude;
    private FieldClockAdapter adapter;
    @Override
    public void returnData(int key, Object data) {

    }

    @Override
    protected int layout() {
        return R.layout.activity_field_clock;
    }

    @Override
    protected void initview() {
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
                startActivity(new Intent(FieldClockActivity.this, ClockRecordActivity.class));
            }
        });

        presenter = new FieldClockPresenter(this);
        rvView.setLayoutManager(new LinearLayoutManager(this));
        rvView.setAdapter(adapter=new FieldClockAdapter(this));
    }

    @Override
    protected void getAddress(BDLocation location) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.text_location_box, null);
        ViewHolder holder = new ViewHolder(contentView);
        holder.tvAddress.setText(location.getLocationDescribe());
        latitude = location.getLatitude();
        longitude = location.getLongitude();
//定义用于显示该InfoWindow的坐标点
        LatLng pt = new LatLng(latitude, longitude);
//创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
        InfoWindow mInfoWindow = new InfoWindow(contentView, pt, -DensityUtils.dp2px(this, 15));
//显示InfoWindow
        mBaiduMap.showInfoWindow(mInfoWindow);
    }


//    @OnClick(R.id.bt_clock)
//    public void onViewClicked() {
//        PlaceSaveRequestData data = new PlaceSaveRequestData();
//        data.setUserId("3066");
//        data.setLat(longitude);
//        data.setLng(latitude);
//        data.setPositionType("1");
//        presenter.sedPost(data);
//    }

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
