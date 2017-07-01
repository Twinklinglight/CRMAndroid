package com.wtcrmandroid.activity.journalmanager;

import android.widget.ListView;
import android.widget.TextView;

import com.wtcrmandroid.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.activity.journalmanager.present.WeekPlanDetailsPresenter;
import com.wtcrmandroid.adapter.listview.WeekDayplanAdapter;
import com.wtcrmandroid.model.requestdata.WeekDetailsRequestData;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.model.WriterWeekPlaneData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
/**
*  周计划详情页
*  @author zxd
*  @date 2017/6/12
*/

public class WeekplanDetailsActivity extends BaseActivity<WeekPlanDetailsPresenter,List<WriterWeekPlaneData>> {

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
    protected void initView() {
        presenter = new WeekPlanDetailsPresenter(this);
        WeekDetailsRequestData weekDetailsRequestData = new WeekDetailsRequestData();
        weekDetailsRequestData.setUserId(MyApplication.application.getLoginData().getUserID());
        weekDetailsRequestData.setType("week");
        weekDetailsRequestData.setPlan(true);
        weekDetailsRequestData.setWeekBegin("2017/6/12");
        weekDetailsRequestData.setWeekEnd("2017/6/18");

        presenter.GetWeekPlanData(weekDetailsRequestData);
        mTitleBar.setTitletext("日志详情");

        mDataList = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            WriterWeekPlaneData writerWeekPlaneData = new WriterWeekPlaneData();
//            writerWeekPlaneData.setWorkNumber("本周计划"+(i+1));
//            mDataList.add(writerWeekPlaneData);
//        }

    }

    @Override
    public void returnData(int key, List<WriterWeekPlaneData> data) {
        mDataList = data;
        mAdapter = new WeekDayplanAdapter(this,mDataList);
        mLvWeekplan.setAdapter(mAdapter);
    }
}
