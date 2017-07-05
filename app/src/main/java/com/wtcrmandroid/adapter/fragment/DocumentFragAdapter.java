package com.wtcrmandroid.adapter.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.R;

import java.util.List;

/**
 * Created by zxd on 2017/7/4.
 */

public class DocumentFragAdapter extends FragmentPagerAdapter {

    private Context context;
    private List<Fragment> fragmentList;
    private List<String> stringList;

    public DocumentFragAdapter(FragmentManager fm, Context context, List<Fragment> fragmentList, List<String> stringList) {
        super(fm);
        this.context = context;
        this.fragmentList = fragmentList;
        this.stringList = stringList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }

    public View getTabView(int position){
        View view = LayoutInflater.from(context).inflate(R.layout.item_customer_tabtitle, null);
        TextView tv= (TextView) view.findViewById(R.id.tv_title);
        tv.setText(stringList.get(position));
        return view;
    }
}
