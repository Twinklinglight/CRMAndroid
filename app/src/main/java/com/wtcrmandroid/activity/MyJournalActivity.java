package com.wtcrmandroid.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.MyJournalAdapter;
import com.wtcrmandroid.custompricing.TitleBar;
import com.wtcrmandroid.data.LoginData;
import com.wtcrmandroid.model.MyJournalData;
import com.wtcrmandroid.presenter.activity.MyJournalPresenter;
import com.wtcrmandroid.pulltorefresh.OnLoadMoreListener;
import com.wtcrmandroid.pulltorefresh.OnRefreshListener;
import com.wtcrmandroid.pulltorefresh.SwipeToLoadLayout;
import com.wtcrmandroid.view.RefreshHeaderView;
import com.wtcrmandroid.view.RefreshLoadMoreFooterView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的日志Activity
 *
 * @author zxd
 * @date 2017/6/6
 */

public class MyJournalActivity extends BaseActivity<MyJournalPresenter, List<LoginData>> implements OnLoadMoreListener, OnRefreshListener {

    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.ll_myjournal_type)
    LinearLayout mLlMyjournalType;  //类型筛选按钮
    @BindView(R.id.ll_myjournal_time)
    LinearLayout mLlMyjournalTime;  //时间筛选按钮
    @BindView(R.id.swipe_target)
    ListView mLvMyjournal;          //我的日志列表
    @BindView(R.id.swipe_refresh_header)
    RefreshHeaderView mHeaderView;
    @BindView(R.id.swipe_load_more_footer)
    RefreshLoadMoreFooterView mFooterView;
    @BindView(R.id.ll_xiala_type)
    LinearLayout mLlXialaType;      //类型弹框
    @BindView(R.id.iv_type_arrow)
    ImageView mIvTypeArrow;
    @BindView(R.id.iv_time_arrow)
    ImageView mIvTimeArrow;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout mSwipeToLoadLayout;

    private MyJournalAdapter mMyJournalAdapter;

    private List<MyJournalData> mDatas;

    private Handler mHandler = new Handler();

    @Override
    protected int layout() {
        return R.layout.activity_my_journal;
    }

    @Override
    protected void initview() {
        mTitlebar.setTitletext("我的日志");
        presenter = new MyJournalPresenter(this);

        mDatas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            MyJournalData myJournalData = new MyJournalData();
            myJournalData.setJournalTitle("2017-5-13日计划");
            myJournalData.setJournalContent("都干了这，干了那");
            mDatas.add(myJournalData);
        }

        mSwipeToLoadLayout.setRefreshHeaderView(mHeaderView);
        mSwipeToLoadLayout.setLoadMoreFooterView(mFooterView);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);
        mSwipeToLoadLayout.setOnRefreshListener(this);

        mMyJournalAdapter = new MyJournalAdapter(mDatas);
        mLvMyjournal.setAdapter(mMyJournalAdapter);

        mMyJournalAdapter.notifyDataSetChanged();

        mLvMyjournal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {

//                    startActivity(new Intent(MyJournalActivity.this,HtDayplanDetails.class));
                    startActivity(new Intent(MyJournalActivity.this, XsDayplanDetailsActivity.class));

                } else if (position == 1) {
//                    startActivity(new Intent(MyJournalActivity.this,HtDaysumDetailsActivity.class));
                    startActivity(new Intent(MyJournalActivity.this, XsDaysumDetailsActivity.class));
                } else if (position == 2) {

                    startActivity(new Intent(MyJournalActivity.this, WeekplanDetailsActivity.class));
                } else if (position == 3) {

                    startActivity(new Intent(MyJournalActivity.this, WeeksumDetailsActivity.class));
                }
            }
        });

    }


    @OnClick(R.id.ll_myjournal_type)
    public void onViewClicked() {
        presenter.getData();

        if (mLlXialaType.getVisibility() == View.VISIBLE) {
            mLlXialaType.setVisibility(View.INVISIBLE);
            ObjectAnimator anim = ObjectAnimator.ofFloat(mIvTypeArrow, "rotation", 180f, 0f);
            anim.setDuration(200);
            anim.start();
        } else {
            mLlXialaType.setVisibility(View.VISIBLE);
            ObjectAnimator anim = ObjectAnimator.ofFloat(mIvTypeArrow, "rotation", 0f, 180f);
            anim.setDuration(200);
            anim.start();
        }

    }


    @Override
    public void returnData(int key, List<LoginData> data) {

    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeToLoadLayout.setLoadingMore(false);
            }
        }, 2000);
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeToLoadLayout.setRefreshing(false);
            }
        }, 2000);
    }
}
