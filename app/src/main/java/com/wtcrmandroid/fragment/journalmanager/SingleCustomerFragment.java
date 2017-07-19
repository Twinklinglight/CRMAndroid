package com.wtcrmandroid.fragment.journalmanager;

import android.util.Log;
import android.widget.ListView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.journalmanager.XsDayplanDetailsActivity;
import com.wtcrmandroid.adapter.listview.SingleFragmentAdapter;
import com.wtcrmandroid.BaseFragment;
import com.wtcrmandroid.model.SingleCustomerData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by zxd on 2017/6/12
 */

public class SingleCustomerFragment extends BaseFragment {

    @BindView(R.id.lv_single_fragment)
    ListView mLvSingleFragment;

    private List<SingleCustomerData> mData;
    private SingleFragmentAdapter mAdapter;


    @Override
    protected int Rlayout() {
        return R.layout.fragment_singlecustomer_details;
    }

    @Override
    public void init() {
        XsDayplanDetailsActivity activity = (XsDayplanDetailsActivity) getActivity();
        mData = activity.RpData.getWorkDreamOrder();
        Log.i("----------","mData = "+mData);
        mAdapter = new SingleFragmentAdapter(activity,mData);
        Log.i("----------","mAdapter = "+mAdapter);
        mLvSingleFragment.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        Log.i("----------","mAdapter = "+mAdapter);
    }

    @Override
    public void returnData(int key, Object data) {

    }
}
