package com.wtcrmandroid.activity.journalmanager;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.BaseActivity;
import com.wtcrmandroid.adapter.listview.WriteDayPlanAdapter;
import com.wtcrmandroid.custompricing.TitleBar;
import com.wtcrmandroid.model.WriteDayplanData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 销售写日计划activity
 *
 * @author zxd
 * @date 2017/6/14
 */

public class XsWriteDayplanActivity extends BaseActivity {

    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.tv_dayplan_date)
    TextView mTvDayplanDate;            //日志日期
    @BindView(R.id.ib_dayplan_calender)
    ImageButton mIbDayplanCalender;     //选择日期按钮
    @BindView(R.id.lv_xs_dayplan)
    ListView mLvXsDayplan;              //日计划列表
    @BindView(R.id.tv_dayplan_submit)
    TextView mTvDayplanSubmit;          //提交按钮
    private WriteDayPlanAdapter mWriteDayPlanAdapter;
    private List<WriteDayplanData> mDataList;

    public static final int SINGLE = 0;
    public static final int MAJOR = 1;


    @Override
    protected int layout() {
        return R.layout.activity_xs_write_dayplan;
    }

    @Override
    protected void initview() {

        mTitlebar.setTitletext("写日计划");
        mDataList = new ArrayList<>();
        WriteDayplanData dayplanData = new WriteDayplanData();
        dayplanData.setWorkSort("A类");
        mDataList.add(dayplanData);
        mWriteDayPlanAdapter = new WriteDayPlanAdapter(this, mDataList);
        mLvXsDayplan.setAdapter(mWriteDayPlanAdapter);

        View view = LayoutInflater.from(this).inflate(R.layout.item_xs_xrjh_foot, null);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        mLvXsDayplan.addFooterView(view);
        //在增加一项
        viewHolder.mLlDayplanAddjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WriteDayplanData dayplanData1 = new WriteDayplanData();
                dayplanData1.setWorkSort("C类");
                mDataList.add(dayplanData1);
                mWriteDayPlanAdapter.notifyDataSetChanged();

                Log.e("zxd",mDataList.toString());
            }
        });

        //预测到单客户情况分析
        viewHolder.mRlDayplanAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(XsWriteDayplanActivity.this,
                        SingleCustomer.class),SINGLE);
            }
        });

        //重点意向客户跟进情况
        viewHolder.mRlDayplanGenjin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(XsWriteDayplanActivity.this,
                        MajorCustomerActivity.class),MAJOR);
            }
        });
    }

    //返回结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SINGLE){

        }else if (requestCode == MAJOR){

        }
    }

    @Override
    public void returnData(int key, Object data) {

    }

    static class ViewHolder {
        @BindView(R.id.ll_dayplan_addjob)
        LinearLayout mLlDayplanAddjob;
        @BindView(R.id.rl_dayplan_analysis)
        RelativeLayout mRlDayplanAnalysis;
        @BindView(R.id.tv_genjin)
        TextView mTvGenjin;
        @BindView(R.id.rl_dayplan_genjin)
        RelativeLayout mRlDayplanGenjin;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
