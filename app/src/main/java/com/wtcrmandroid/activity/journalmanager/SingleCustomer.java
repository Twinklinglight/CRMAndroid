package com.wtcrmandroid.activity.journalmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.wtcrmandroid.R;
import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.adapter.listview.SingleCustomerAdapter;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.model.SingleCustomerData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    @Override
    protected int layout() {
        return R.layout.activity_single_customer;
    }

    @Override
    protected void initview() {

        mTitlebar.setTitletext("预测到单客户");
        mDataList = new ArrayList<>();
        SingleCustomerData singleCustomerData = new SingleCustomerData();
        singleCustomerData.setWorkSort("A类");
        mDataList.add(singleCustomerData);
        mCustomerAdapter = new SingleCustomerAdapter(this, mDataList);
        mLvSinglecustomer.setAdapter(mCustomerAdapter);

        View view = LayoutInflater.from(this).inflate(R.layout.item_xrz_foot, null);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(view);
        mLvSinglecustomer.addFooterView(view);
        //在增加一项
        viewHolder.mRlAddjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleCustomerData singleCustomerData1 = new SingleCustomerData();
                singleCustomerData1.setWorkSort("B类");
                mDataList.add(singleCustomerData1);
                mCustomerAdapter.notifyDataSetChanged();
            }
        });

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
