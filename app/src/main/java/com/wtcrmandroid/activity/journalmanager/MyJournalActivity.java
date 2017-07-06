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
import com.wtcrmandroid.model.MyjournalRponseData;
import com.wtcrmandroid.model.reponsedata.LoginData;
import com.wtcrmandroid.model.requestdata.MyJournalRequestData;
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
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * 我的日志Activity
 *
 * @author zxd
 * @date 2017/6/6
 */

public class MyJournalActivity extends BaseActivity<MyJournalPresenter, List<MyjournalRponseData>> implements OnLoadMoreListener, OnRefreshListener, CalendarDialog.CalendarListener, MyJournalAdapter.ItemClickListener {

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

    private String type = "";
    private String todate = "";
    private int index = 0;

    private TitlePopupWindow TypeWindows;

    private MyJournalAdapter mMyJournalAdapter;

    private List<MyjournalRponseData> mDatas;

    private Handler mHandler = new Handler();

    @Override
    protected int layout() {
        return R.layout.activity_my_journal;
    }

    @Override
    protected void initView() {
        presenter = new MyJournalPresenter(this);
        postData(index,type,todate,1);

        mTitlebar.setTitletext("我的日志");
        mTitlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyJournalActivity.this.finish();
            }
        });
        mMyJournalAdapter = new MyJournalAdapter(this);
        tcmbBar.setStrings(new String[]{"类型","时间"});
        tcmbBar.setOnCheckedChangedListener(new TopChooseMenuBar.OnCheckedChangedListener() {
            @Override
            public void isSelected(int i) {
                switch (i){         //选择类型
                    case 1:
                        if (TypeWindows == null){
                            final List<String> list = new ArrayList();
                            list.add("全部");
                            list.add("日计划");
                            list.add("日总结");
                            list.add("周计划");
                            list.add("周总结");
                            TypeWindows = new TitlePopupWindow(MyJournalActivity.this,tcmbBar,list,0,0,
                                    new PoppupWindowTitleAdapter.oNclicklistner() {
                                        @Override
                                        public void oNclicklistner(String data, int position) {

                                            index = 0;
                                            todate = "";
                                            switch (position){
                                                case 0:
                                                    type = "";
                                                    postData(index,type,todate,1);
                                                    break;
                                                case 1:
                                                    type = "dayPlan";
                                                    postData(index,type,todate,1);
                                                    break;
                                                case 2:
                                                    type = "dayWork";
                                                    postData(index,type,todate,1);
                                                    break;
                                                case 3:
                                                    type = "weekPlan";
                                                    postData(0,type,todate,1);
                                                    break;
                                                case 4:
                                                    type = "weekWork";
                                                    postData(0,type,todate,1);
                                                    break;
                                            }
                                            TypeWindows.dismiss();
                                            tcmbBar.NoCheckStyle(1);         //tcmBar右边样式
                                            tcmbBar.setLeftText(list.get(position));
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

        mDatas = new ArrayList<>();

        mSwipeToLoadLayout.setRefreshHeaderView(mHeaderView);
        mSwipeToLoadLayout.setLoadMoreFooterView(mFooterView);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);
        mSwipeToLoadLayout.setOnRefreshListener(this);

        /*mLvMyjournal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        });*/

    }

    //数据请求
    private void postData(int index,String type,String today,int key){
        MyJournalRequestData myJournalRequestData = new MyJournalRequestData();
        myJournalRequestData.setType(type);
        myJournalRequestData.setUserId(3066);
        myJournalRequestData.setWeekIndex(index);
        myJournalRequestData.setToDay(today);
        presenter.getData(myJournalRequestData,key);
    }


    @Override
    public void returnData(int key, List<MyjournalRponseData> data) {

        switch (key){
            case 1:
                mSwipeToLoadLayout.setRefreshing(false);
                mMyJournalAdapter.setmDatas(data);
                mLvMyjournal.setAdapter(mMyJournalAdapter);
                mMyJournalAdapter.notifyDataSetChanged();
                break;
            case 2:
                mSwipeToLoadLayout.setLoadingMore(false);
                List<MyjournalRponseData> list = mMyJournalAdapter.getmDatas();
                int selectPosition = list.size()-1;
                list.addAll(data);
                mMyJournalAdapter.setmDatas(list);
                mLvMyjournal.setAdapter(mMyJournalAdapter);
                mMyJournalAdapter.notifyDataSetChanged();
                mLvMyjournal.setSelection(selectPosition);      //设置显示位置
                break;
        }

    }

    //日期选中的回调
    @Override
    public void CalendarSelcet(String datetext, Date date) {

        todate = datetext;
        type = "";
        index = 0;
        postData(index,type,todate,1);
    }

    @Override
    public void onLoadMore() {
        index = index+1;
        postData(index,type,todate,2);
    }

    @Override
    public void onRefresh() {
        index = 0;
        postData(index,type,todate,1);
    }

    /**
     * 条目点击的回调
     * @param position  条目位置
     */

    @Override
    public void DayPlanClick(int position) {

        Intent intent = new Intent(MyJournalActivity.this, HtDayplanDetails.class);
        intent.putExtra("dpdate",mMyJournalAdapter.getmDatas().get(position).getShortRecordDate());
        startActivity(intent);

    }

    @Override
    public void DaySumClick(int position) {

        Intent intent = new Intent(MyJournalActivity.this, HtDaysumDetailsActivity.class);
        intent.putExtra("dsdate",mMyJournalAdapter.getmDatas().get(position).getShortRecordDate());
        startActivity(intent);
    }

    @Override
    public void WeekPlanClick(int position) {
        Intent intent = new Intent(MyJournalActivity.this, WeekplanDetailsActivity.class);
        intent.putExtra("wpbegin",mMyJournalAdapter.getmDatas().get(position).getWeekBegin());
        intent.putExtra("wpend",mMyJournalAdapter.getmDatas().get(position).getWeekEnd());
        startActivity(intent);

    }

    @Override
    public void WeekSumClick(int position) {

        Intent intent = new Intent(MyJournalActivity.this, WeeksumDetailsActivity.class);
        intent.putExtra("wsbegin",mMyJournalAdapter.getmDatas().get(position).getWeekBegin());
        intent.putExtra("wsend",mMyJournalAdapter.getmDatas().get(position).getWeekEnd());
        startActivity(intent);
    }
}
