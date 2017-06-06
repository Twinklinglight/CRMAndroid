package com.wtcrmandroid.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.WriteDayPlanAdapter;
import com.wtcrmandroid.http.data.BaseData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WriteDayPlanActivity extends BaseActivity {


    @BindView(R.id.lv_write_dayplan)
    ListView mLvWriteDayplan;

    ViewHolder viewHolder;

    private WriteDayPlanAdapter mDayPlanAdapter;
    private List<String> mList;
    private ViewHolder1 mViewHolder1;

    @Override
    protected int layout() {
        return R.layout.activity_write_day_plan;
    }

    @Override
    protected void initview() {

        mList = new ArrayList<>();
        mList.add("");
        mDayPlanAdapter = new WriteDayPlanAdapter(mList);
        mLvWriteDayplan.setAdapter(mDayPlanAdapter);
        View headview = LayoutInflater.from(this).inflate(R.layout.item_xrz_head, null);
        viewHolder = new ViewHolder(headview);
        mLvWriteDayplan.addHeaderView(headview);

        View footview = LayoutInflater.from(this).inflate(R.layout.item_xrz_foot, null);
        mViewHolder1 = new ViewHolder1(footview);
        mLvWriteDayplan.addFooterView(footview);

        mDayPlanAdapter.notifyDataSetChanged();

        mViewHolder1.mRlAddjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mList.add("");
                mDayPlanAdapter.notifyDataSetChanged();

            }
        });
    }

    @Override
    public void returnData(int key, BaseData data) {

    }


   /* @OnClick(R.id.rl_addjob)
    public void onViewClicked() {
        mList.add("");
        mDayPlanAdapter.notifyDataSetChanged();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }



    static class ViewHolder {
        @BindView(R.id.tv_dayplan_date)
        TextView mTvDayplanDate;
        @BindView(R.id.ib_dayplan_calender)
        ImageButton mIbDayplanCalender;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder1 {
        @BindView(R.id.rl_addjob)
        RelativeLayout mRlAddjob;

        ViewHolder1(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
