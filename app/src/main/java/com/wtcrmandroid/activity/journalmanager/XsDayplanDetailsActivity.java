package com.wtcrmandroid.activity.journalmanager;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.BaseActivity;
import com.wtcrmandroid.adapter.fragment.XsDayplanAdapter;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.fragment.journalmanager.MajorCustomerFragment;
import com.wtcrmandroid.fragment.journalmanager.SingleCustomerFragment;
import com.wtcrmandroid.fragment.journalmanager.WorkPlanFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class XsDayplanDetailsActivity extends BaseActivity {

    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.tv_journal_type)
    TextView mTvJournalType;            //日志详情标题
    @BindView(R.id.tab_dayplan_details)
    TabLayout mTabDayplanDetails;       //tab
    @BindView(R.id.vp_dayplan_details)
    ViewPager mVpDayplanDetails;        //vp
    private XsDayplanAdapter mAdapter;

    private List<String> mTitleList;    //tablayout 标题列表
    private List<Fragment> mFragmentList; //vp中fragment集合


    @Override
    protected int layout() {
        return R.layout.activity_xs_dayplan_details;
    }

    @Override
    protected void initview() {

        mTitlebar.setTitletext("日志详情");
        mTitlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initVp();

    }

    private void initVp() {
        mTitleList = new ArrayList<>();
        mFragmentList = new ArrayList<>();
        mTitleList.add("工作计划");
        mTitleList.add("预测到单客户");
        mTitleList.add("重点意向客户");

        mFragmentList.add(new WorkPlanFragment());
        mFragmentList.add(new SingleCustomerFragment());
        mFragmentList.add(new MajorCustomerFragment());

        mAdapter = new XsDayplanAdapter(getSupportFragmentManager(),mTitleList,mFragmentList);
        mVpDayplanDetails.setAdapter(mAdapter);
        mTabDayplanDetails.setupWithViewPager(mVpDayplanDetails);
        mTabDayplanDetails.setSelectedTabIndicatorColor(ContextCompat.getColor(this,R.color.colorPrimary));
    }


    @Override
    public void returnData(int key, Object data) {

    }
}
