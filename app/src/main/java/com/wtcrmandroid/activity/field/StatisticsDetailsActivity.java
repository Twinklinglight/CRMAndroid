package com.wtcrmandroid.activity.field;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wtcrmandroid.base.BaseActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.recycleview.StatisticaDetailsAdapter;
import com.wtcrmandroid.model.reponsedata.ClockRecordRP;
import com.wtcrmandroid.model.requestdata.DayCloclRecordRQ;
import com.wtcrmandroid.presenter.activity.FieldStatisticsDetailsPresenter;
import com.wtcrmandroid.view.custompricing.TitleBar;

import java.util.List;

import butterknife.BindView;

/**
 * Created by wt-pc on 2017/6/17.
 * 外勤统计详情
 */

public class StatisticsDetailsActivity extends BaseActivity<FieldStatisticsDetailsPresenter, List<ClockRecordRP>> {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.swipe_target)
    RecyclerView rvView;

    private StatisticaDetailsAdapter adapter;
    private  DayCloclRecordRQ data;

    @Override
    public void returnData(int key, List<ClockRecordRP> data) {
        adapter.setList(data);

    }

    @Override
    protected int layout() {
        return R.layout.activity_statistics_details;
    }

    @Override
    protected void initView() {
        String userId = getIntent().getStringExtra("userId");
        String date = getIntent().getStringExtra("date");
        titlebar.setTitletext("外勤统计详情");
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rvView.setLayoutManager(new LinearLayoutManager(this));
        rvView.setAdapter(adapter = new StatisticaDetailsAdapter(this));
        presenter = new FieldStatisticsDetailsPresenter(this, this);
        data = new DayCloclRecordRQ();;
        data.setToDay(date);
        data.setUserId(userId);
        presenter.sedPost(data, 0);
        adapter.setDate(date);

    }


}
