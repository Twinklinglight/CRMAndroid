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
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.BaseMapActivity;
import com.wtcrmandroid.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.recycleview.FieldClockAdapter;
import com.wtcrmandroid.model.reponsedata.ClockRecordRP;
import com.wtcrmandroid.model.requestdata.DayCloclRecordRQ;
import com.wtcrmandroid.model.requestdata.PlaceSaveRQ;
import com.wtcrmandroid.presenter.activity.FieldClockPresenter;
import com.wtcrmandroid.utils.DensityUtils;
import com.wtcrmandroid.view.custompricing.TitleBar;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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


    private UiSettings mUiSettings;
    private FieldClockAdapter adapter;
    /**
     * 位置数据保存
     */
    private PlaceSaveRQ placeSaveRequestData;


    private DayCloclRecordRQ dayCloclRecordRQ;

    @Override
    public void returnData(int key, Object data) {
        switch (key) {
            case 0:
                showShortToast("打卡成功！");
                presenter.clockRecord(dayCloclRecordRQ, 1);
                break;
            case 1:
                List<ClockRecordRP> list = new ArrayList<>();
                Type listType = new TypeToken<List<ClockRecordRP>>() {
                }.getType();
                list = new Gson().fromJson(data.toString(), listType);
                adapter.setList(list);
                break;
        }

    }

    @Override
    protected int layout() {
        return R.layout.activity_field_clock;
    }

    @Override
    protected void initview() {
        setTitlebar();
        mUiSettings = mBaiduMap.getUiSettings();
        mUiSettings.setScrollGesturesEnabled(false);
        presenter = new FieldClockPresenter(this, this);
        int userId = MyApplication.application.getLoginData().getUserID();
        dayCloclRecordRQ = new DayCloclRecordRQ();
        dayCloclRecordRQ.setUserId(userId + "");
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DATE);       //日
        int month = cal.get(Calendar.MONTH) + 1;//月
        int year = cal.get(Calendar.YEAR);
        dayCloclRecordRQ.setToDay(year + "-" + month + "-" + day);
        presenter.clockRecord(dayCloclRecordRQ, 1);
        placeSaveRequestData = new PlaceSaveRQ();
        placeSaveRequestData.setUserId(userId);
        placeSaveRequestData.setPositionType("1");
        rvView.setLayoutManager(new LinearLayoutManager(this));
        rvView.setAdapter(adapter = new FieldClockAdapter(this));

        adapter.setOnItemOnlick(new FieldClockAdapter.OnItemOnlick() {
            @Override
            public void ClockOnlick() {
                presenter.clock(placeSaveRequestData, 0);
            }
        });
    }

    /**
     * 标题设置
     */
    private void setTitlebar() {
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
        placeSaveRequestData.setLng(latitude);
        placeSaveRequestData.setLat(longitude);
        placeSaveRequestData.setAddress(location.getLocationDescribe());
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }



    @OnClick(R.id.bt_clock)
    public void onViewClicked() {
        presenter.clock(placeSaveRequestData, 0);
    }


    static class ViewHolder {
        @BindView(R.id.tv_address)
        TextView tvAddress;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
