package com.wtcrmandroid.fragment.journalmanager;

import android.widget.ListView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.journalmanager.XsDaysumDetailsActivity;
import com.wtcrmandroid.adapter.listview.IfgetSingleDetailsAdapter;
import com.wtcrmandroid.BaseFragment;
import com.wtcrmandroid.model.reponsedata.GetSingleCustomerData;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zxd on 2017/6/12
 */

public class DaysumSingleCustomerFragment extends BaseFragment {

    @BindView(R.id.lv_single_fragment)
    ListView mLvSingleFragment;

    private IfgetSingleDetailsAdapter mAdapter;

    @Override
    protected int Rlayout() {
        return R.layout.fragment_daysum_singlecustomer;
    }

    @Override
    public void init() {

        XsDaysumDetailsActivity activity = (XsDaysumDetailsActivity)getActivity();
        List<GetSingleCustomerData> workdream = activity.DaysumData.getWork().getWorkdream();

        mAdapter = new IfgetSingleDetailsAdapter(getActivity(),workdream);
        mLvSingleFragment.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void returnData(int key, Object data) {

    }
}
