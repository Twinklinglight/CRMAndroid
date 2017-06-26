package com.wtcrmandroid.activity.field;

import android.hardware.Sensor;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.BaseMapActivity;
import com.wtcrmandroid.view.custompricing.TitleBar;

import butterknife.BindView;

/**
 * Created by wt-pc on 2017/6/16.
 * 客户拜访
 */

public class CustomerCallActivity extends BaseMapActivity {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.tv_address)
    TextView tvAddress;

    @Override
    public void returnData(int key, Object data) {

    }

    @Override
    protected int layout() {
        return R.layout.activity_customer_call;
    }

    @Override
    protected void initview() {
        titlebar.setTitletext("客户拜访");
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void getAddress(BDLocation location) {
        tvAddress.setText("当前位置： "+location.getLocationDescribe());

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
