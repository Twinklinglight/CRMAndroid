package com.wtcrmandroid.fragment.journalmanager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wtcrmandroid.BaseFragment;
import com.wtcrmandroid.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.listview.AddPurposeFragmentAdapter;
import com.wtcrmandroid.adapter.listview.CommentAdapter;
import com.wtcrmandroid.adapter.listview.HtDayplanDetailsAdapter;
import com.wtcrmandroid.adapter.listview.HtDaysumDetailsAdapter;
import com.wtcrmandroid.adapter.listview.IfgetSingleDetailsAdapter;
import com.wtcrmandroid.adapter.listview.MajorCustomerAdapter;
import com.wtcrmandroid.adapter.listview.SingleFragmentAdapter;
import com.wtcrmandroid.adapter.listview.WorkLoadFgAdapter;
import com.wtcrmandroid.fragment.journalmanager.presenter.XsDepartDayPresenter;
import com.wtcrmandroid.model.reponsedata.CommentData;
import com.wtcrmandroid.model.reponsedata.XsDausumWorkData;
import com.wtcrmandroid.model.reponsedata.XsDayDepartRp;
import com.wtcrmandroid.model.reponsedata.XsDayplanDetailsRP;
import com.wtcrmandroid.model.reponsedata.XsDaysumDetailsRP;
import com.wtcrmandroid.model.requestdata.CommintRQ;
import com.wtcrmandroid.model.requestdata.DayDetailsRQ;
import com.wtcrmandroid.model.requestdata.WorkOrder;
import com.wtcrmandroid.utils.DateUtil;
import com.wtcrmandroid.view.dialog.CalendarDialog;
import com.wtcrmandroid.view.dialog.CommentDialog;
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
 * 部门员工日志 销售某天日志详情
 * Created by zxd on 2017/6/23
 */

public class DepartXsDayjournalFragment extends BaseFragment<XsDepartDayPresenter, Object> implements CommentDialog.submitListener, CalendarDialog.CalendarListener {


    @BindView(R.id.tv_date)
    TextView mTvDate;                       //时间显示textview
    @BindView(R.id.lv_dayplan_details)
    MyListView mLvDayplanDetails;           //工作计划列表
    @BindView(R.id.lv_single_analysis)
    MyListView mLvSingleAnalysis;           //预测到单客户列表
    @BindView(R.id.lv_major_genjin)
    MyListView mLvMajorGenjin;              //重点意向客户列表
    @BindView(R.id.lv_daysum_details)
    MyListView mLvDaysumDetails;            //工作总结列表
    @BindView(R.id.tv_phone_count)
    TextView mTvPhoneCount;                 //有效电话数量
    @BindView(R.id.tv_shangmen_count)
    TextView mTvShangmenCount;              //有效上门
    @BindView(R.id.tv_A_count)
    TextView mTvACount;                     //A类客户库存
    @BindView(R.id.tv_B_count)
    TextView mTvBCount;                     //B类客户库存
    @BindView(R.id.tv_addA_count)
    TextView mTvAddACount;                  //新增A类
    @BindView(R.id.tv_addB_count)
    TextView mTvAddBCount;                  //新增B类
    @BindView(R.id.tv_krl_count)
    TextView mTvKrlCount;                   //库容量
    @BindView(R.id.lv_load)
    MyListView lvLoad;                      //汇款到单
    @BindView(R.id.lv_ifget_single)
    MyListView mLvIfgetSingle;              //预测到单客户是否踩中列表
    @BindView(R.id.lv_add_customer)
    MyListView mLvAddCustomer;              //新增意向客户列表
    @BindView(R.id.lv_comment)
    MyListView mLvComment;                  //评论列表
    Unbinder unbinder;
    @BindView(R.id.ll_timeTitle)
    LinearLayout llTimeTitle;



    private String datetime;    //提交时间
    private int userid;         //部门员工id
    private int Logid;          //日志id
    private View footview;      //总结footview;
    private View commentHead;   //评论headview;
    private ViewHolder viewHolder;
    private TextView learnStudy;//学习反省
    private String level;
    private CalendarPopupWindow Calendarwindow;
    private HtDayplanDetailsAdapter xsDayplanAdapter;
    private SingleFragmentAdapter singleFragmentAdapter;
    private MajorCustomerAdapter majorCustomerAdapter;
    private HtDaysumDetailsAdapter daysumDetailsAdapter;
    private WorkLoadFgAdapter workLoadFgAdapter;
    private IfgetSingleDetailsAdapter ifgetSingleDetailsAdapter;
    private AddPurposeFragmentAdapter addPurposeFragmentAdapter;
    private CommentAdapter commentAdapter;


    @Override
    public void returnData(int key, Object data) {

        switch (key) {
            case 1:
                XsDayDepartRp dayData = (XsDayDepartRp)data;

                XsDayplanDetailsRP planData = dayData.getPlan();
                XsDausumWorkData work = dayData.getWork();
                List<CommentData> exam = dayData.getExam();
                learnStudy.setText(dayData.getLearning());
                Logid = dayData.getLogId();
                level = dayData.getLeve();

                xsDayplanAdapter = new HtDayplanDetailsAdapter(getActivity(), planData.getWorkdetail());
                mLvDayplanDetails.setAdapter(xsDayplanAdapter);
                xsDayplanAdapter.notifyDataSetChanged();                    //日计划详情

                singleFragmentAdapter = new SingleFragmentAdapter(getActivity(), planData.getWorkDreamOrder());
                mLvSingleAnalysis.setAdapter(singleFragmentAdapter);
                singleFragmentAdapter.notifyDataSetChanged();               //预测单客户

                majorCustomerAdapter = new MajorCustomerAdapter(getActivity(), planData.getWorkFocus());
                mLvMajorGenjin.setAdapter(majorCustomerAdapter);
                majorCustomerAdapter.notifyDataSetChanged();                //重点客户

                daysumDetailsAdapter = new HtDaysumDetailsAdapter(getActivity(), work.getWorkdetail());
                mLvDaysumDetails.setAdapter(daysumDetailsAdapter);
                daysumDetailsAdapter.notifyDataSetChanged();                //日总结列表

                List<WorkOrder> workorder = work.getWorkorder();

                if (workorder != null){

                    if (workorder.size()>0){
                        WorkOrder order = workorder.get(0);
                        mTvPhoneCount.setText(order.getTrueCall());
                        mTvACount.setText(order.getAStore());
                        mTvBCount.setText(order.getBStore());
                        mTvAddACount.setText(order.getNewAStore());
                        mTvAddBCount.setText(order.getNewBStore());
                        mTvKrlCount.setText(order.getStroe());
                        mTvShangmenCount.setText(order.getTrueVisit());     //今日工作量
                    }

                }
                workLoadFgAdapter = new WorkLoadFgAdapter(getActivity(), work.getWorkload());
                lvLoad.setAdapter(workLoadFgAdapter);
                workLoadFgAdapter.notifyDataSetChanged();           //回款到单

                ifgetSingleDetailsAdapter = new IfgetSingleDetailsAdapter(getActivity(), work.getWorkdream());
                mLvIfgetSingle.setAdapter(ifgetSingleDetailsAdapter);
                ifgetSingleDetailsAdapter.notifyDataSetChanged();       //单客户是否踩中

                addPurposeFragmentAdapter = new AddPurposeFragmentAdapter(getActivity(), work.getAddcustinfo());
                mLvAddCustomer.setAdapter(addPurposeFragmentAdapter);
                addPurposeFragmentAdapter.notifyDataSetChanged();       //新增意向客户

                if (exam.size() > 0) {
                    viewHolder.tvCommentCount.setVisibility(View.VISIBLE);
                } else {
                    viewHolder.tvCommentCount.setVisibility(View.GONE);
                }
                viewHolder.tvCommentCount.setText("评论(" + exam.size() + ")");
                commentAdapter = new CommentAdapter(getActivity(), exam, level);
                mLvComment.setAdapter(commentAdapter);
                commentAdapter.notifyDataSetChanged();
                break;
            case 2:
                Toast.makeText(getContext(), "评论成功", Toast.LENGTH_SHORT).show();
                postData(userid, datetime);  //刷新列表
                break;
        }

    }

    @Override
    protected int Rlayout() {
        return R.layout.fragment_depart_xsdayjournal;
    }

    @Override
    public void init() {
        presenter = new XsDepartDayPresenter(this, getContext());

        datetime = DateUtil.getSubToaday();
        mTvDate.setText(DateUtil.getTodayString());

        footview = LayoutInflater.from(getContext()).inflate(R.layout.item_sum_foot, null);
        learnStudy = (TextView) footview.findViewById(R.id.tv_daysum_xxfx);
        mLvDaysumDetails.addFooterView(footview);       //学习反省

        commentHead = LayoutInflater.from(getActivity()).inflate(R.layout.item_comment_head, null);
        viewHolder = new ViewHolder(commentHead);
        commentHead.setTag(viewHolder);
        mLvComment.addHeaderView(commentHead);  //评论数量

        Bundle arguments = getArguments();
        if (arguments != null) {
            userid = arguments.getInt("userid");
        }
        postData(userid, datetime);

    }

    //取消popupwindow
    public void cancleWindows(){
        if (Calendarwindow != null){

            if (Calendarwindow.isShowing()){
                Calendarwindow.dismiss();
            }
        }
    }
    @OnClick({R.id.iv_calender, R.id.tv_write_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_calender:
                if (Calendarwindow == null) {
                    Calendarwindow = new CalendarPopupWindow(getContext(), llTimeTitle, this);
                    Calendarwindow.show();
                } else {
                    if (Calendarwindow.isShowing()) {
                        Calendarwindow.dismiss();
                    } else {
                        Calendarwindow.show();
                    }
                }
                break;
            case R.id.tv_write_comment:
                new CommentDialog(getContext(), this).show();
                break;
        }
    }

    //提交评论回调
    @Override
    public void clickOk(String leve, String context) {

        CommintRQ commintRQ = new CommintRQ();
        commintRQ.setUserId(MyApplication.application.getLoginData().getUserID());
        commintRQ.setLogId(Logid);
        commintRQ.setLeve(leve);
        commintRQ.setExamineText(context);
        presenter.PostCommint(commintRQ);

    }

    //日期选择的回调
    @Override
    public void CalendarSelcet(String datetext, Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        datetime = sdf2.format(date);
        mTvDate.setText(sdf.format(date));
        postData(userid,datetime );
    }

    private void postData(int userid, String data) {
        DayDetailsRQ dayDetailsRQ = new DayDetailsRQ();
        dayDetailsRQ.setUserId(userid);
        dayDetailsRQ.setNowDate(data);
        dayDetailsRQ.setRoleClass(0);
        dayDetailsRQ.setType("day");
        dayDetailsRQ.setIsPlan("");
        presenter.PostDepartDayplan(dayDetailsRQ);
    }


    static class ViewHolder {
        @BindView(R.id.tv_comment_count)
        TextView tvCommentCount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
