package com.wtcrmandroid.activity.field;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.PopupWindow;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.recycleview.FieldStatisticaAdapter;
import com.wtcrmandroid.adapter.recycleview.PoppupWindowTitleAdapter;
import com.wtcrmandroid.model.reponsedata.FieldStatisticsRP;
import com.wtcrmandroid.model.requestdata.FieldStatisticsRQ;
import com.wtcrmandroid.presenter.activity.FieldStatisticsPresenter;
import com.wtcrmandroid.utils.DateUtil;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.view.custompricing.TopChooseMenuBar;
import com.wtcrmandroid.view.dialog.CalendarDialog;
import com.wtcrmandroid.view.popupwindow.CalendarPopupWindow;
import com.wtcrmandroid.view.popupwindow.TitlePopupWindow;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 1363655717 on 2017-06-14.
 * 外勤统计
 */

public class FieldStatisticsAcrivity extends BaseActivity<FieldStatisticsPresenter,  List<FieldStatisticsRP>> {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.tcmb_bar)
    TopChooseMenuBar tcmbBar;
    @BindView(R.id.rv_view)
    RecyclerView rvView;
    @BindView(R.id.v_shadow)
    View vShadow;

    private FieldStatisticaAdapter adapter;
    private TitlePopupWindow titleLeftPopupWindow;
    private CalendarPopupWindow calendarPopupWindow;
    FieldStatisticsRQ fieldStatisticsRQ;




    @Override
    protected int layout() {
        return R.layout.activity_field_statistics;
    }

    @Override
    protected void initView() {
        titlebar.setTitletext("外勤统计");
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tcmbBar.setStrings(new String[]{"部门", "今天"});
        tcmbBar.setOnCheckedChangedListener(new TopChooseMenuBar.OnCheckedChangedListener() {
            @Override
            public void isSelected(int i) {
                vShadow.setVisibility(View.VISIBLE);
                switch (i) {
                    case 1:
                        //左边弹窗
                        if (titleLeftPopupWindow == null) {
                            List list = new ArrayList();
                            list.add("全部");
                            list.add("物通市场部");
                            titleLeftPopupWindow = new TitlePopupWindow(FieldStatisticsAcrivity.this, tcmbBar, list, 0, 0, new PoppupWindowTitleAdapter.oNclicklistner() {
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
                        titleLeftPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            tcmbBar.NoCheckStyle(1);
                            tcmbBar.setIsCheck_number(0);
                            vShadow.setVisibility(View.GONE);
                        }
                    });
                        break;
                    case 3:
                        if (calendarPopupWindow == null) {
                            calendarPopupWindow = new CalendarPopupWindow(FieldStatisticsAcrivity.this, tcmbBar, new CalendarDialog.CalendarListener() {
                                @Override
                                public void CalendarSelcet(String datetext, Date d) {
                                    if(DateUtil.isSameDate(d,new Date())){
                                        tcmbBar.setRightTextNo("今天");
                                        fieldStatisticsRQ.setCreateTime(DateUtil.getToday());
                                        adapter.setDate(DateUtil.getToday());
                                        presenter.sedPost(fieldStatisticsRQ,0);
                                    }else {
                                        tcmbBar.setRightTextNo(datetext);
                                        fieldStatisticsRQ.setCreateTime(datetext);
                                        adapter.setDate(datetext);
                                        presenter.sedPost(fieldStatisticsRQ,0);
                                    }
                                    adapter.addList(new ArrayList<FieldStatisticsRP>());
                                }
                            });
                            calendarPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                                @Override
                                public void onDismiss() {
                                    tcmbBar.NoCheckStyle(3);
                                    tcmbBar.setIsCheck_number(0);
                                    vShadow.setVisibility(View.GONE);
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
                    case 3:
                        calendarPopupWindow.dismiss();
                        break;

                }

            }
        });
        presenter=new FieldStatisticsPresenter(this,this);
        rvView.setLayoutManager(new LinearLayoutManager(this));
        rvView.setAdapter(adapter = new FieldStatisticaAdapter(this));
        fieldStatisticsRQ=new FieldStatisticsRQ();
        fieldStatisticsRQ.setCreateTime(DateUtil.getToday());
        fieldStatisticsRQ.setUserId(MyApplication.application.getLoginData().getUserID());
        presenter.sedPost(fieldStatisticsRQ,0);
        adapter.setDate(DateUtil.getToday());
    }


    @Override
    public void returnData(int key, List<FieldStatisticsRP> data) {
        adapter.addList(data);

    }
    @OnClick(R.id.v_shadow)
    public void onViewClicked() {
        if(titleLeftPopupWindow!=null)
            titleLeftPopupWindow.dismiss();
        if(calendarPopupWindow!=null)
            calendarPopupWindow.dismiss();
    }
}
