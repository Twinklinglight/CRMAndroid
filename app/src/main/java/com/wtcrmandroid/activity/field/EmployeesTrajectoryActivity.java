package com.wtcrmandroid.activity.field;

import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.SpatialRelationUtil;
import com.wtcrmandroid.BaseMapActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.model.reponsedata.PersonalAllRecordRP;
import com.wtcrmandroid.model.reponsedata.StaffPositionRP;
import com.wtcrmandroid.model.requestdata.EmlpoyessTrajectoryRQ;
import com.wtcrmandroid.presenter.activity.EmployeesTrajectoryP;
import com.wtcrmandroid.utils.L;
import com.wtcrmandroid.view.custompricing.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wt-pc on 2017/6/19.
 * 员工轨迹
 */

public class EmployeesTrajectoryActivity extends BaseMapActivity<EmployeesTrajectoryP,List<StaffPositionRP>> {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    BitmapDescriptor bd;
    private List<PersonalAllRecordRP> list;
    EmlpoyessTrajectoryRQ data;
    @Override
    public void returnData(int key, final List<StaffPositionRP> list) {
        L.e(list.size()+"");
       if(list!=null&&list.size()>0) {
            L.e("1");
           Thread t=new Thread(new MyRunnable(list));//这里比第一种创建线程对象多了个任务对象
           t.start();

        }else {
            showShortToast("没有该员工当天的轨迹信息！");
        }
    }

    @Override
    protected int layout() {
        return R.layout.activity_customer_visit_details_map;
    }

    @Override
    protected void initview() {
        Location();
        titlebar.setTitletext("员工轨迹");

        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        presenter=new EmployeesTrajectoryP(this,this);
        data=new EmlpoyessTrajectoryRQ();
        data.setUserName(getIntent().getStringExtra("name"));
        presenter.getData(data,0);


    }

    @Override
    protected void getAddress(BDLocation location) {

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }



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

    private class MyRunnable implements Runnable {
        private List<StaffPositionRP> list;
        public MyRunnable(List<StaffPositionRP> list) {
            this.list=list;
        }

        @Override
        public void run() {
            // 构造折线点坐标
            final List<LatLng> points = new ArrayList<LatLng>();
            LatLng latLng = null;
            for (int i = 0; i < this.list.size(); i++) {
                StaffPositionRP data = this.list.get(i);
                if(i==0){
                    latLng=new LatLng(data.getLat(), data.getLng());
                    points.add(latLng);
                }else {
                    LatLng pt=new LatLng(data.getLat(), data.getLng());
                    if(!SpatialRelationUtil.isCircleContainsPoint(latLng, 50, pt)){
                        points.add(latLng);
                        latLng=pt;
                    }
                }


            }
            L.e(points.size()+"---------------------------"+points.toString());
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    //已在主线程中，可以更新UI
                    OverlayOptions ooPolyline = new PolylineOptions().width(15).color(R.color.colorPrimary)
                            .points(points);
//        添加在地图中
                    Polyline mPolyline = (Polyline) mBaiduMap.addOverlay(ooPolyline);
                    View contentView = LayoutInflater.from(EmployeesTrajectoryActivity.this).inflate(R.layout.overlay_view, null);
                    StaffPositionActivity.ViewHolder holder = new StaffPositionActivity.ViewHolder(contentView);
                    holder.tvAddress.setText(data.getUserName());
                    StaffPositionRP bean = list.get(0);
                    L.e("3");
                    //定义用于显示该InfoWindow的坐标点
                    LatLng pt = new LatLng(bean.getLat(), bean.getLng());
                    bd = BitmapDescriptorFactory.fromBitmap(getBitmapFromView(contentView));
                    MarkerOptions oo = new MarkerOptions().icon(bd).
                            position(pt);
                    mBaiduMap.addOverlay(oo);
                    bd.recycle();
                    L.e("4");



                }
            });

        }
    }
}
