package com.wtcrmandroid.activity;

import android.widget.ListView;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.custompricing.TitleBar;
import com.wtcrmandroid.http.retrofit2.data.BaseData;

import butterknife.BindView;
import butterknife.OnClick;
/**
*   重点意向客户activity
*  @author zxd
*  @date 2017/6/8
*/

public class MajorCustomerActivity extends BaseActivity {


    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.tv_major_save)
    TextView mTvMajorSave;          //保存提交按钮
    @BindView(R.id.lv_singlecustomer)
    ListView mLvSinglecustomer;     //列表

    @Override
    public void returnData(int key, BaseData data) {

    }

    @Override
    protected int layout() {
        return R.layout.activity_major_customer;
    }

    @Override
    protected void initview() {
        mTitlebar.setTitletext("重点意向客户");
    }


    @OnClick(R.id.tv_major_save)
    public void onViewClicked() {
    }
}
