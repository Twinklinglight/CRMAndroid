package com.wtcrmandroid.activity.crm;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.recycleview.ClientLibraryAdapter;
import com.wtcrmandroid.model.reponsedata.SearchCustomerRP;
import com.wtcrmandroid.model.requestdata.CompanyNameRetrievalRQ;
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

public class SearchClientLibraryActivity extends BaseActivity<SearchClientLibraryPresenter, List<SearchCustomerRP>> implements OnLoadMoreListener, OnRefreshListener {
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
    private ClientLibraryAdapter adapter;

    private CompanyNameRetrievalRQ data;

    private int kind;

    private int page = 0;

    @Override
    protected int layout() {
        return R.layout.activity_search_library;
    }

    @Override
    protected void initView() {
        presenter = new SearchClientLibraryPresenter(this,this);
        data = new CompanyNameRetrievalRQ();
        kind = getIntent().getIntExtra("kind", 0);
        rvView.setLayoutManager(new LinearLayoutManager(this));
        rvView.setAdapter(adapter = new ClientLibraryAdapter(this));
        switch (kind) {
            case 0:

                break;
            case 1:
                adapter.setStyle(1);
                break;
        }
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
                data.setPageSize(page);
                data.setCompanyName(etSearch.getText().toString());
                switch (kind) {
                    //主客户库
                    case 0:
                        presenter.searchCompany(data, 0);
                        break;
                    //续单公海
                    case 1:
                        presenter.searchCompany(data, 0);
                        break;
                }


                break;
        }
    }


    @Override
    public void returnData(int key, List<SearchCustomerRP> data) {
        switch (key) {
            //刷新返回数据
            case 0:
                adapter.addList(data);
                mSwipeToLoadLayout.setRefreshing(false);
                break;
            //加载更多返回数据
            case 1:
                List<SearchCustomerRP> list = adapter.getList();
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
