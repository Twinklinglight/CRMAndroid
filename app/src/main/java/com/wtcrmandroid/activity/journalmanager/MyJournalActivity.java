package com.wtcrmandroid.activity.journalmanager;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.listview.MyJournalAdapter;
import com.wtcrmandroid.adapter.recycleview.PoppupWindowTitleAdapter;
import com.wtcrmandroid.model.reponsedata.MyjournalRponseData;
import com.wtcrmandroid.model.requestdata.MyJournalRequestData;
import com.wtcrmandroid.presenter.activity.MyJournalPresenter;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.view.custompricing.TopChooseMenuBar;
import com.wtcrmandroid.view.dialog.CalendarDialog;
import com.wtcrmandroid.view.popupwindow.CalendarPopupWindow;
import com.wtcrmandroid.view.popupwindow.TitlePopupWindow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;

/**
 * 我的日志Activity
 *
 * @author zxd
 * @date 2017/6/6
 */

public class MyJournalActivity extends BaseActivity<MyJournalPresenter, List<MyjournalRponseData>> implements CalendarDialog.CalendarListener, MyJournalAdapter.ItemClickListener {

    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.swipe_target)
    ListView mLvMyjournal;          //我的日志列表
    @BindView(R.id.tcmb_bar)
    TopChooseMenuBar tcmbBar;

    private SimpleDateFormat sDf;

    private String type = "";       //类型筛选
    private String todate = "";     //时间筛选
    private int index = 0;          //索引
    private Date nowDate;

    private TitlePopupWindow TypeWindows;

    private CalendarPopupWindow calendarWindow;

    private MyJournalAdapter mMyJournalAdapter;

    private List<MyjournalRponseData> mDatas;
    private int roleClass;

    @Override
    protected int layout() {
        return R.layout.activity_my_journal;
    }

    @Override
    protected void initView() {
        presenter = new MyJournalPresenter(this,this);
        sDf = new SimpleDateFormat("yyyy年MM月dd日");
        nowDate = new Date();
        roleClass = MyApplication.application.getLoginData().getRoleClass();

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
                    case 3:     //选择日期
                        if (calendarWindow != null){
                            if (calendarWindow.isShowing()){
                                calendarWindow.dismiss();
                            }else {
                                calendarWindow.show();
                            }
                        }else {
                            calendarWindow =  new CalendarPopupWindow(MyJournalActivity.this,tcmbBar,MyJournalActivity.this);
                            calendarWindow.show();
                            calendarWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                                @Override
                                public void onDismiss() {
                                    tcmbBar.NoCheckStyle(3);
                                    tcmbBar.setIsCheck_number(0);
                                }
                            });
                        }
                        break;
                }
            }
            @Override
            public void isNoSelected(int i) {
                switch (i){
                    case 1:
                        TypeWindows.dismiss();
                        break;
                    case 3:
                        calendarWindow.dismiss();
                }
            }
        });

        mDatas = new ArrayList<>();
    }

    //数据请求
    private void postData(int index,String type,String today,int key){
        MyJournalRequestData myJournalRequestData = new MyJournalRequestData();
        myJournalRequestData.setType(type);
        myJournalRequestData.setUserId(MyApplication.application.getLoginData().getUserID());
        myJournalRequestData.setWeekIndex(index);
        myJournalRequestData.setToDay(today);
        presenter.getData(myJournalRequestData,key);
    }


    @Override
    public void returnData(int key, List<MyjournalRponseData> data) {

        switch (key){
            case 1:
                mMyJournalAdapter.setmDatas(data);
                mLvMyjournal.setAdapter(mMyJournalAdapter);
                mMyJournalAdapter.notifyDataSetChanged();
                break;
        }
    }

    /**
     * 日期选中的回调
     * todate 不为空，忽略index，（日 日志）
     * todate 为空 ，index 为准 （周 日志）
     */
    @Override
    public void CalendarSelcet(String datetext, Date date) {

        String rightDate = sDf.format(date);
        tcmbBar.setRightAllText(datetext);
        if (type.equals("weekPlan")||type.equals("weekWork")){
            todate = "";
            index = getWeekOfYear(nowDate)-getWeekOfYear(date);
            Log.i("----------","index = "+index);
        }else {
            todate = datetext;
        }

        postData(index,type,todate,1);
    }

    /**
     * 条目点击的回调
     * @param position  条目位置
     */

    @Override
    public void DayPlanClick(int position) {


        if (roleClass == 0){
            Intent intent = new Intent(MyJournalActivity.this, XsDayplanDetailsActivity.class);
            intent.putExtra("dpdate",mMyJournalAdapter.getmDatas().get(position).getShortRecordDate());
            startActivity(intent);
        }else {
            Intent intent = new Intent(MyJournalActivity.this, HtDayplanDetails.class);
            intent.putExtra("dpdate",mMyJournalAdapter.getmDatas().get(position).getShortRecordDate());
            startActivity(intent);
        }
    }

    @Override
    public void DaySumClick(int position) {

        if (roleClass == 0){
            Intent intent = new Intent(MyJournalActivity.this, XsDaysumDetailsActivity.class);
            intent.putExtra("dsdate",mMyJournalAdapter.getmDatas().get(position).getShortRecordDate());
            startActivity(intent);

        }else {
            Intent intent = new Intent(MyJournalActivity.this, HtDaysumDetailsActivity.class);
            intent.putExtra("dsdate",mMyJournalAdapter.getmDatas().get(position).getShortRecordDate());
            startActivity(intent);
        }
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

    //获取当前日期所在周 为一年的第几周；
    public int getWeekOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);

        return c.get(Calendar.WEEK_OF_YEAR);
    }

}
