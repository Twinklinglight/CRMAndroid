package com.wtcrmandroid.fragment;

import com.wtcrmandroid.R;
import com.wtcrmandroid.view.MyListView;

import butterknife.BindView;

/**
 * Created by zxd on 2017/6/12
 */

public class DaysumSingleCustomerFragment extends BaseFragmengt {

    @BindView(R.id.lv_single_fragment)
    MyListView mLvSingleFragment;

    @Override
    protected int Rlayout() {
        return R.layout.fragment_daysum_singlecustomer;
    }

    @Override
    protected void init() {

    }

}
