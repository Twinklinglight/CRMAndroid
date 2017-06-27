package com.wtcrmandroid.activity.journalmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.adapter.listview.WriteDayPlanAdapter;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.model.WriteDayplanData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 写日计划activity
 *
 * @author zxd
 * @date 2017/6/8
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
    private List<WriteDayplanData> mList;
    private ViewHolder1 mViewHolder1;

    @Override
    protected int layout() {
        return R.layout.activity_write_day_plan;
    }

    @Override
    protected void initView() {

        mTitlebar.setTitletext("写日计划");
        mList = new ArrayList<>();
        WriteDayplanData writeDayplanData = new WriteDayplanData();
        writeDayplanData.setWorkSort("A类");
        mList.add(writeDayplanData);
        mDayPlanAdapter = new WriteDayPlanAdapter(this, mList);
        mLvWriteDayplan.setAdapter(mDayPlanAdapter);
        mDayPlanAdapter.notifyDataSetChanged();

        if (mList.size() > 0) {

            View footview = LayoutInflater.from(this).inflate(R.layout.item_xrz_foot, null);
            mViewHolder1 = new ViewHolder1(footview);
            mLvWriteDayplan.addFooterView(footview);

            mDayPlanAdapter.notifyDataSetChanged();

            mViewHolder1.mRlAddjob.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    WriteDayplanData writeDayplanData1 = new WriteDayplanData();
                    writeDayplanData1.setWorkSort("B类");
                    mList.add(writeDayplanData1);
                    mDayPlanAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public void returnData(int key, Object data) {

    }


    static class ViewHolder1 {
        @BindView(R.id.rl_addjob)
        RelativeLayout mRlAddjob;

        ViewHolder1(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
