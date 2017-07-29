package com.wtcrmandroid.activity.journalmanager;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.BaseFragment;
import com.wtcrmandroid.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.journalmanager.present.XsDaysumDetailsPresenter;
import com.wtcrmandroid.adapter.fragment.XsDayplanAdapter;
import com.wtcrmandroid.fragment.journalmanager.DaysumAddCustomerFragment;
import com.wtcrmandroid.fragment.journalmanager.DaysumHkdzFragment;
import com.wtcrmandroid.fragment.journalmanager.DaysumSingleCustomerFragment;
import com.wtcrmandroid.fragment.journalmanager.DaysumWorkCountFragment;
import com.wtcrmandroid.fragment.journalmanager.DaysumWorkPlanFragment;
import com.wtcrmandroid.model.reponsedata.XsDaysumDetailsRP;
import com.wtcrmandroid.model.requestdata.DayDetailsRQ;
import com.wtcrmandroid.view.custompricing.TitleBar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

public class XsDaysumDetailsActivity extends BaseActivity<XsDaysumDetailsPresenter,XsDaysumDetailsRP> {

    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.tv_journal_type)
    TextView mTvJournalType;                    //日总结标题
    @BindView(R.id.tab_daysum_details)
    TabLayout mTabDaysumDetails;                //tab
    @BindView(R.id.vp_dayplan_details)
    ViewPager mVpDayplanDetails;              //vp
    private XsDayplanAdapter mXsDayplanAdapter;

    private String timeDate;
    private List<String> mStringList;
    private List<BaseFragment> mFragmentList;
    public XsDaysumDetailsRP DaysumData;

    @Override
    protected int layout() {
        return R.layout.activity_xs_daysum_details;
    }

    @Override
    protected void initView() {
        presenter = new XsDaysumDetailsPresenter(this,this);
        mTitlebar.setTitletext("日志详情");
        mTitlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XsDaysumDetailsActivity.this.finish();
            }
        });
        timeDate = getIntent().getStringExtra("dsdate");
        mTvJournalType.setText(setTitleString(timeDate));   //设置日期标题
        mStringList = new ArrayList<>();
        mFragmentList = new ArrayList<>();

        mStringList.add("工作总结");
        mStringList.add("今日工作量");
        mStringList.add("回款到单");
        mStringList.add("预测到单客户踩中");
        mStringList.add("新增意向客户");

        DayDetailsRQ dayDetailsRQ = new DayDetailsRQ();
        dayDetailsRQ.setUserId(MyApplication.application.getLoginData().getUserID());
        dayDetailsRQ.setType("day");
        dayDetailsRQ.setIsPlan("false");
        dayDetailsRQ.setRoleClass(MyApplication.application.getLoginData().getRoleClass());
        dayDetailsRQ.setNowDate(timeDate);

        presenter.postDaysumData(dayDetailsRQ);

    }

    @Override
    public void returnData(int key, XsDaysumDetailsRP data) {

        DaysumData = data;
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


    private String setTitleString(String dateTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        SimpleDateFormat format = new SimpleDateFormat("yyyy年M月d日");

        String TitleString = "";
        try {
            TitleString = format.format(simpleDateFormat.parse(dateTime))+"总结";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return TitleString;

    }

    static class ViewHolder {
        @BindView(R.id.tv_comment_count)
        TextView mTvCommentCount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
