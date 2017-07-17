package com.wtcrmandroid.activity.field;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.PopupWindow;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.recycleview.BaseRecycleAdapter;
import com.wtcrmandroid.adapter.recycleview.CustomerCallStatisticaAdapter;
import com.wtcrmandroid.adapter.recycleview.PoppupWindowTitleAdapter;
import com.wtcrmandroid.model.dealdata.GroupingClockRecordDD;
import com.wtcrmandroid.model.reponsedata.VisitStatisticalRP;
import com.wtcrmandroid.model.requestdata.VisitStatisticalRQ;
import com.wtcrmandroid.presenter.activity.CustomerCallStatisticalP;
import com.wtcrmandroid.utils.DateUtil;
import com.wtcrmandroid.view.RefreshHeaderView;
import com.wtcrmandroid.view.RefreshLoadMoreFooterView;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.view.custompricing.TopChooseMenuBar;
import com.wtcrmandroid.view.dialog.CalendarDialog;
import com.wtcrmandroid.view.popupwindow.CalendarPopupWindow;
import com.wtcrmandroid.view.popupwindow.TitlePopupWindow;
import com.wtcrmandroid.view.pulltorefresh.OnLoadMoreListener;
import com.wtcrmandroid.view.pulltorefresh.OnRefreshListener;
import com.wtcrmandroid.view.pulltorefresh.SwipeToLoadLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 1363655717 on 2017-06-14.
 * 客户拜访统计
 */

public class CustomerCallStatisticalAcrivity extends BaseActivity<CustomerCallStatisticalP, List<VisitStatisticalRP>> implements OnLoadMoreListener, OnRefreshListener {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.tcmb_bar)
    TopChooseMenuBar tcmbBar;

    @BindView(R.id.swipe_target)
    RecyclerView rvView;
    @BindView(R.id.swipe_refresh_header)
    RefreshHeaderView mHeaderView;
    @BindView(R.id.swipe_load_more_footer)
    RefreshLoadMoreFooterView mFooterView;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout mSwipeToLoadLayout;

    private BaseRecycleAdapter adapter;
    private TitlePopupWindow titleLeftPopupWindow;
    private CalendarPopupWindow calendarPopupWindow;
    private VisitStatisticalRQ visitStatisticalRQ;
    private int page = 1;//当前页码
    @Override
    public void returnData(int key, List<VisitStatisticalRP> data) {
        switch (key) {
            //刷新返回数据
            case 0:
                adapter.addList(data);
                mSwipeToLoadLayout.setRefreshing(false);
                break;
            //加载更多数据
            case 1:
                mSwipeToLoadLayout.setLoadingMore(false);
                List<VisitStatisticalRP>  list = adapter.getList();
                list.addAll(data);
                adapter.addList(list);
                break;
        }
    }

    @Override
    protected int layout() {
        return R.layout.activity_visit_statistics;
    }

    @Override
    protected void initView() {
        titlebar.setTitletext("客户拜访统计");

        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tcmbBar.setOnCheckedChangedListener(new TopChooseMenuBar.OnCheckedChangedListener() {
            @Override
            public void isSelected(int i) {
                switch (i) {
                    case 1:
                        //左边弹窗
                        if (titleLeftPopupWindow == null) {
                            List list = new ArrayList();
                            list.add("物通市场部");
                            titleLeftPopupWindow = new TitlePopupWindow(CustomerCallStatisticalAcrivity.this, tcmbBar, list, 0, 0, new PoppupWindowTitleAdapter.oNclicklistner() {
                                @Override
                                public void oNclicklistner(String data, int position) {
                                    titleLeftPopupWindow.dismiss();
                                    tcmbBar.setLeftText(data);
                                    tcmbBar.NoCheckStyle(1);
                                    tcmbBar.setIsCheck_number(0);
                                }
                            });

                        }
                        titleLeftPopupWindow.show();
                        break;
                    case 3:
                        if (calendarPopupWindow == null) {
                            calendarPopupWindow = new CalendarPopupWindow(CustomerCallStatisticalAcrivity.this, tcmbBar, new CalendarDialog.CalendarListener() {
                                @Override
                                public void CalendarSelcet(String datetext, Date d) {
                                    if (DateUtil.isSameDate(d, new Date())) {
                                        tcmbBar.setRightTextNo("今天");
                                        visitStatisticalRQ.setDate(DateUtil.getToday());
                                        presenter.sedPost(visitStatisticalRQ,0);
                                    }else {
                                        tcmbBar.setRightTextNo(datetext);
                                        visitStatisticalRQ.setDate(datetext);
                                        presenter.sedPost(visitStatisticalRQ,0);
                                    }
                                    adapter.addList(new ArrayList<VisitStatisticalRP>());

                                }
                            });
                            calendarPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                                @Override
                                public void onDismiss() {
                                    tcmbBar.NoCheckStyle(3);
                                    tcmbBar.setIsCheck_number(0);
                                }
                            });
                        }
                        calendarPopupWindow.show();

                        break;
                }

            }

            @Override
            public void isNoSelected(int i) {
                switch (i) {
                    case 1:
                        titleLeftPopupWindow.dismiss();
                        break;

                }

            }
        });
        rvView.setLayoutManager(new LinearLayoutManager(this));
        rvView.setAdapter(adapter = new CustomerCallStatisticaAdapter(this));
        mSwipeToLoadLayout.setRefreshHeaderView(mHeaderView);
        mSwipeToLoadLayout.setLoadMoreFooterView(mFooterView);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);
        mSwipeToLoadLayout.setOnRefreshListener(this);
        presenter = new CustomerCallStatisticalP(this, this);
        visitStatisticalRQ=new VisitStatisticalRQ();
        visitStatisticalRQ.setDate(DateUtil.getSubToaday());
        visitStatisticalRQ.setDepartmentId("0");
        visitStatisticalRQ.setPageindex(page);
        visitStatisticalRQ.setUserId(MyApplication.application.getLoginData().getUserID()+"");
        presenter.sedPost(visitStatisticalRQ,0);
    }


    @Override
    public void onRefresh() {
        page = 1;
        visitStatisticalRQ.setPageindex(page);
        presenter.sedPost(visitStatisticalRQ,0);
        adapter.addList(new ArrayList<GroupingClockRecordDD>());
    }

    @Override
    public void onLoadMore() {
        page = page + 1;
        visitStatisticalRQ.setPageindex(page);
        presenter.sedPost(visitStatisticalRQ,1);
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
