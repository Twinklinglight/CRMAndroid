package com.wtcrmandroid.fragment;

import com.wtcrmandroid.R;
import com.wtcrmandroid.view.MyListView;

import butterknife.BindView;


/**
 * Created by zxd on 2017/6/12
 */

public class DaysumAddCustomerFragment extends BaseFragmengt {

    @BindView(R.id.lv_daysum_addcustomer)
    MyListView mLvDaysumAddcustomer;

    @Override
    protected int Rlayout() {
        return R.layout.fragment_daysum_addcustomer;
    }

    @Override
    protected void init() {

    }

    @Override
    public void returnData(int key, Object data) {

    }
}
