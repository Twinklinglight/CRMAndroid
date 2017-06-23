package com.wtcrmandroid.fragment.journalmanager;

import android.widget.ListView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.listview.AddPurposeFragmentAdapter;
import com.wtcrmandroid.fragment.BaseFragmengt;
import com.wtcrmandroid.model.AddPurpostCtAtData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by zxd on 2017/6/12
 */

public class DaysumAddCustomerFragment extends BaseFragmengt {

    @BindView(R.id.lv_daysum_addcustomer)
    ListView mLvDaysumAddcustomer;
    private AddPurposeFragmentAdapter mAdapter;
    private List<AddPurpostCtAtData> mDataList;

    @Override
    protected int Rlayout() {
        return R.layout.fragment_daysum_addcustomer;
    }

    @Override
    protected void init() {

        mDataList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            AddPurpostCtAtData addPurpostCtAtData = new AddPurpostCtAtData();
            addPurpostCtAtData.setWorkSort("A类");
            mDataList.add(addPurpostCtAtData);
        }
        mAdapter = new AddPurposeFragmentAdapter(getActivity(),mDataList);
        mLvDaysumAddcustomer.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void returnData(int key, Object data) {

    }
}
