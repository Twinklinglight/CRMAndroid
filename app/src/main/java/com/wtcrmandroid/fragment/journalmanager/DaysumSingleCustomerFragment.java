package com.wtcrmandroid.fragment.journalmanager;

import android.widget.ListView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.listview.IfgetSingleDetailsAdapter;
import com.wtcrmandroid.fragment.BaseFragmengt;
import com.wtcrmandroid.model.GetSingleCustomerData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zxd on 2017/6/12
 */

public class DaysumSingleCustomerFragment extends BaseFragmengt {

    @BindView(R.id.lv_single_fragment)
    ListView mLvSingleFragment;

    private IfgetSingleDetailsAdapter mAdapter;
    private List<GetSingleCustomerData> mDataList;

    @Override
    protected int Rlayout() {
        return R.layout.fragment_daysum_singlecustomer;
    }

    @Override
    protected void init() {

        mDataList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            GetSingleCustomerData getSingleCustomerData = new GetSingleCustomerData();
            getSingleCustomerData.setWorkSort("B类");
            mDataList.add(getSingleCustomerData);
        }
        mAdapter = new IfgetSingleDetailsAdapter(getActivity(),mDataList);
        mLvSingleFragment.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void returnData(int key, Object data) {

    }
}
