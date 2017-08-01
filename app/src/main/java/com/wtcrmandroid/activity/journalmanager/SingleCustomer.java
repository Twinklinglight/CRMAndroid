package com.wtcrmandroid.activity.journalmanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.wtcrmandroid.base.BaseActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.listview.SingleCustomerAdapter;
import com.wtcrmandroid.model.reponsedata.SingleCustomerData;
import com.wtcrmandroid.view.custompricing.TitleBar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 预测到单客户activity
 *
 * @author zxd
 * @date 2017/6/8
 */
public class SingleCustomer extends BaseActivity {


    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.lv_singlecustomer)
    ListView mLvSinglecustomer;
    private SingleCustomerAdapter mCustomerAdapter;
    private List<SingleCustomerData> mDataList;
    public static final int RESUlT_CODE = 2;
    private static final String TAG = "SingleCustomer";

    @Override
    protected int layout() {
        return R.layout.activity_single_customer;
    }

    @Override
    protected void initView() {

        mTitlebar.setTitletext("预测到单客户");
        mTitlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleCustomer.this.finish();
            }
        });
        mDataList = (List<SingleCustomerData>)getIntent().getExtras().getSerializable("single");
        if (mDataList.size()>0){
            mCustomerAdapter = new SingleCustomerAdapter(this, mDataList);
            mLvSinglecustomer.setAdapter(mCustomerAdapter);
            mCustomerAdapter.notifyDataSetChanged();
        }else {
            mDataList = new ArrayList<>();
            SingleCustomerData singleCustomerData = new SingleCustomerData();
            singleCustomerData.setWorkSort("");
            mDataList.add(singleCustomerData);
            mCustomerAdapter = new SingleCustomerAdapter(this, mDataList);
            mLvSinglecustomer.setAdapter(mCustomerAdapter);
            mCustomerAdapter.notifyDataSetChanged();
        }
        View view = LayoutInflater.from(this).inflate(R.layout.item_xrz_foot, null);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(view);
        mLvSinglecustomer.addFooterView(view);
        //在增加一项
        viewHolder.mRlAddjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleCustomerData singleCustomerData1 = new SingleCustomerData();
                singleCustomerData1.setWorkSort("");
                mDataList.add(singleCustomerData1);
                mCustomerAdapter.notifyDataSetChanged();
            }
        });

    }


    @Override
    public void returnData(int key, Object data) {

    }

    @OnClick(R.id.tv_single_submit)
    public void onClick() {
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("SingleList",(Serializable)mDataList);
        intent.putExtras(bundle);
        Log.i("XsWriteDayplanActivity", "-----="+mDataList.toString());
        this.setResult(RESUlT_CODE, intent);
        this.finish();
    }

    static class ViewHolder {
        @BindView(R.id.rl_addjob)
        LinearLayout mRlAddjob;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
