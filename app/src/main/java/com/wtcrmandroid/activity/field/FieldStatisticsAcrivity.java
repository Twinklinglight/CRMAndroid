package com.wtcrmandroid.activity.field;

import android.os.Bundle;
import android.view.View;

import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.BaseActivity;
import com.wtcrmandroid.custompricing.TitleBar;
import com.wtcrmandroid.custompricing.TopChooseMenuBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 1363655717 on 2017-06-14.
 * 外勤
 */

public class FieldStatisticsAcrivity extends BaseActivity {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.tcmb_bar)
    TopChooseMenuBar tcmbBar;

    @Override
    public void returnData(int key, Object data) {

    }

    @Override
    protected int layout() {
        return R.layout.activity_field_statistics;
    }

    @Override
    protected void initview() {
        titlebar.setTitletext("外勤统计");
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tcmbBar.setOnCheckedChangedListener(new TopChooseMenuBar.OnCheckedChangedListener() {
            @Override
            public void isSelected(int i) {


            }

            @Override
            public void isNoSelected(int i) {


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
