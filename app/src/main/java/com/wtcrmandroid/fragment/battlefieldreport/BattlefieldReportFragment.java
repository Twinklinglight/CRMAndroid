package com.wtcrmandroid.fragment.battlefieldreport;

import android.support.v4.view.ViewPager;

import com.wtcrmandroid.R;
import com.wtcrmandroid.fragment.BaseFragmengt;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by wt-pc on 2017/6/20.
 */

public class BattlefieldReportFragment extends BaseFragmengt {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    Unbinder unbinder;

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