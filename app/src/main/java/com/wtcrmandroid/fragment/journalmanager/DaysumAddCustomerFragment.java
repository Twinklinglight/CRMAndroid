package com.wtcrmandroid.fragment.journalmanager;

import android.widget.ListView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.journalmanager.XsDaysumDetailsActivity;
import com.wtcrmandroid.adapter.listview.AddPurposeFragmentAdapter;
import com.wtcrmandroid.base.BaseFragment;
import com.wtcrmandroid.model.reponsedata.AddPurpostCtAtData;

import java.util.List;

import butterknife.BindView;


/**
 * Created by zxd on 2017/6/12
 */

public class DaysumAddCustomerFragment extends BaseFragment {

    @BindView(R.id.lv_daysum_addcustomer)
    ListView mLvDaysumAddcustomer;
    private AddPurposeFragmentAdapter mAdapter;

    @Override
    protected int Rlayout() {
        return R.layout.fragment_daysum_addcustomer;
    }

    @Override
    public void init() {

        XsDaysumDetailsActivity activity = (XsDaysumDetailsActivity)getActivity();
        List<AddPurpostCtAtData> addcustinfo = activity.DaysumData.getWork().getAddcustinfo();

        mAdapter = new AddPurposeFragmentAdapter(getActivity(),addcustinfo);
        mLvDaysumAddcustomer.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void returnData(int key, Object data) {

    }
}
