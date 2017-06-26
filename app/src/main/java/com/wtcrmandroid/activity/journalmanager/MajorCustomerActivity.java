package com.wtcrmandroid.activity.journalmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.BaseActivity;
import com.wtcrmandroid.adapter.listview.MajorCustomerAtAdapter;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.model.MajorCustomerData;

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

    List<MajorCustomerData> mDataList;
    private MajorCustomerAtAdapter mAtAdapter;


    @Override
    protected int layout() {
        return R.layout.activity_major_customer;
    }

    @Override
    protected void initview() {

        mTitlebar.setTitletext("重点意向客户");
        mDataList = new ArrayList<>();
        MajorCustomerData majorCustomerData = new MajorCustomerData();
        majorCustomerData.setWorkSort("B类");
        mDataList.add(majorCustomerData);
        mAtAdapter = new MajorCustomerAtAdapter(this, mDataList);
        mLvMajor.setAdapter(mAtAdapter);

        View view = LayoutInflater.from(this).inflate(R.layout.item_xrz_foot, null);
        ViewHolder viewHolder = new ViewHolder(view);
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
    }

    @Override
    public void returnData(int key, Object data) {

    }

    static class ViewHolder {
        @BindView(R.id.rl_addjob)
        RelativeLayout mRlAddjob;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
