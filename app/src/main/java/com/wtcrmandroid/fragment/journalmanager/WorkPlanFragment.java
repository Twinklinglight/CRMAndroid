package com.wtcrmandroid.fragment.journalmanager;

import android.widget.ListView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.journalmanager.XsDayplanDetailsActivity;
import com.wtcrmandroid.adapter.listview.HtDayplanDetailsAdapter;
import com.wtcrmandroid.base.BaseFragment;
import com.wtcrmandroid.model.reponsedata.HtDayplanDetailsData;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zxd on 2017/6/12
 */

public class WorkPlanFragment extends BaseFragment {

    @BindView(R.id.lv_work_plan)
    ListView mLvWorkPlan;

    private HtDayplanDetailsAdapter mAdapter;
    private List<HtDayplanDetailsData> mData;

    @Override
    protected int Rlayout() {
        return R.layout.fragment_workplan;
    }

    @Override
    public void init() {

        XsDayplanDetailsActivity activity= (XsDayplanDetailsActivity) getActivity();

        mData = activity.RpData.getWorkdetail();
        mAdapter = new HtDayplanDetailsAdapter(activity,mData);
        mLvWorkPlan.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void returnData(int key, Object data) {

    }
}
