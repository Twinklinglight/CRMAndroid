package com.wtcrmandroid.fragment.journalmanager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wtcrmandroid.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.listview.CommentAdapter;
import com.wtcrmandroid.adapter.listview.HtDayplanDetailsAdapter;
import com.wtcrmandroid.adapter.listview.HtDaysumDetailsAdapter;
import com.wtcrmandroid.fragment.journalmanager.presenter.DepartDayPresenter;
import com.wtcrmandroid.model.reponsedata.DaySumDetailsRpData;
import com.wtcrmandroid.model.reponsedata.HtDayDepartRp;
import com.wtcrmandroid.model.requestdata.CommintRQ;
import com.wtcrmandroid.model.requestdata.DayDetailsRQ;
import com.wtcrmandroid.utils.DateUtil;
import com.wtcrmandroid.view.dialog.CalendarDialog;
import com.wtcrmandroid.view.dialog.CommentDialog;
import com.wtcrmandroid.BaseFragment;
import com.wtcrmandroid.model.reponsedata.CommentData;
import com.wtcrmandroid.model.reponsedata.HtDayplanDetailsData;
import com.wtcrmandroid.model.reponsedata.HtDaysumDetailsData;
import com.wtcrmandroid.view.listview.MyListView;
import com.wtcrmandroid.view.popupwindow.CalendarPopupWindow;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 部门员工一天日志详情
 * Created by zxd on 2017/6/23
 */

public class DepartDayjournalFragment extends BaseFragment<DepartDayPresenter,Object> implements CalendarDialog.CalendarListener, CommentDialog.submitListener {

    @BindView(R.id.ll_calendar)
    LinearLayout llcalendar;
    @BindView(R.id.tv_date)
    TextView mTvDate;           //日志时间
    @BindView(R.id.iv_calender)
    ImageView mIvCalender;      //选择日期按钮
    @BindView(R.id.lv_dayplan_details)
    MyListView mLvDayplanDetails;//日计划列表
    @BindView(R.id.lv_daysum_details)
    MyListView mLvDaysumDetails;//日总结列表
    @BindView(R.id.lv_comment)
    MyListView mLvComment;      //评论列表
    Unbinder unbinder;

    private int Logid;          //日志id

    private int userid;         //员工id

    private String datetime;

    TextView learnStudy;
    View commentHead;
    View footview;
    ViewHolder viewHolder;
    private String level;

    private HtDayplanDetailsAdapter mDayplanAdapter;

    private HtDaysumDetailsAdapter mDysumAdapter;

    private CommentAdapter mCommentAdapter;

    private CalendarPopupWindow Calendarwindow;

    private static final String TAG = "DepartDayjournalFragmen";

    @Override
    public void returnData(int key, Object data) {

        switch (key){
            case 1:
                HtDayDepartRp dayData = (HtDayDepartRp)data;
                List<HtDayplanDetailsData> plan = dayData.getPlan();
                List<HtDaysumDetailsData> work1 = dayData.getWork();
                List<CommentData> exam1 = dayData.getExam();
                learnStudy.setText(dayData.getLearning());
                Logid = dayData.getLogId();

                mDayplanAdapter = new HtDayplanDetailsAdapter(getActivity(),plan);
                mLvDayplanDetails.setAdapter(mDayplanAdapter);
                mDayplanAdapter.notifyDataSetChanged();             //日计划列表

                if (work1.size()>0){
                    footview.setVisibility(View.VISIBLE);
                }else {
                    footview.setVisibility(View.GONE);
                }
                mDysumAdapter = new HtDaysumDetailsAdapter(getActivity(), work1);
                mLvDaysumDetails.setAdapter(mDysumAdapter);
                mDysumAdapter.notifyDataSetChanged();

                if (exam1 != null){
                    if (exam1.size()>0){
                        commentHead.setVisibility(View.VISIBLE);
                    }else {
                        commentHead.setVisibility(View.GONE);
                    }

                    mCommentAdapter = new CommentAdapter(getActivity(), exam1);
                    mLvComment.setAdapter(mCommentAdapter);     //评论列表
                    viewHolder.mTvCommentCount.setText("评论(" + exam1.size() + ")");
                    mCommentAdapter.notifyDataSetChanged();
                }

                break;
            case 2:
                postData(userid,datetime);
                Toast.makeText(DepartDayjournalFragment.this.getContext(), "成功", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    protected int Rlayout() {
        return R.layout.fragment_department_dayjournal;
    }

    @Override
    public void init() {

        presenter = new DepartDayPresenter(this,getContext());
        datetime = DateUtil.getSubToaday();
        mTvDate.setText(DateUtil.getTodayString());

        footview = LayoutInflater.from(getContext()).inflate(R.layout.item_sum_foot,null);
        learnStudy = (TextView) footview.findViewById(R.id.tv_daysum_xxfx);
        mLvDaysumDetails.addFooterView(footview);       //学习反省

        commentHead = LayoutInflater.from(getActivity()).inflate(R.layout.item_comment_head, null);
        viewHolder = new ViewHolder(commentHead);
        commentHead.setTag(viewHolder);
        mLvComment.addHeaderView(commentHead);  //评论数量

        Bundle arguments = getArguments();
        if (arguments !=null){
            userid = arguments.getInt("userid");
        }
        postData(userid,datetime);

    }

    public void cancleWindows(){
        if (Calendarwindow != null){

            if (Calendarwindow.isShowing()){
                Calendarwindow.dismiss();
            }
        }
    }

    @OnClick({R.id.iv_calender,R.id.tv_write_comment})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.iv_calender:           //选择日期
                if (Calendarwindow == null){
                    Calendarwindow = new CalendarPopupWindow(getContext(),llcalendar,this);
                    Calendarwindow.show();
                }else {
                    if (Calendarwindow.isShowing()){
                        Calendarwindow.dismiss();
                    }else {
                        Calendarwindow.show();
                    }
                }
                break;
            case R.id.tv_write_comment:     //写评论
                new CommentDialog(getContext(),this).show();
                break;
        }
    }

    //日期选中回调
    @Override
    public void CalendarSelcet(String datetext, Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        datetime = sdf2.format(date);
        mTvDate.setText(sdf.format(date));
        postData(userid,datetime);

    }
    //提交评论
    @Override
    public void clickOk(String leve, String context) {
        CommintRQ commintRQ = new CommintRQ();
        commintRQ.setUserId(MyApplication.application.getLoginData().getUserID());
        commintRQ.setLogId(Logid);
        commintRQ.setLeve(leve);
        commintRQ.setExamineText(context);
        presenter.postDayCommint(commintRQ);

    }

    private void postData(int userid,String data){

        DayDetailsRQ dayDetailsRQ = new DayDetailsRQ();
        dayDetailsRQ.setUserId(userid);
        dayDetailsRQ.setType("day");
        dayDetailsRQ.setIsPlan("");
        dayDetailsRQ.setRoleClass(1);
        dayDetailsRQ.setNowDate(data);
        presenter.postDepartDay(dayDetailsRQ);

    }


    static class ViewHolder {
        @BindView(R.id.tv_comment_count)
        TextView mTvCommentCount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
