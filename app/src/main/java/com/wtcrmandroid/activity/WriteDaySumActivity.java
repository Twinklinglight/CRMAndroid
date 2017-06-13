package com.wtcrmandroid.activity;

import com.wtcrmandroid.R;
import com.wtcrmandroid.custompricing.TitleBar;

import butterknife.BindView;

/**
*
*  @author zxd
*  @date 2017/6/6
*/

public class WriteDaySumActivity extends BaseActivity {

    @BindView(R.id.titlebar)
    TitleBar mTitlebar;

    @Override
    protected int layout() {
        return R.layout.activity_write_day_sum;
    }

    @Override
    protected void initview() {

        mTitlebar.setTitletext("写日总结");
    }


    @Override
    public void returnData(int key, Object data) {

    }
}
