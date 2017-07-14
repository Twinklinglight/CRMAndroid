package com.wtcrmandroid.activity.field;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.recycleview.ClockRecordAdapter;
import com.wtcrmandroid.model.dealdata.GroupingClockRecordDD;
import com.wtcrmandroid.model.requestdata.ListPersonSignInRQ;
import com.wtcrmandroid.presenter.activity.ClockRecordPresenter;
import com.wtcrmandroid.view.RefreshHeaderView;
import com.wtcrmandroid.view.RefreshLoadMoreFooterView;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.view.pulltorefresh.OnLoadMoreListener;
import com.wtcrmandroid.view.pulltorefresh.OnRefreshListener;
import com.wtcrmandroid.view.pulltorefresh.SwipeToLoadLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wt-pc on 2017/6/17.
 * 打卡记录
 */

public class ClockRecordActivity extends BaseActivity<ClockRecordPresenter, List<GroupingClockRecordDD>> implements OnLoadMoreListener, OnRefreshListener {
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
    private ClockRecordAdapter adapter;
    private int page = 1;//当前页码
    ListPersonSignInRQ data;



    @Override
    protected int layout() {
        return R.layout.activity_clock_record;
    }

    @Override
    protected void initView() {
        titlebar.setTitletext("打卡记录");
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rvView.setLayoutManager(new LinearLayoutManager(this));
        rvView.setAdapter(adapter = new ClockRecordAdapter(this));
        presenter = new ClockRecordPresenter(this,this);
        data = new ListPersonSignInRQ();
        data.setUserId(MyApplication.application.getLoginData().getUserID());
        data.setWeekIndex(0);
        presenter.sedPost(data,0);
        mSwipeToLoadLayout.setRefreshHeaderView(mHeaderView);
        mSwipeToLoadLayout.setLoadMoreFooterView(mFooterView);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);
        mSwipeToLoadLayout.setOnRefreshListener(this);
    }


    @Override
    public void onRefresh() {
        page=1;
        data.setWeekIndex(0);
        presenter.sedPost(data,0);
        adapter.setList(new ArrayList<GroupingClockRecordDD>());
    }

    @Override
    public void onLoadMore() {
        page=page+1;
        data.setWeekIndex(page-1);
        presenter.sedPost(data,1);
    }

    @Override
    public void returnData(int key, List<GroupingClockRecordDD> data) {
        switch(key) {
            //刷新返回数据
            case 0:
                adapter.setList(data);
                mSwipeToLoadLayout.setRefreshing(false);
                break;
            //加载更多数据
            case 1:
                mSwipeToLoadLayout.setLoadingMore(false);
                List<GroupingClockRecordDD> list=adapter.getList();
                list.addAll(data);
                adapter.setList(list);
                break;
        }


    }
    @Override
    public void showShortToast(String text) {
        super.showShortToast(text);

        if(page==1) {
            mSwipeToLoadLayout.setRefreshing(false);

        }else
            mSwipeToLoadLayout.setLoadingMore(false);

    }
}
