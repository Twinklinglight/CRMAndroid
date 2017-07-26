package com.wtcrmandroid.activity.journalmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.adapter.listview.GetSingleAtAdapter;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.model.reponsedata.GetSingleCustomerData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 预测单客户踩中activity
 *
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

    private List<GetSingleCustomerData> mGetList;
    private GetSingleAtAdapter mSingleAtAdapter;
    public static final int SINGLE_CODE = 1;

    @Override
    protected int layout() {
        return R.layout.activity_get_single_customer;
    }

    @Override
    protected void initView() {

        mTitlebar.setTitletext("预测单客户踩中");
        mTitlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetSingleCustomerActivity.this.finish();
            }
        });

        mGetList = (List<GetSingleCustomerData>) getIntent().getExtras().getSerializable("workdream");
        if (mGetList.size() > 0) {

            mSingleAtAdapter = new GetSingleAtAdapter(this, mGetList);
            mLvGetSinglecustomer.setAdapter(mSingleAtAdapter);

        } else {
            mGetList = new ArrayList<>();
            GetSingleCustomerData getSingleCustomerData = new GetSingleCustomerData();
            getSingleCustomerData.setWorkSort("");
            mGetList.add(getSingleCustomerData);
            mSingleAtAdapter = new GetSingleAtAdapter(this, mGetList);
            mLvGetSinglecustomer.setAdapter(mSingleAtAdapter);
        }

        View footview = LayoutInflater.from(this).inflate(R.layout.item_xrz_foot, null);
        ViewHolder viewHolder = new ViewHolder(footview);
        footview.setTag(viewHolder);
        mLvGetSinglecustomer.addFooterView(footview);

        //再增加一项
        viewHolder.mRlAddjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetSingleCustomerData getSingleCustomerData1 = new GetSingleCustomerData();
                mGetList.add(getSingleCustomerData1);
                mSingleAtAdapter.notifyDataSetChanged();
            }
        });


    }


    @OnClick(R.id.tv_dayplan_submit)
    public void onViewClicked() {
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Single", (Serializable) mGetList);
        intent.putExtras(bundle);
        this.setResult(SINGLE_CODE, intent);
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
