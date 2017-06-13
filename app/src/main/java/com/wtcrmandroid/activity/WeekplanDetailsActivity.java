package com.wtcrmandroid.activity;

import android.widget.ListView;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.listview.WeekDayplanAdapter;
import com.wtcrmandroid.custompricing.TitleBar;
import com.wtcrmandroid.http.retrofit2.data.BaseData;
import com.wtcrmandroid.model.WriterWeekPlaneData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
/**
*  周计划详情页
*  @author zxd
*  @date 2017/6/12
*/

public class WeekplanDetailsActivity extends BaseActivity {

    @BindView(R.id.titlebar)
    TitleBar mTitleBar;
    @BindView(R.id.tv_weekplan)
    TextView mTvWeekplan;           //日志详情标题
    @BindView(R.id.lv_weekplan)
    ListView mLvWeekplan;           //日志列表

    private WeekDayplanAdapter mAdapter;
    private List<WriterWeekPlaneData>mDataList; //数据源


    @Override
    protected int layout() {
        return R.layout.activity_weekplan_details;
    }

    @Override
    protected void initview() {
        mTitleBar.setTitletext("日志详情");
        mDataList = new ArrayList<>();
        mAdapter = new WeekDayplanAdapter(this,mDataList);
        mLvWeekplan.setAdapter(mAdapter);
    }

    @Override
    public void returnData(int key, BaseData data) {

    }
}