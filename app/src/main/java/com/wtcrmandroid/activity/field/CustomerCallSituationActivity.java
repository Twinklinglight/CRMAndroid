package com.wtcrmandroid.activity.field;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.base.BaseActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.recycleview.BaseRecycleAdapter;
import com.wtcrmandroid.adapter.recycleview.CustomerCallSituationAdapter;
import com.wtcrmandroid.model.dealdata.GroupingClockRecordDD;
import com.wtcrmandroid.model.reponsedata.CompanyVisitDetailsRP;
import com.wtcrmandroid.model.requestdata.CompanyVisitDetailsRQ;
import com.wtcrmandroid.presenter.activity.CustomerCallSituationP;
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
 * Created by wt-pc on 2017/6/19.
 * 客户拜访情况
 */

public class CustomerCallSituationActivity extends BaseActivity<CustomerCallSituationP, List<CompanyVisitDetailsRP>> implements OnLoadMoreListener, OnRefreshListener {
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
    @BindView(R.id.tv_customerName)
    TextView tvCustomerName;
    private BaseRecycleAdapter adapter;

    private CompanyVisitDetailsRQ companyVisitDetailsRQ;
    private int page = 1;//当前页码

    @Override
    public void returnData(int key, List<CompanyVisitDetailsRP> data) {
        switch (key) {
            //刷新返回数据
            case 0:
                adapter.addList(data);
                mSwipeToLoadLayout.setRefreshing(false);
                break;
            //加载更多数据
            case 1:
                mSwipeToLoadLayout.setLoadingMore(false);
                List<CompanyVisitDetailsRP> list = adapter.getList();
                list.addAll(data);
                adapter.addList(list);
                break;
        }
    }

    @Override
    protected int layout() {
        return R.layout.activity_customer_call_situation;
    }

    @Override
    protected void initView() {
        titlebar.setTitletext("客户拜访情况");
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvCustomerName.setText(getIntent().getStringExtra("customerName"));
        rvView.setLayoutManager(new LinearLayoutManager(this));
        rvView.setAdapter(adapter = new CustomerCallSituationAdapter(this));
        mSwipeToLoadLayout.setRefreshHeaderView(mHeaderView);
        mSwipeToLoadLayout.setLoadMoreFooterView(mFooterView);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);
        mSwipeToLoadLayout.setOnRefreshListener(this);
        presenter = new CustomerCallSituationP(this, this);
        companyVisitDetailsRQ = new CompanyVisitDetailsRQ();
        companyVisitDetailsRQ.setCustomerId(getIntent().getStringExtra("customerid"));
        companyVisitDetailsRQ.setPageIndex(1);
        presenter.sedPost(companyVisitDetailsRQ, 0);
    }


    @Override
    public void onRefresh() {
        page = 1;
        companyVisitDetailsRQ.setPageIndex(1);
        presenter.sedPost(companyVisitDetailsRQ, 0);
        adapter.addList(new ArrayList<GroupingClockRecordDD>());
    }

    @Override
    public void onLoadMore() {
        page = page + 1;
        companyVisitDetailsRQ.setPageIndex(page);
        presenter.sedPost(companyVisitDetailsRQ, 1);
    }

    @Override
    public void showShortToast(String text) {
        super.showShortToast(text);

        if (page == 1) {
            mSwipeToLoadLayout.setRefreshing(false);

        } else {
            mSwipeToLoadLayout.setLoadingMore(false);
            page=page-1;
        }

    }


}
