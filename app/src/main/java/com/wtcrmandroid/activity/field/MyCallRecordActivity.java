package com.wtcrmandroid.activity.field;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wtcrmandroid.base.BaseActivity;
import com.wtcrmandroid.base.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.recycleview.BaseRecycleAdapter;
import com.wtcrmandroid.adapter.recycleview.MyCallRecordAdapter;
import com.wtcrmandroid.model.reponsedata.PersonalAllRecordRP;
import com.wtcrmandroid.model.requestdata.PersonalAllRecordRQ;
import com.wtcrmandroid.presenter.activity.MyCallRecordP;
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
 * 我的拜访记录
 */

public class MyCallRecordActivity extends BaseActivity<MyCallRecordP,List<PersonalAllRecordRP>> implements OnLoadMoreListener, OnRefreshListener {
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
    private BaseRecycleAdapter adapter;

    PersonalAllRecordRQ personalAllRecordRQ;

    private int page=1;
    @Override
    public void returnData(int key, List<PersonalAllRecordRP> list) {
        switch(key){
            //刷新返回数据
            case 0:
                adapter.addList(list);
                mSwipeToLoadLayout.setRefreshing(false);
                break;
            //加载更多返回数据
            case 1:
                List<PersonalAllRecordRP> lisa=adapter.getList();
                lisa.addAll(list);
                adapter.addList(lisa);
                mSwipeToLoadLayout.setLoadingMore(false);
                break;

        }
    }

    @Override
    protected int layout() {
        return R.layout.activity_my_call_record;
    }

    @Override
    protected void initView() {
        titlebar.setTitletext("我的拜访记录");
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rvView.setLayoutManager(new LinearLayoutManager(this));
        rvView.setAdapter(adapter=new MyCallRecordAdapter(this));
        presenter=new MyCallRecordP(this,this);
        personalAllRecordRQ=new PersonalAllRecordRQ();
        personalAllRecordRQ.setUserId(MyApplication.application.getLoginData().getUserID()+"");
        personalAllRecordRQ.setPageIndex(1);
        presenter.getData(personalAllRecordRQ,0);
        mSwipeToLoadLayout.setRefreshHeaderView(mHeaderView);
        mSwipeToLoadLayout.setLoadMoreFooterView(mFooterView);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);
        mSwipeToLoadLayout.setOnRefreshListener(this);
    }


    @Override
    public void onRefresh() {
        page=1;
        personalAllRecordRQ.setPageIndex(page);
        presenter.getData(personalAllRecordRQ,0);
        adapter.addList(new ArrayList<PersonalAllRecordRP>());
    }

    @Override
    public void onLoadMore() {
        page=page+1;
        personalAllRecordRQ.setPageIndex(page);
        presenter.getData(personalAllRecordRQ,1);

    }
    @Override
    public void showShortToast(String text) {
        super.showShortToast(text);
        mSwipeToLoadLayout.setLoadingMore(false);
        if(page==1) {
            mSwipeToLoadLayout.setRefreshing(false);

        }else
            mSwipeToLoadLayout.setLoadingMore(false);

    }
}
