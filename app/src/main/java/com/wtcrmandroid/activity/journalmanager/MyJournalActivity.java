package com.wtcrmandroid.activity.journalmanager;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.MyJournalAdapter;
import com.wtcrmandroid.adapter.recycleview.PoppupWindowTitleAdapter;
import com.wtcrmandroid.model.MyJournalData;
import com.wtcrmandroid.model.reponsedata.LoginData;
import com.wtcrmandroid.presenter.activity.MyJournalPresenter;
import com.wtcrmandroid.view.RefreshHeaderView;
import com.wtcrmandroid.view.RefreshLoadMoreFooterView;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.view.custompricing.TopChooseMenuBar;
import com.wtcrmandroid.view.dialog.CalendarDialog;
import com.wtcrmandroid.view.popupwindow.TitlePopupWindow;
import com.wtcrmandroid.view.pulltorefresh.OnLoadMoreListener;
import com.wtcrmandroid.view.pulltorefresh.OnRefreshListener;
import com.wtcrmandroid.view.pulltorefresh.SwipeToLoadLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 我的日志Activity
 *
 * @author zxd
 * @date 2017/6/6
 */

public class MyJournalActivity extends BaseActivity<MyJournalPresenter, List<LoginData>> implements OnLoadMoreListener, OnRefreshListener, CalendarDialog.CalendarListener {

    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.swipe_target)
    ListView mLvMyjournal;          //我的日志列表
    @BindView(R.id.swipe_refresh_header)
    RefreshHeaderView mHeaderView;
    @BindView(R.id.swipe_load_more_footer)
    RefreshLoadMoreFooterView mFooterView;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout mSwipeToLoadLayout;
    @BindView(R.id.tcmb_bar)
    TopChooseMenuBar tcmbBar;

    private TitlePopupWindow TypeWindows;

    private MyJournalAdapter mMyJournalAdapter;

    private List<MyJournalData> mDatas;

    private Handler mHandler = new Handler();

    @Override
    protected int layout() {
        return R.layout.activity_my_journal;
    }

    @Override
    protected void initView() {
        mTitlebar.setTitletext("我的日志");
        mTitlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyJournalActivity.this.finish();
            }
        });
        tcmbBar.setStrings(new String[]{"类型","时间"});

        tcmbBar.setOnCheckedChangedListener(new TopChooseMenuBar.OnCheckedChangedListener() {
            @Override
            public void isSelected(int i) {
                switch (i){         //选择类型
                    case 1:
                        if (TypeWindows == null){
                            List list = new ArrayList();
                            list.add("全部");
                            list.add("日计划");
                            list.add("日总结");
                            list.add("周计划");
                            list.add("周总结");
                            TypeWindows = new TitlePopupWindow(MyJournalActivity.this,tcmbBar,list,0,0,
                                    new PoppupWindowTitleAdapter.oNclicklistner() {
                                        @Override
                                        public void oNclicklistner(String data, int position) {

                                            TypeWindows.dismiss();
                                            tcmbBar.NoCheckStyle(1);
                                            tcmbBar.setIsCheck_number(0);

                                        }
                                    });
                        }
                        TypeWindows.show();
                        break;
                    case 2:     //选择日期
                        new CalendarDialog(MyJournalActivity.this,MyJournalActivity.this).show();
                        break;
                }

            }

            @Override
            public void isNoSelected(int i) {
                switch (i){
                    case 1:
                        TypeWindows.dismiss();
                        break;
                }

            }
        });
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

                    Intent intent = new Intent(MyJournalActivity.this, HtDayplanDetails.class);
                    intent.putExtra("date","");
                    startActivity(intent);
//                    startActivity(new Intent(MyJournalActivity.this, XsDayplanDetailsActivity.class));

                } else if (position == 1) {
                    startActivity(new Intent(MyJournalActivity.this, HtDaysumDetailsActivity.class));
//                    startActivity(new Intent(MyJournalActivity.this, XsDaysumDetailsActivity.class));
                } else if (position == 2) {

                    startActivity(new Intent(MyJournalActivity.this, WeekplanDetailsActivity.class));
                } else if (position == 3) {

                    startActivity(new Intent(MyJournalActivity.this, WeeksumDetailsActivity.class));
                }
            }
        });

    }


    @Override
    public void returnData(int key, List<LoginData> data) {

    }

    //日期选中的回调
    @Override
    public void CalendarSelcet(String date) {


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
