package com.wtcrmandroid.activity.field;

import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.wtcrmandroid.base.BaseMapActivity;
import com.wtcrmandroid.base.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.model.reponsedata.StaffPositionRP;
import com.wtcrmandroid.model.requestdata.DepartmentRquestData;
import com.wtcrmandroid.presenter.activity.StaffPositionP;
import com.wtcrmandroid.view.custompricing.TitleBar;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wt-pc on 2017/6/19.
 * 员工位置
 */

public class StaffPositionActivity extends BaseMapActivity<StaffPositionP,List<StaffPositionRP>> {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    BitmapDescriptor bd;
    private List<StaffPositionRP> list;
    DepartmentRquestData data;
    @Override
    public void returnData(int key, final List<StaffPositionRP> list) {

        for (int i = 0; i < list.size(); i++) {
            StaffPositionRP data = list.get(i);
            View contentView = LayoutInflater.from(this).inflate(R.layout.overlay_view, null);
            ViewHolder holder = new ViewHolder(contentView);
            holder.tvAddress.setText(data.getUserName());
            //定义用于显示该InfoWindow的坐标点
            LatLng pt = new LatLng(data.getLat(),data.getLng());
            bd = BitmapDescriptorFactory.fromBitmap(getBitmapFromView(contentView));
            MarkerOptions oo = new MarkerOptions().icon(bd).
                    position(pt).zIndex(100);
            Bundle des = new Bundle();
            des.putInt("des", i);
            oo.extraInfo(des);
            mBaiduMap.addOverlay(oo);

        }



        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (marker != null) {
                    StaffPositionRP bean = list.get(marker.getExtraInfo().getInt("des"));
                    startActivity(new Intent(StaffPositionActivity.this, EmployeesTrajectoryActivity.class).putExtra("name", bean.getUserName()));
                    return true;
                } else {
                    return false;
                }
            }
        });
        bd.recycle();
    }

    @Override
    protected int layout() {
        return R.layout.activity_customer_visit_details_map;
    }

    @Override
    protected void initview() {
        Location();
        titlebar.setTitletext("员工位置");
        titlebar.setRightImageResource(R.mipmap.ic_white_search);
        titlebar.setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StaffPositionActivity.this,StaffPositionSearchActivity.class).putExtra("list", (Serializable) list));

            }
        });
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        presenter=new StaffPositionP(this,this);
        data=new DepartmentRquestData();
        data.setUserId(MyApplication.application.getLoginData().getUserID());
        presenter.getData(data,0);
    }

    @Override
    protected void getAddress(BDLocation location) {

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    //定位按钮点击事件
    @OnClick(R.id.iv_positioning)
    public void onViewClicked() {
        startLocation();
    }


    static class ViewHolder {
        @BindView(R.id.tv_address)
        TextView tvAddress;
        @BindView(R.id.tv_number)
        TextView tvNumber;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public Bitmap getBitmapFromView(View view) {
        view.destroyDrawingCache();
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = view.getDrawingCache(true);
        return bitmap;
    }
}
