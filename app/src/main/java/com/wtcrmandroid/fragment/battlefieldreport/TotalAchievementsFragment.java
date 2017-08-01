package com.wtcrmandroid.fragment.battlefieldreport;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wtcrmandroid.base.BaseFragment;
import com.wtcrmandroid.base.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.model.reponsedata.TotalAchievementsRP;
import com.wtcrmandroid.model.requestdata.IdTimeRequestdata;
import com.wtcrmandroid.presenter.fragment.TotalAchievementsP;
import com.wtcrmandroid.utils.DateUtil;
import com.wtcrmandroid.view.dialog.CalendarDialog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wt-pc on 2017/6/20.
 * 总业绩
 */

public class TotalAchievementsFragment extends BaseFragment<TotalAchievementsP, TotalAchievementsRP> {
    @BindView(R.id.tv_start_data)
    TextView tvStartData;
    @BindView(R.id.tv_end_data)
    TextView tvEndData;

    Date startdate;
    IdTimeRequestdata idTimeRequestdata;
    @BindView(R.id.tv_wt)
    TextView tvWt;
    @BindView(R.id.tv_wt_money)
    TextView tvWtMoney;
    @BindView(R.id.tv_new)
    TextView tvNew;
    @BindView(R.id.tv_continue)
    TextView tvContinue;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_market)
    TextView tvMarket;
    @BindView(R.id.tv_merchants)
    TextView tvMerchants;
    @BindView(R.id.tv_sp)
    TextView tvSp;
    @BindView(R.id.tv_sp_money)
    TextView tvSpMoney;
    @BindView(R.id.tv_sp_new)
    TextView tvSpNew;
    @BindView(R.id.tv_sp_continue)
    TextView tvSpContinue;
    @BindView(R.id.tv_z_money)
    TextView tvZMoney;


    @Override
    public void returnData(int key, TotalAchievementsRP data) {
        tvWtMoney.setText(data.getMoney_wt());
        tvSpMoney.setText(data.getMoney_sp());
        tvZMoney.setText(data.getMoney());
       tvNew.setText(data.getMoney_wtNew());
        tvContinue.setText(data.getMoney_wtXu());
        tvTotal.setText(data.getMoney_wtdx());
        tvMarket.setText(data.getMoney_wtsc());
        tvMerchants.setText(data.getMoney_wtzs());
        tvSpMoney.setText(data.getMoney_sp());
        tvSpNew.setText(data.getMoney_spNew());
        tvSpContinue.setText(data.getMoney_spXu());


    }

    @Override
    protected int Rlayout() {
        return R.layout.fragment_total_achievements;
    }

    @Override
    public void init() {
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
        presenter = new TotalAchievementsP(this, getActivity());
        idTimeRequestdata.setStartTime(tvStartData.getText().toString());
        idTimeRequestdata.setEndTime(tvEndData.getText().toString());
        presenter.getTotalAchievements(idTimeRequestdata, 0);
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
                        idTimeRequestdata.setStartTime(datetext.substring(0, 10));
                        presenter.getTotalAchievements(idTimeRequestdata, 0);
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
                            presenter.getTotalAchievements(idTimeRequestdata, 0);
                        }
                    }
                }).show();
                break;
        }
    }


}
