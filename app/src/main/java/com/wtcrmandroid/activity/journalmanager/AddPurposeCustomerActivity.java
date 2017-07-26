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
import com.wtcrmandroid.adapter.listview.AddPurposeCustomerAtAdapter;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.model.reponsedata.AddPurpostCtAtData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 新增意向客户activity
 *
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

    List<AddPurpostCtAtData> mDataList;
    private AddPurposeCustomerAtAdapter mAdapter;

    @Override
    protected int layout() {
        return R.layout.activity_add_purpose_customer;
    }

    @Override
    protected void initView() {

        mTitlebar.setTitletext("新增意向客户");
        mTitlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPurposeCustomerActivity.this.finish();
            }
        });
        mDataList = (List<AddPurpostCtAtData>)getIntent().getExtras().getSerializable("addcustinfo");

        if (mDataList.size()>0){

            mAdapter = new AddPurposeCustomerAtAdapter(this, mDataList);
            mLvAddCustomer.setAdapter(mAdapter);

        }else {

            mDataList = new ArrayList<>();
            AddPurpostCtAtData addPurpostCtAtData = new AddPurpostCtAtData();
            addPurpostCtAtData.setWorkSort("");
            mDataList.add(addPurpostCtAtData);
            mAdapter = new AddPurposeCustomerAtAdapter(this, mDataList);
            mLvAddCustomer.setAdapter(mAdapter);

        }

        View footview = LayoutInflater.from(this).inflate(R.layout.item_xrz_foot, null);
        ViewHolder viewHolder = new ViewHolder(footview);
        footview.setTag(viewHolder);
        mLvAddCustomer.addFooterView(footview);
        viewHolder.mRlAddjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPurpostCtAtData addPurpostCtAtData1 = new AddPurpostCtAtData();
                mDataList.add(addPurpostCtAtData1);
                mAdapter.notifyDataSetChanged();
            }
        });

    }


    @OnClick(R.id.tv_addcustomer_save)
    public void onViewClicked() {
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("NewCustomer",(Serializable)mDataList);
        intent.putExtras(bundle);
        this.setResult(1,intent);
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
