package com.wtcrmandroid.activity;

import android.widget.EditText;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.custompricing.TitleBar;
import com.wtcrmandroid.http.retrofit2.data.BaseData;

import butterknife.BindView;

/**
 * 今日工作量activity
 * @author zxd
 * @date 2017/6/8
 */

public class TodayWorkLoadActivity extends BaseActivity {

    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.et_phone_number)
    EditText mEtPhoneNumber;        //有效电话数量
    @BindView(R.id.et_sm_count)
    EditText mEtSmCount;            //有效上门数量
    @BindView(R.id.et_A_customer)
    EditText mEtACustomer;          //A类库存
    @BindView(R.id.et_B_customer)
    EditText mEtBCustomer;          //B类库存
    @BindView(R.id.et_add_A)
    EditText mEtAddA;               //新增A类
    @BindView(R.id.et_add_B)
    EditText mEtAddB;               //新增B类
    @BindView(R.id.et_krl)
    EditText mEtKrl;                //库存量
    @BindView(R.id.tv_major_save)
    TextView mTvMajorSave;          //保存完成


    @Override
    protected int layout() {
        return R.layout.activity_today_work_load;
    }

    @Override
    protected void initview() {

        mTitlebar.setTitletext("今日工作量");

    }

    @Override
    public void returnData(int key, BaseData data) {

    }
}
