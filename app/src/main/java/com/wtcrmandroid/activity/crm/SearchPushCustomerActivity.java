package com.wtcrmandroid.activity.crm;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.recycleview.PushCustomerAdapter;
import com.wtcrmandroid.model.reponsedata.PushCustomerRP;
import com.wtcrmandroid.model.requestdata.PushCustomerRQ;
import com.wtcrmandroid.presenter.activity.MyPushCustomerP;
import com.wtcrmandroid.view.RefreshHeaderView;
import com.wtcrmandroid.view.RefreshLoadMoreFooterView;
import com.wtcrmandroid.view.pulltorefresh.OnLoadMoreListener;
import com.wtcrmandroid.view.pulltorefresh.OnRefreshListener;
import com.wtcrmandroid.view.pulltorefresh.SwipeToLoadLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wt-pc on 2017/6/30.
 * 我的地推客户搜索页面
 */

public class SearchPushCustomerActivity extends BaseActivity<MyPushCustomerP, List<PushCustomerRP>> implements OnLoadMoreListener, OnRefreshListener {
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.swipe_target)
    RecyclerView rvView;

    @BindView(R.id.swipe_refresh_header)
    RefreshHeaderView mHeaderView;
    @BindView(R.id.swipe_load_more_footer)
    RefreshLoadMoreFooterView mFooterView;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout mSwipeToLoadLayout;
    private PushCustomerAdapter adapter;

    private PushCustomerRQ data;


    private int page = 1;

    @Override
    protected int layout() {
        return R.layout.activity_search_library;
    }

    @Override
    protected void initView() {
        presenter = new MyPushCustomerP(this,this);
        data = new PushCustomerRQ();
        data.setUserId(MyApplication.application.getLoginData().getUserID()+"");
        data.setIsDiTui(0);
        data.setPageIndex(page);
        rvView.setLayoutManager(new LinearLayoutManager(this));


        rvView.setAdapter(adapter = new PushCustomerAdapter(this));
        mSwipeToLoadLayout.setRefreshHeaderView(mHeaderView);
        mSwipeToLoadLayout.setLoadMoreFooterView(mFooterView);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);
        mSwipeToLoadLayout.setOnRefreshListener(this);
    }


    @OnClick({R.id.iv_left, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:
                page = 1;
                data.setPageIndex(page);
                data.setComName(etSearch.getText().toString());
                presenter.getData(data, 0);
                break;
        }
    }


    @Override
    public void returnData(int key, List<PushCustomerRP> data) {
        switch (key) {
            //刷新返回数据
            case 0:
                adapter.addList(data);
                mSwipeToLoadLayout.setRefreshing(false);
                break;
            //加载更多返回数据
            case 1:
                List<PushCustomerRP> list = adapter.getList();
                list.addAll(data);
                adapter.addList(list);
                mSwipeToLoadLayout.setLoadingMore(false);
                break;

        }
    }

    @Override
    public void onRefresh() {
        page = 1;
        data.setPageIndex(page);
        presenter.getData(data, 0);
    }

    @Override
    public void onLoadMore() {
        page = page + 1;
        data.setPageIndex(page);
        presenter.getData(data, 1);

    }
    @Override
    public void showShortToast(String text) {
        super.showShortToast(text);
        mSwipeToLoadLayout.setLoadingMore(false);
        if(page==1) {
            mSwipeToLoadLayout.setRefreshing(false);
        } else{
            mSwipeToLoadLayout.setLoadingMore(false);
            page=page-1;
        }
    }
}
