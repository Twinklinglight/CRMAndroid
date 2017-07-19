package com.wtcrmandroid.fragment.journalmanager;

import android.widget.ListView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.journalmanager.XsDayplanDetailsActivity;
import com.wtcrmandroid.adapter.listview.MajorCustomerAdapter;
import com.wtcrmandroid.BaseFragment;
import com.wtcrmandroid.model.MajorCustomerData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zxd on 2017/6/12
 */

public class MajorCustomerFragment extends BaseFragment {
    @BindView(R.id.lv_major_fragment)
    ListView mLvMajorFragment;
    private MajorCustomerAdapter mAdapter;
    private List<MajorCustomerData>mDatas;


    @Override
    protected int Rlayout() {
        return R.layout.fragment_majorcustomer;
    }

    @Override
    public void init() {
        XsDayplanDetailsActivity activity = (XsDayplanDetailsActivity) getActivity();
        mDatas = activity.RpData.getWorkFocus();
        mAdapter = new MajorCustomerAdapter(activity,mDatas);
        mLvMajorFragment.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void returnData(int key, Object data) {

    }
}
