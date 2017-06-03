package com.wtcrmandroid.activity;

import android.widget.ListView;
import android.widget.RelativeLayout;

import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.WriteDayPlanAdapter;
import com.wtcrmandroid.http.data.BaseData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class WriteDayPlanActivity extends BaseActivity {


    @BindView(R.id.lv_write_dayplan)
    ListView mLvWriteDayplan;
    @BindView(R.id.rl_dayplan_addjob)
    RelativeLayout mRlDayPlanAddjob;

    private WriteDayPlanAdapter mDayPlanAdapter;
    private List<String> mList;

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
        mDayPlanAdapter.notifyDataSetChanged();
    }

    @Override
    public void returnData(int key, BaseData data) {

    }


    @OnClick(R.id.rl_dayplan_addjob)
    public void onViewClicked() {
        mList.add("");
        mDayPlanAdapter.notifyDataSetChanged();
    }
}
