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
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.wtcrmandroid.BaseMapActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.model.reponsedata.PersonalAllRecordRP;
import com.wtcrmandroid.view.custompricing.TitleBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/6/19.
 * 客户拜访详情
 */

public class CustomerVisitDetailsMapActivity extends BaseMapActivity {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    BitmapDescriptor bd;
    private List<PersonalAllRecordRP> list;

    @Override
    public void returnData(int key, Object data) {

    }

    @Override
    protected int layout() {
        return R.layout.activity_customer_visit_details_map;
    }

    @Override
    protected void initview() {
        titlebar.setTitletext("客户拜访详情");
        titlebar.setRightText("列表模式");
        titlebar.setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        list = (List<PersonalAllRecordRP>) getIntent().getExtras().getSerializable("list");
        for (int i = 0; i < list.size(); i++) {
            PersonalAllRecordRP data = list.get(i);
            View contentView = LayoutInflater.from(this).inflate(R.layout.overlay_view, null);
            ViewHolder holder = new ViewHolder(contentView);
            holder.tvAddress.setText(data.getCustomerName());
            holder.tvNumber.setText((i+1)+"");
            //定义用于显示该InfoWindow的坐标点
            LatLng pt = new LatLng(data.getLng(), data.getLat());
            bd = BitmapDescriptorFactory.fromBitmap(getBitmapFromView(contentView));
            MarkerOptions oo = new MarkerOptions().icon(bd).
                    position(pt).zIndex(100);
            Bundle des = new Bundle();
            des.putInt("des", i);
            oo.extraInfo(des);
            mBaiduMap.addOverlay(oo);

        }
        bd.recycle();
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (marker != null) {
                    PersonalAllRecordRP bean = list.get(marker.getExtraInfo().getInt("des"));
                    startActivity(new Intent(CustomerVisitDetailsMapActivity.this, CustomerCallSituationActivity.class).putExtra("customerid", bean.getCustomerid()).putExtra("customerName", bean.getCustomerName()));
                    return true;
                } else {
                    return false;
                }
            }
        });

    }

    @Override
    protected void getAddress(BDLocation location) {
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(14.0f);
        mBaiduMap.setMapStatus(msu);
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

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
