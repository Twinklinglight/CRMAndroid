package com.wtcrmandroid.activity.journalmanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.model.requestdata.WorkOrder;
import com.wtcrmandroid.view.custompricing.TitleBar;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 今日工作量activity
 *
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

    public static final int WORKORDER_CODE = 1;

    private static final String TAG = "TodayWorkLoadActivity";
    @Override
    protected int layout() {
        return R.layout.activity_today_work_load;
    }

    @Override
    protected void initView() {

        mTitlebar.setTitletext("今日工作量");
        mTitlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodayWorkLoadActivity.this.finish();
            }
        });


    }


    @Override
    public void returnData(int key, Object data) {

    }

    @OnClick(R.id.tv_major_save)
    public void onClick() {
        WorkOrder workOrder = new WorkOrder();
        workOrder.setTrueVisit(mEtSmCount.getText().toString());
        workOrder.setTrueCall(mEtPhoneNumber.getText().toString());
        workOrder.setAStore(mEtACustomer.getText().toString());
        workOrder.setBStore(mEtBCustomer.getText().toString());
        workOrder.setNewAStore(mEtAddA.getText().toString());
        workOrder.setNewBStore(mEtAddB.getText().toString());
        workOrder.setStroe(mEtKrl.getText().toString());

        Bundle extras = new Bundle();
        extras.putSerializable("TodayLoad",workOrder);
        Intent intent = getIntent();
        intent.putExtras(extras);
        this.setResult(WORKORDER_CODE,intent);
        this.finish();
    }
}
