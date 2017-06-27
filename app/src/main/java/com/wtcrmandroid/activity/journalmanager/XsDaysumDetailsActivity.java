package com.wtcrmandroid.activity.journalmanager;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.adapter.fragment.XsDayplanAdapter;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.fragment.journalmanager.DaysumAddCustomerFragment;
import com.wtcrmandroid.fragment.journalmanager.DaysumHkdzFragment;
import com.wtcrmandroid.fragment.journalmanager.DaysumSingleCustomerFragment;
import com.wtcrmandroid.fragment.journalmanager.DaysumWorkCountFragment;
import com.wtcrmandroid.fragment.journalmanager.DaysumWorkPlanFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 销售日总结详情页
 *
 * @author zxd
 * @date 2017/6/12
 */

public class XsDaysumDetailsActivity extends BaseActivity {

    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.tv_journal_type)
    TextView mTvJournalType;                    //日总结标题
    @BindView(R.id.tab_daysum_details)
    TabLayout mTabDaysumDetails;                //tab
    @BindView(R.id.vp_dayplan_details)
    ViewPager mVpDayplanDetails;              //vp
    private XsDayplanAdapter mXsDayplanAdapter;

    private List<String> mStringList;
    private List<Fragment> mFragmentList;

    @Override
    protected int layout() {
        return R.layout.activity_xs_daysum_details;
    }

    @Override
    protected void initView() {
        mStringList = new ArrayList<>();
        mFragmentList = new ArrayList<>();

        mStringList.add("工作总结");
        mStringList.add("今日工作量");
        mStringList.add("回款到单");
        mStringList.add("预测到单客户踩中");
        mStringList.add("新增意向客户");

        mFragmentList.add(new DaysumWorkPlanFragment());
        mFragmentList.add(new DaysumWorkCountFragment());
        mFragmentList.add(new DaysumHkdzFragment());
        mFragmentList.add(new DaysumSingleCustomerFragment());
        mFragmentList.add(new DaysumAddCustomerFragment());
        mXsDayplanAdapter = new XsDayplanAdapter(getSupportFragmentManager(), mStringList, mFragmentList);
        mVpDayplanDetails.setAdapter(mXsDayplanAdapter);

        mTabDaysumDetails.setupWithViewPager(mVpDayplanDetails);    //关联vp
        //设置指示器颜色
        mTabDaysumDetails.setSelectedTabIndicatorColor(ContextCompat.getColor(this,R.color.colorPrimary));
    }


    @Override
    public void returnData(int key, Object data) {

    }

    static class ViewHolder {
        @BindView(R.id.tv_comment_count)
        TextView mTvCommentCount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
