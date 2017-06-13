package com.wtcrmandroid.activity;

import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.custompricing.TitleBar;
import com.wtcrmandroid.http.retrofit2.data.BaseData;

import butterknife.BindView;

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


    @Override
    protected int layout() {
        return R.layout.activity_xs_write_dayplan;
    }

    @Override
    protected void initview() {

    }

    @Override
    public void returnData(int key, BaseData data) {

    }
}
