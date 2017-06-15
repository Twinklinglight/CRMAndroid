package com.wtcrmandroid.activity;

import android.view.View;

import com.wtcrmandroid.R;
import com.wtcrmandroid.custompricing.TitleBar;

import butterknife.BindView;

/**
 * Created by 1363655717 on 2017-06-14.
 * 外勤
 */

public class FieldAcrivity extends BaseActivity {
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
}
