package com.wtcrmandroid.adapter.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 销售日计划详情的适配器
 * Created by zxd on 2017/6/12
 */

public class XsDayplanAdapter extends FragmentPagerAdapter {

    private List<String>mTitleList;
    private List<Fragment>mFragmentList;

    public XsDayplanAdapter(FragmentManager fm, List<String> titleList, List<Fragment> fragmentList) {
        super(fm);
        mTitleList = titleList;
        mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return mTitleList.get(position);
    }
}
