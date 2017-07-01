package com.wtcrmandroid.activity.field;

import android.hardware.Sensor;
import android.view.View;

import com.baidu.location.BDLocation;
import com.wtcrmandroid.BaseMapActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.view.custompricing.TitleBar;

import butterknife.BindView;

/**
 * Created by wt-pc on 2017/6/19.
 * 客户拜访详情
 */

public class CustomerVisitDetailsMapActivity extends BaseMapActivity {
    @BindView(R.id.titlebar)
    TitleBar titlebar;

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
    }

    @Override
    protected void getAddress(BDLocation location) {

    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
