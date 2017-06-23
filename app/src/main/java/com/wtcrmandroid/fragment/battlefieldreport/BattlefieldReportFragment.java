package com.wtcrmandroid.fragment.battlefieldreport;

import android.support.design.widget.TabLayout;

import com.wtcrmandroid.R;
import com.wtcrmandroid.fragment.BaseFragmengt;

import butterknife.BindView;

/**
 * Created by wt-pc on 2017/6/20.
 */

public class BattlefieldReportFragment extends BaseFragmengt {
    @BindView(R.id.tl_tabs)
    TabLayout tlTabs;

    @Override
    public void returnData(int key, Object data) {

    }

    @Override
    protected int Rlayout() {
        return R.layout.frgment_batlefield_report;
    }

    @Override
    protected void init() {

    }

}