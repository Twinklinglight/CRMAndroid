package com.wtcrmandroid.activity.crm;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.recycleview.MyClientLibraryAdapter;
import com.wtcrmandroid.model.reponsedata.SearchSalerCustomerReponseData;
import com.wtcrmandroid.model.requestdata.CompanyNameRetrievalRequestData;
import com.wtcrmandroid.presenter.activity.SearchClientLibraryPresenter;
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
 * CRM搜索页面
 */

public class SearchMyClientLibraryActivity extends BaseActivity<SearchClientLibraryPresenter, List<SearchSalerCustomerReponseData>> implements OnLoadMoreListener, OnRefreshListener {
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
    private MyClientLibraryAdapter adapter;

    private CompanyNameRetrievalRequestData data;


    private int page = 1;

    @Override
    protected int layout() {
        return R.layout.activity_search_library;
    }

    @Override
    protected void initView() {
        presenter = new SearchClientLibraryPresenter(this);
        data = new CompanyNameRetrievalRequestData();

        rvView.setLayoutManager(new LinearLayoutManager(this));


        rvView.setAdapter(adapter = new MyClientLibraryAdapter(this));
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
                data.setCompanyName(etSearch.getText().toString());
                presenter.searchCompany(data, 0);
                break;
        }
    }


    @Override
    public void returnData(int key, List<SearchSalerCustomerReponseData> data) {
        switch (key) {
            //刷新返回数据
            case 0:
                adapter.addList(data);
                mSwipeToLoadLayout.setRefreshing(false);
                break;
            //加载更多返回数据
            case 1:
                List<SearchSalerCustomerReponseData> list = adapter.getList();
                list.addAll(data);
                adapter.addList(list);
                mSwipeToLoadLayout.setLoadingMore(false);
                break;

        }
    }

    @Override
    public void onRefresh() {
        page = 1;
        data.setPageSize(page);
        presenter.searchCompany(data, 0);
    }

    @Override
    public void onLoadMore() {
        page = page + 1;
        data.setPageSize(page);
        presenter.searchCompany(data, 1);

    }
}
