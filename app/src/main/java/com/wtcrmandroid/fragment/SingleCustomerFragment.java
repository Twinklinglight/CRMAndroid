package com.wtcrmandroid.fragment;

import android.widget.ListView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.listview.SingleFragmentAdapter;
import com.wtcrmandroid.model.SingleCustomerData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by zxd on 2017/6/12
 */

public class SingleCustomerFragment extends BaseFragmengt {

    @BindView(R.id.lv_single_fragment)
    ListView mLvSingleFragment;

    private List<SingleCustomerData> mData;
    private SingleFragmentAdapter mAdapter;


    @Override
    protected int Rlayout() {
        return R.layout.fragment_singlecustomer_details;
    }

    @Override
    protected void init() {

        mData = new ArrayList<>();
        getData();
        mAdapter = new SingleFragmentAdapter(getActivity(),mData);
        mLvSingleFragment.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public void getData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            SingleCustomerData mDatas = new SingleCustomerData();
            mDatas.setWorkSort("A");
            mDatas.setWorkName("张三");
            mDatas.setWorkPercent("50%");
            mDatas.setWorkAnalysis("就是个这");

            mData.add(mDatas);
        }
    }

    @Override
    public void returnData(int key, Object data) {

    }
}
