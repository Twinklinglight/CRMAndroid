package com.wtcrmandroid.fragment.battlefieldreport;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wtcrmandroid.base.BaseFragment;
import com.wtcrmandroid.base.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.fragment.XsDayplanAdapter;
import com.wtcrmandroid.model.reponsedata.SalesRankingRP;
import com.wtcrmandroid.model.requestdata.IdTimeRequestdata;
import com.wtcrmandroid.presenter.fragment.BattlefieldReportP;
import com.wtcrmandroid.utils.DateUtil;
import com.wtcrmandroid.view.dialog.CalendarDialog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wt-pc on 2017/6/20.
 * 销售战报
 */

public class BattlefieldReportFragment extends BaseFragment<BattlefieldReportP, List<SalesRankingRP>> {
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
    private List<BaseFragment> mFragmentList; //vp中fragment集合


    IdTimeRequestdata idTimeRequestdata;
    private boolean[] first = new boolean[]{false, true};

    @Override
    public void returnData(int key, List<SalesRankingRP> data) {
        mFragmentList.get(key).load(data);
    }

    @Override
    protected int Rlayout() {
        return R.layout.frgment_batlefield_report;
    }

    @Override
    public void init() {
        presenter = new BattlefieldReportP(this, getActivity());
        idTimeRequestdata = new IdTimeRequestdata();
        idTimeRequestdata.setUserId(MyApplication.application.getLoginData().getUserID());
        int totalday = DateUtil.getCurrentMonthDay();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = df.format(new Date()).substring(0, 8) + "01";
        tvStartData.setText(startDate);
        try {
            startdate = df.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tvEndData.setText(startDate.substring(0, 8) + totalday);
        idTimeRequestdata.setStartTime(tvStartData.getText().toString());
        idTimeRequestdata.setEndTime(tvEndData.getText().toString());

        presenter.getTeamRanking(idTimeRequestdata, 0);
        mTitleList = new ArrayList<>();
        mFragmentList = new ArrayList<>();
        mTitleList.add("营销中心团队排名");
        mTitleList.add("个人销售业绩排名");
        mFragmentList.add(new TeamRankingFragment());
        mFragmentList.add(new PersonalRankingFragment());
        mAdapter = new XsDayplanAdapter(getActivity().getSupportFragmentManager(), mTitleList, mFragmentList);
        viewpager.setAdapter(mAdapter);
        mTabDayplanDetails.setupWithViewPager(viewpager);
        mTabDayplanDetails.setSelectedTabIndicatorColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (first[position]) {
                    first[position]=false;
                    switch (position) {
                        case 0:
                            presenter.getTeamRanking(idTimeRequestdata, position);
                            break;
                        case 1:
                            presenter.getPersonalRanking(idTimeRequestdata, position);
                            break;

                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @OnClick({R.id.tv_start_data, R.id.tv_end_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_start_data:
                new CalendarDialog(getActivity(), new CalendarDialog.CalendarListener() {
                    @Override
                    public void CalendarSelcet(String datetext, Date date) {
                        startdate = date;
                        tvStartData.setText(datetext.substring(0, 10));
                        idTimeRequestdata.setStartTime(datetext.substring(0, 10));
                        first=new boolean[]{true,true};
                        switch (viewpager.getCurrentItem()){
                            case 0:
                                presenter.getTeamRanking(idTimeRequestdata,0);
                                first[0]=false;
                                break;
                            case 1:
                                presenter.getPersonalRanking(idTimeRequestdata, 1);
                                first[1]=false;
                                break;
                        }
                    }
                }).show();
                break;
            case R.id.tv_end_data:
                new CalendarDialog(getActivity(), new CalendarDialog.CalendarListener() {
                    @Override
                    public void CalendarSelcet(String datetext, Date date) {
                        if (date.getTime() < startdate.getTime()) {
                            Toast.makeText(getActivity(), "结束时间小于起始时间", Toast.LENGTH_SHORT).show();
                        } else {
                            tvEndData.setText(datetext.substring(0, 10));
                            idTimeRequestdata.setEndTime(datetext.substring(0, 10));
                            first=new boolean[]{true,true};
                            switch (viewpager.getCurrentItem()){
                                case 0:
                                    presenter.getTeamRanking(idTimeRequestdata,0);
                                    first[0]=false;
                                    break;
                                case 1:
                                    presenter.getPersonalRanking(idTimeRequestdata, 1);
                                    first[1]=false;
                                    break;
                            }
                        }
                    }
                }).show();
                break;
        }
    }

}