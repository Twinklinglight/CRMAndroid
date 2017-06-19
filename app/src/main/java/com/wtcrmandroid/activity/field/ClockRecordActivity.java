package com.wtcrmandroid.activity.field;

import android.os.Bundle;
import android.view.View;

import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.BaseActivity;
import com.wtcrmandroid.custompricing.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/6/17.
 * 打卡记录
 */

public class ClockRecordActivity extends BaseActivity {
    @BindView(R.id.titlebar)
    TitleBar titlebar;

    @Override
    public void returnData(int key, Object data) {

    }

    @Override
    protected int layout() {
        return R.layout.activity_clock_record;
    }

    @Override
    protected void initview() {
        titlebar.setTitletext("打卡记录");
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
}
