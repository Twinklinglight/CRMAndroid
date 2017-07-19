package com.wtcrmandroid.activity.journalmanager;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.BaseFragment;
import com.wtcrmandroid.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.journalmanager.present.XsDayPlanDetailsPresenter;
import com.wtcrmandroid.adapter.fragment.XsDayplanAdapter;
import com.wtcrmandroid.fragment.journalmanager.MajorCustomerFragment;
import com.wtcrmandroid.fragment.journalmanager.SingleCustomerFragment;
import com.wtcrmandroid.fragment.journalmanager.WorkPlanFragment;
import com.wtcrmandroid.model.MajorCustomerData;
import com.wtcrmandroid.model.SingleCustomerData;
import com.wtcrmandroid.model.reponsedata.HtDayplanDetailsData;
import com.wtcrmandroid.model.reponsedata.XsDayplanDetailsRP;
import com.wtcrmandroid.model.requestdata.DayDetailsRQ;
import com.wtcrmandroid.view.custompricing.TitleBar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class XsDayplanDetailsActivity extends BaseActivity<XsDayPlanDetailsPresenter,XsDayplanDetailsRP> {

    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.tv_journal_type)
    TextView mTvJournalType;            //日志详情标题
    @BindView(R.id.tab_dayplan_details)
    TabLayout mTabDayplanDetails;       //tab
    @BindView(R.id.vp_dayplan_details)
    ViewPager mVpDayplanDetails;        //vp
    private XsDayplanAdapter mAdapter;

    private String timeDate;
    private List<String> mTitleList;    //tablayout 标题列表
    private List<BaseFragment> mFragmentList; //vp中fragment集合
    private WorkPlanFragment planFragment;
    private SingleCustomerFragment singleCustomerFragment;
    private MajorCustomerFragment majorCustomerFragment;

    public XsDayplanDetailsRP RpData;
    public List<HtDayplanDetailsData> workdetail;
    public List<SingleCustomerData> workDreamOrder;
    public List<MajorCustomerData> workFocus;

    private static final String TAG = "XsDayplanDetailsActivit";

    @Override
    protected int layout() {
        return R.layout.activity_xs_dayplan_details;
    }

    @Override
    protected void initView() {
        presenter = new XsDayPlanDetailsPresenter(this,this);
        timeDate = getIntent().getStringExtra("dpdate");
        mTvJournalType.setText(setTitleString(timeDate));   //设置日期标题
        mTitlebar.setTitletext("日志详情");
        mTitlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initVp();

        DayDetailsRQ dayDetailsRQ = new DayDetailsRQ();
        dayDetailsRQ.setUserId(MyApplication.application.getLoginData().getUserID());
        dayDetailsRQ.setType("day");
        dayDetailsRQ.setIsPlan(true);
        dayDetailsRQ.setNowDate(timeDate);
        dayDetailsRQ.setRoleClass(0);

        presenter.postWpDetails(dayDetailsRQ);

    }
    private void initVp() {
        mTitleList = new ArrayList<>();
        mFragmentList = new ArrayList<>();
        mTitleList.add("工作计划");
        mTitleList.add("预测到单客户");
        mTitleList.add("重点意向客户");

    }
    @Override
    public void returnData(int key, XsDayplanDetailsRP data) {
        if (data != null){
            RpData = data;
            workdetail = data.getWorkdetail();
            workDreamOrder = data.getWorkDreamOrder();
            workFocus = data.getWorkFocus();

            planFragment = new WorkPlanFragment();
            singleCustomerFragment = new SingleCustomerFragment();
            majorCustomerFragment = new MajorCustomerFragment();

            mFragmentList.add(planFragment);
            mFragmentList.add(singleCustomerFragment);
            mFragmentList.add(majorCustomerFragment);

            mAdapter = new XsDayplanAdapter(getSupportFragmentManager(),mTitleList,mFragmentList);
            mVpDayplanDetails.setAdapter(mAdapter);
            mTabDayplanDetails.setupWithViewPager(mVpDayplanDetails);
//            mTabDayplanDetails.setSelectedTabIndicatorColor(ContextCompat.getColor(this,R.color.colorPrimary));
        }
    }

    private String setTitleString(String dateTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        SimpleDateFormat format = new SimpleDateFormat("yyyy年M月d日");

        String TitleString = "";
        try {
            TitleString = format.format(simpleDateFormat.parse(dateTime))+"计划";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return TitleString;

    }
}
