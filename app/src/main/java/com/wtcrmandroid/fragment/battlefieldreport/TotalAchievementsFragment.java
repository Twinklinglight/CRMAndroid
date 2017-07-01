package com.wtcrmandroid.fragment.battlefieldreport;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wtcrmandroid.BaseFragment;
import com.wtcrmandroid.R;
import com.wtcrmandroid.utils.DateUtils;
import com.wtcrmandroid.view.dialog.CalendarDialog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wt-pc on 2017/6/20.
 * 总业绩
 */

public class TotalAchievementsFragment extends BaseFragment {
    @BindView(R.id.tv_start_data)
    TextView tvStartData;
    @BindView(R.id.tv_end_data)
    TextView tvEndData;

    Date startdate;

    @Override
    public void returnData(int key, Object data) {

    }

    @Override
    protected int Rlayout() {
        return R.layout.fragment_total_achievements;
    }

    @Override
    protected void init() {
        Calendar cal = Calendar.getInstance();
//        int day = cal.get(Calendar.DATE);       //日
        int month = cal.get(Calendar.MONTH) + 1;//月
        int year = cal.get(Calendar.YEAR);      //年
        int totalday = DateUtils.getCurrentMonthDay();
        String startDate=year + "-" + month + "-01";
        tvStartData.setText(startDate);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            startdate=df.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tvEndData.setText(year + "-" + month + "-" + totalday);
    }

    @OnClick({R.id.tv_start_data, R.id.tv_end_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_start_data:
                new CalendarDialog(getActivity(), new CalendarDialog.CalendarListener() {
                    @Override
                    public void CalendarSelcet(String datetext, Date date) {
                        tvStartData.setText(datetext.substring(0, 10));
                        startdate = date;
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
