package com.wtcrmandroid.fragment;

import com.wtcrmandroid.R;
import com.wtcrmandroid.view.MyListView;

import butterknife.BindView;

/**
 * Created by zxd on 2017/6/12
 */

public class DaysumWorkPlanFragment extends BaseFragmengt {

    @BindView(R.id.lv_work_plan)
    MyListView mLvWorkPlan;

    @Override
    protected int Rlayout() {
        return R.layout.fragment_daysum_workplan;
    }

    @Override
    protected void init() {

    }

}
