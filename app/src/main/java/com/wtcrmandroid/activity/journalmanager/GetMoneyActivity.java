package com.wtcrmandroid.activity.journalmanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.base.BaseActivity;
import com.wtcrmandroid.adapter.listview.GetMoneyAtAdapter;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.model.reponsedata.GetMoneyData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 回款到单
 *
 * @author zxd
 * @date 2017/6/8
 */

public class GetMoneyActivity extends BaseActivity {

    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.tv_getmoney_save)
    TextView mTvGetmoneySave;       //完成保存
    @BindView(R.id.lv_getmoney)
    ListView mLvGetmoney;           //列表
    private List<GetMoneyData> mDataList;
    private GetMoneyAtAdapter mMoneyAtAdapter;
    public static final int GETMONEY_CODE = 1;
    private static final String TAG = "GetMoneyActivity";


    @Override
    protected int layout() {
        return R.layout.activity_get_money;
    }

    @Override
    protected void initView() {
        mTitlebar.setTitletext("回款到单");
        mTitlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetMoneyActivity.this.finish();
            }
        });

        mDataList = (List<GetMoneyData>)getIntent().getExtras().getSerializable("workload");
        if (mDataList.size()>0){
            mMoneyAtAdapter = new GetMoneyAtAdapter(this, mDataList);
            mLvGetmoney.setAdapter(mMoneyAtAdapter);
            mMoneyAtAdapter.notifyDataSetChanged();
        }else {
            mDataList = new ArrayList<>();
            GetMoneyData getMoneyData = new GetMoneyData();
            getMoneyData.setCustomerName("");
            mDataList.add(getMoneyData);
            mMoneyAtAdapter = new GetMoneyAtAdapter(this, mDataList);
            mLvGetmoney.setAdapter(mMoneyAtAdapter);
            mMoneyAtAdapter.notifyDataSetChanged();
        }

        View footview = LayoutInflater.from(this).inflate(R.layout.item_xrz_foot, null);
        ViewHolder viewHolder = new ViewHolder(footview);
        footview.setTag(footview);
        mLvGetmoney.addFooterView(footview);

        viewHolder.mRlAddjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetMoneyData getMoneyData1 = new GetMoneyData();
                mDataList.add(getMoneyData1);
                mMoneyAtAdapter.notifyDataSetChanged();
            }
        });
    }


    @OnClick(R.id.tv_getmoney_save)
    public void onViewClicked() {
        Log.i(TAG, "onViewClicked: WorkLoad = "+mDataList.toString());
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("WorkLoad",(Serializable)mDataList);
        intent.putExtras(bundle);
        this.setResult(GETMONEY_CODE,intent);
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
