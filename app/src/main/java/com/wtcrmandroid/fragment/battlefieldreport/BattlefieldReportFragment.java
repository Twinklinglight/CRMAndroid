package com.wtcrmandroid.fragment.battlefieldreport;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wtcrmandroid.BaseFragment;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.fragment.XsDayplanAdapter;
import com.wtcrmandroid.utils.DateUtil;
import com.wtcrmandroid.view.dialog.CalendarDialog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wt-pc on 2017/6/20.
 */

public class BattlefieldReportFragment extends BaseFragment {
    @BindView(R.id.tv_start_data)
    TextView tvStartData;
    @BindView(R.id.tv_end_data)
    TextView tvEndData;
    Date startdate;

    @BindView(R.id.tab_dayplan_details)
    TabLayout mTabDayplanDetails;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private XsDayplanAdapter mAdapter;
    private List<String> mTitleList;    //tablayout 标题列表
    private List<Fragment> mFragmentList; //vp中fragment集合
    @Override
    public void returnData(int key, Object data) {

    }

    @Override
    protected int Rlayout() {
        return R.layout.frgment_batlefield_report;
    }

    @Override
    protected void init() {

        Calendar cal = Calendar.getInstance();
//        int day = cal.get(Calendar.DATE);       //日
        int month = cal.get(Calendar.MONTH) + 1;//月
        int year = cal.get(Calendar.YEAR);      //年
        int totalday= DateUtil.getCurrentMonthDay();
        String startDate=year + "-" + month + "-01";
        tvStartData.setText(startDate);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            startdate=df.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tvEndData.setText(year+"-"+month+"-"+totalday);
        mTitleList = new ArrayList<>();
        mFragmentList = new ArrayList<>();
        mTitleList.add("营销中心团队排名");
        mTitleList.add("个人销售业绩排名");
        mFragmentList.add(new TeamRankingFragment());
        mFragmentList.add(new PersonalRankingFragment());
        mAdapter = new XsDayplanAdapter(getActivity().getSupportFragmentManager(),mTitleList,mFragmentList);
        viewpager.setAdapter(mAdapter);
        mTabDayplanDetails.setupWithViewPager(viewpager);
        mTabDayplanDetails.setSelectedTabIndicatorColor(ContextCompat.getColor(getActivity(),R.color.colorPrimary));
    }
    @OnClick({R.id.tv_start_data, R.id.tv_end_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_start_data:
                new CalendarDialog(getActivity(), new CalendarDialog.CalendarListener() {
                    @Override
                    public void CalendarSelcet(String datetext, Date date) {
                        startdate = date;
                        tvStartData.setText(datetext.substring(0,10) );
                    }
                }).show();
                break;
            case R.id.tv_end_data:
                new CalendarDialog(getActivity(), new CalendarDialog.CalendarListener() {
                    @Override
                    public void CalendarSelcet(String datetext, Date date) {
                        if (date.getTime() < startdate.getTime()) {
                            Toast.makeText(getActivity(), "结束时间小于起始时间", Toast.LENGTH_SHORT).show();
                        } else
                            tvEndData.setText(datetext.substring(0, 10));
                    }
                }).show();
                break;
        }
    }

}