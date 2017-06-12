package com.wtcrmandroid.fragment;

import android.widget.ListView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.listview.HtDayplanDetailsAdapter;
import com.wtcrmandroid.model.HtDayplanDetailsData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zxd on 2017/6/12
 */

public class WorkPlanFragment extends BaseFragmengt {

    @BindView(R.id.lv_work_plan)
    ListView mLvWorkPlan;

    private HtDayplanDetailsAdapter mAdapter;

    private List<HtDayplanDetailsData> mData;

    @Override
    protected int Rlayout() {
        return R.layout.fragment_workplan;
    }

    @Override
    protected void init() {
        mData = new ArrayList<>();
        getData();
        mAdapter = new HtDayplanDetailsAdapter(getActivity(),mData);
        mLvWorkPlan.setAdapter(mAdapter);
    }

    public void getData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            HtDayplanDetailsData htDayplanDetailsData = new HtDayplanDetailsData();
            htDayplanDetailsData.setWorkSort("A");
            htDayplanDetailsData.setWorkPerson("张三");
            htDayplanDetailsData.setWorkPercent("50%");
            htDayplanDetailsData.setWorkContent("就是个这");

            mData.add(htDayplanDetailsData);
        }
    }

}
