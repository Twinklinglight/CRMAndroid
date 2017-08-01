package com.wtcrmandroid.activity.journalmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.base.BaseActivity;
import com.wtcrmandroid.adapter.listview.MajorCustomerAtAdapter;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.model.reponsedata.MajorCustomerData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 重点意向客户activity
 *
 * @author zxd
 * @date 2017/6/8
 */

public class MajorCustomerActivity extends BaseActivity {


    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.tv_major_save)
    TextView mTvMajorSave;          //保存提交按钮
    @BindView(R.id.lv_majorcustomer)
    ListView mLvMajor;     //列表

    private ViewHolder viewHolder;
    List<MajorCustomerData> mDataList;
    private MajorCustomerAtAdapter mAtAdapter;

    public static final int RESULT_CODE = 3;
    private static final String TAG = "MajorCustomerActivity";

    @Override
    protected int layout() {
        return R.layout.activity_major_customer;
    }

    @Override
    protected void initView() {

        mTitlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MajorCustomerActivity.this.finish();
            }
        });
        mTitlebar.setTitletext("重点意向客户");

        mDataList = (List<MajorCustomerData>)getIntent().getExtras().getSerializable("major");
        if (mDataList.size()>0){

            mAtAdapter = new MajorCustomerAtAdapter(this, mDataList);
            mLvMajor.setAdapter(mAtAdapter);
            mAtAdapter.notifyDataSetChanged();

        }else {
            mDataList = new ArrayList<>();
            MajorCustomerData majorCustomerData = new MajorCustomerData();
            majorCustomerData.setWorkSort("");
            mDataList.add(majorCustomerData);
            mAtAdapter = new MajorCustomerAtAdapter(this, mDataList);
            mLvMajor.setAdapter(mAtAdapter);
            mAtAdapter.notifyDataSetChanged();
        }

        View view = LayoutInflater.from(this).inflate(R.layout.item_xrz_foot, null);
        viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        mLvMajor.addFooterView(view);

        viewHolder.mRlAddjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MajorCustomerData majorCustomerData1 = new MajorCustomerData();
                mDataList.add(majorCustomerData1);
                mAtAdapter.notifyDataSetChanged();
            }
        });


    }


    @OnClick(R.id.tv_major_save)
    public void onViewClicked() {
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("MajorList",(Serializable) mDataList);
        intent.putExtras(bundle);
        this.setResult(RESULT_CODE,intent);
        this.finish();
    }

    @Override
    public void returnData(int key, Object data) {

    }

    static class ViewHolder {
        @BindView(R.id.rl_addjob)
        LinearLayout mRlAddjob;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
