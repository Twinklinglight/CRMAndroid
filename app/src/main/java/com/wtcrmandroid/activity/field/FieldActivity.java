package com.wtcrmandroid.activity.field;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.BaseActivity;
import com.wtcrmandroid.custompricing.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    protected void initview() {
        titlebar.setTitletext("外勤");
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.srl_field_clock, R.id.srl_field_statistical, R.id.srl_customer_call, R.id.srl_customer_call_statistics, R.id.srl_employees_location})
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
            case R.id.srl_customer_call_statistics:
                break;
            case R.id.srl_employees_location:
                break;
        }
    }
}
