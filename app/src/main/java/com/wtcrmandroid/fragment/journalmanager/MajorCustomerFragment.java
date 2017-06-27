package com.wtcrmandroid.fragment.journalmanager;

import android.widget.ListView;

import com.wtcrmandroid.R;
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
    protected void init() {
        mDatas = new ArrayList<>();
        getData();
        mAdapter = new MajorCustomerAdapter(getActivity(),mDatas);
        mLvMajorFragment.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public void getData() {
        for (int i = 0; i <5 ; i++) {
            MajorCustomerData majorCustomerData = new MajorCustomerData();
            majorCustomerData.setWorkSort("A类");
            majorCustomerData.setCustomerName("小东");
            majorCustomerData.setWorkAnalysis("救你不用谢");
            mDatas.add(majorCustomerData);
        }
    }

    @Override
    public void returnData(int key, Object data) {

    }
}
