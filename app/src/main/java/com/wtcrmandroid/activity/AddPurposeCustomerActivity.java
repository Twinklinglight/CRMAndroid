package com.wtcrmandroid.activity;

import android.widget.ListView;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.custompricing.TitleBar;
import com.wtcrmandroid.http.retrofit2.data.BaseData;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author zxd
 * @date 2017/6/8
 */
public class AddPurposeCustomerActivity extends BaseActivity {


    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.tv_addcustomer_save)
    TextView mTvAddcustomerSave;        //完成保存
    @BindView(R.id.lv_add_customer)
    ListView mLvAddCustomer;            //列表

    @Override
    protected int layout() {
        return R.layout.activity_add_purpose_customer;
    }

    @Override
    protected void initview() {

        mTitlebar.setTitletext("新增意向客户");

    }

    @Override
    public void returnData(int key, BaseData data) {

    }


    @OnClick(R.id.tv_addcustomer_save)
    public void onViewClicked() {
    }
}
