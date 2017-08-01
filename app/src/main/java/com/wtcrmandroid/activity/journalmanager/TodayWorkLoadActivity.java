package com.wtcrmandroid.activity.journalmanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import com.wtcrmandroid.base.BaseActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.model.requestdata.WorkOrder;
import com.wtcrmandroid.view.custompricing.TitleBar;

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
    private String bStore;
    private String newAStore;
    private String newBStore;
    private String stroe;
    private String custom;
    private String trueCall;
    private String aStore;

    @Override
    protected int layout() {
        return R.layout.activity_today_work_load;
    }

    @Override
    protected void initView() {

        //加载之前提交数据
        WorkOrder workorder = (WorkOrder)getIntent().getExtras().getSerializable("workorder");
        if (workorder != null){
            mEtKrl.setText(workorder.getStroe());
            mEtAddA.setText(workorder.getNewAStore());
            mEtAddB.setText(workorder.getNewBStore());
            mEtSmCount.setText(workorder.getTrueVisit());
            mEtPhoneNumber.setText(workorder.getTrueCall());
            mEtACustomer.setText(workorder.getAStore());
            mEtBCustomer.setText(workorder.getBStore());
        }
        mTitlebar.setTitletext("今日工作量");
        mTitlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodayWorkLoadActivity.this.finish();
            }
        });

        mEtACustomer.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        mEtBCustomer.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        mEtAddA.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        mEtAddB.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        mEtPhoneNumber.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        mEtSmCount.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        mEtKrl.setInputType(EditorInfo.TYPE_CLASS_PHONE);


    }


    @Override
    public void returnData(int key, Object data) {

    }

    @OnClick(R.id.tv_major_save)
    public void onClick() {
        WorkOrder workOrder = new WorkOrder();
        custom = mEtSmCount.getText().toString();
        trueCall = mEtPhoneNumber.getText().toString();
        aStore = mEtACustomer.getText().toString();
        bStore = mEtBCustomer.getText().toString();
        newAStore = mEtAddA.getText().toString();
        newBStore = mEtAddB.getText().toString();
        stroe = mEtKrl.getText().toString();

        if (trueCall.equals("")){

            Toast.makeText(this, "有效电话必填", Toast.LENGTH_SHORT).show();
            return;

        }if (aStore.equals("")){
            Toast.makeText(this, "A类客户库存必填", Toast.LENGTH_SHORT).show();
            return;
        }if (bStore.equals("")){
            Toast.makeText(this, "B类客户库存必填", Toast.LENGTH_SHORT).show();
            return;
        }else {
            workOrder.setTrueVisit(custom);
            workOrder.setTrueCall(trueCall);
            workOrder.setAStore(aStore);
            workOrder.setBStore(bStore);
            workOrder.setNewAStore(newAStore);
            workOrder.setNewBStore(newBStore);
            workOrder.setStroe(stroe);

            Log.i(TAG, "onClick: workOrder = "+workOrder.toString());
            Bundle extras = new Bundle();
            extras.putSerializable("TodayLoad",workOrder);
            Intent intent = getIntent();
            intent.putExtras(extras);
            this.setResult(WORKORDER_CODE,intent);
            this.finish();
        }

    }
}
