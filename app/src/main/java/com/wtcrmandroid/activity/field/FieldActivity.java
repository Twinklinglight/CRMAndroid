package com.wtcrmandroid.activity.field;

import android.content.Intent;
import android.view.View;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.view.custompricing.TitleBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 1363655717 on 2017-06-14.
 * 外勤
 */

public class FieldActivity extends BaseActivity {
    @BindView(R.id.titlebar)
    TitleBar titlebar;

    @Override
    public void returnData(int key, Object data) {

    }

    @Override
    protected int layout() {
        return R.layout.activity_field;
    }

    @Override
    protected void initView() {
        titlebar.setTitletext("外勤");
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }



    @OnClick({R.id.srl_field_clock, R.id.srl_field_statistical, R.id.srl_customer_call,R.id.srl_my_call_record, R.id.srl_customer_call_statistics, R.id.srl_employees_location})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.srl_field_clock:
                startActivity(new Intent(FieldActivity.this,FieldClockActivity.class));
                break;
            case R.id.srl_field_statistical:
                startActivity(new Intent(FieldActivity.this,FieldStatisticsAcrivity.class));
                break;
            case R.id.srl_customer_call:
                startActivity(new Intent(FieldActivity.this,CustomerCallActivity.class));
                break;
            case R.id.srl_my_call_record:
                startActivity(new Intent(FieldActivity.this,MyCallRecordActivity.class));
                break;
            case R.id.srl_customer_call_statistics:
                startActivity(new Intent(FieldActivity.this,CustomerCallStatisticalAcrivity.class));
                break;
            case R.id.srl_employees_location:
                startActivity(new Intent(FieldActivity.this,StaffPositionActivity.class));
                break;
        }
    }
}
