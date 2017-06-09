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
import com.wtcrmandroid.custompricing.TitleBar;
import com.wtcrmandroid.http.retrofit2.data.BaseData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
*  写日计划activity
*  @author zxd
*  @date 2017/6/8
*/

public class WriteDayPlanActivity extends BaseActivity {


    @BindView(R.id.lv_write_dayplan)
    ListView mLvWriteDayplan;       //工作事项列表
    @BindView(R.id.tv_dayplan_date)
    TextView mTvDayplanDate;        //显示选择时间
    @BindView(R.id.ib_dayplan_calender)
    ImageButton mIbDayplanCalender; //日历弹窗按钮
    @BindView(R.id.tv_dayplan_submit)
    TextView mTvDayplanSubmit;      //提交
    @BindView(R.id.titlebar)
    TitleBar mTitlebar;             //标题

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
        mTitlebar.setTitletext("写日计划");

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }


    static class ViewHolder1 {
        @BindView(R.id.rl_addjob)
        RelativeLayout mRlAddjob;

        ViewHolder1(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
