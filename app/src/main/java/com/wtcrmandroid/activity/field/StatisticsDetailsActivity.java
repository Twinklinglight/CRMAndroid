package com.wtcrmandroid.activity.field;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.recycleview.StatisticaDetailsAdapter;
import com.wtcrmandroid.model.reponsedata.ClockRecordRP;
import com.wtcrmandroid.model.requestdata.FieldStatisticsDetailsRQ;
import com.wtcrmandroid.presenter.activity.FieldStatisticsDetailsPresenter;
import com.wtcrmandroid.view.RefreshHeaderView;
import com.wtcrmandroid.view.RefreshLoadMoreFooterView;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.view.pulltorefresh.SwipeToLoadLayout;

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

    @BindView(R.id.swipe_refresh_header)
    RefreshHeaderView mHeaderView;
    @BindView(R.id.swipe_load_more_footer)
    RefreshLoadMoreFooterView mFooterView;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout mSwipeToLoadLayout;
    private StatisticaDetailsAdapter adapter;
    private FieldStatisticsDetailsRQ data;

    @Override
    public void returnData(int key, List<ClockRecordRP> data) {
        adapter.setList(data);

    }

    @Override
    protected int layout() {
        return R.layout.activity_clock_record;
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
        data = new FieldStatisticsDetailsRQ();
        data.setCreateTime(date);
        data.setPageSize("1");
        data.setUserId(userId);
        presenter.sedPost(data, 0);
        adapter.setDate(date);

    }


}
