package com.wtcrmandroid.activity.journalmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.BaseActivity;
import com.wtcrmandroid.adapter.listview.GetSingleAtAdapter;
import com.wtcrmandroid.custompricing.TitleBar;
import com.wtcrmandroid.model.GetSingleCustomerData;

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

    @Override
    protected int layout() {
        return R.layout.activity_get_single_customer;
    }

    @Override
    protected void initview() {

        mTitlebar.setTitletext("预测单客户踩中");
        mGetList = new ArrayList<>();
        GetSingleCustomerData getSingleCustomerData = new GetSingleCustomerData();
        getSingleCustomerData.setWorkSort("A类");
        mGetList.add(getSingleCustomerData);

        mSingleAtAdapter = new GetSingleAtAdapter(this, mGetList);
        mLvGetSinglecustomer.setAdapter(mSingleAtAdapter);

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
