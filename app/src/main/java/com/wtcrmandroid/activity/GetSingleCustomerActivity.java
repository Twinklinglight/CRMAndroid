package com.wtcrmandroid.activity;

import android.widget.ListView;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.custompricing.TitleBar;

import butterknife.BindView;
import butterknife.OnClick;

/** 预测单客户踩中activity
 * @author zxd
 * @date 2017/6/8
 */
public class GetSingleCustomerActivity extends BaseActivity {

    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.tv_dayplan_submit)
    TextView mTvDayplanSubmit;
    @BindView(R.id.lv_get_singlecustomer)
    ListView mLvGetSinglecustomer;

    @Override
    protected int layout() {
        return R.layout.activity_get_single_customer;
    }

    @Override
    protected void initview() {

    }


    @OnClick(R.id.tv_dayplan_submit)
    public void onViewClicked() {
    }

    @Override
    public void returnData(int key, Object data) {

    }
}
