package com.wtcrmandroid.activity;

import android.widget.ListView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.custompricing.TitleBar;

import butterknife.BindView;
/**
*
*  @author zxd
*  @date 2017/6/8
*/
public class SingleCustomer extends BaseActivity {


    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.lv_singlecustomer)
    ListView mLvSinglecustomer;

    @Override
    protected int layout() {
        return R.layout.activity_single_customer;
    }

    @Override
    protected void initview() {

        mTitlebar.setTitletext("预测到单客户");

    }


    @Override
    public void returnData(int key, Object data) {

    }
}
