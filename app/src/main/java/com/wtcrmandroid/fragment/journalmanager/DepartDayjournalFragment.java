package com.wtcrmandroid.fragment.journalmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.listview.CommentAdapter;
import com.wtcrmandroid.adapter.listview.HtDayplanDetailsAdapter;
import com.wtcrmandroid.adapter.listview.HtDaysumDetailsAdapter;
import com.wtcrmandroid.fragment.journalmanager.presenter.DepartDayPresenter;
import com.wtcrmandroid.model.reponsedata.BaseData;
import com.wtcrmandroid.model.reponsedata.DaySumDetailsRpData;
import com.wtcrmandroid.model.requestdata.CommintRQ;
import com.wtcrmandroid.model.requestdata.DayDetailsRQ;
import com.wtcrmandroid.view.dialog.CalendarDialog;
import com.wtcrmandroid.view.dialog.CommentDialog;
import com.wtcrmandroid.BaseFragment;
import com.wtcrmandroid.model.reponsedata.CommentData;
import com.wtcrmandroid.model.HtDayplanDetailsData;
import com.wtcrmandroid.model.reponsedata.HtDaysumDetailsData;
import com.wtcrmandroid.view.listview.MyListView;
import com.wtcrmandroid.view.popupwindow.CalendarPopupWindow;

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

    private int Logid;

    private HtDayplanDetailsAdapter mDayplanAdapter;

    private HtDaysumDetailsAdapter mDysumAdapter;

    private CommentAdapter mCommentAdapter;

    private CalendarPopupWindow Calendarwindow;


    @Override
    public void returnData(int key, Object data) {

        switch (key){
            case 1:         //日计划列表
                mDayplanAdapter = new HtDayplanDetailsAdapter(getActivity(),(List<HtDayplanDetailsData>) data);
                mLvDayplanDetails.setAdapter(mDayplanAdapter);
                mDayplanAdapter.notifyDataSetChanged();
                break;
            case 2:
                DaySumDetailsRpData daysumData = (DaySumDetailsRpData)data;
                List<HtDaysumDetailsData> work = daysumData.getWork();
                List<CommentData> exam = daysumData.getExam();
                String learning = daysumData.getLearning();
                String leve = daysumData.getLeve();
                Logid = daysumData.getLogId();

                mDysumAdapter = new HtDaysumDetailsAdapter(getActivity(), work);
                mLvDaysumDetails.setAdapter(mDysumAdapter);
                mDysumAdapter.notifyDataSetChanged();

                View footview = LayoutInflater.from(getContext()).inflate(R.layout.item_sum_foot,null);
                TextView learnStudy = (TextView) footview.findViewById(R.id.tv_daysum_xxfx);
                learnStudy.setText(learning);
                mLvDaysumDetails.addFooterView(footview);       //学习反省

                if (exam != null){

                    View commentHead = LayoutInflater.from(getActivity()).inflate(R.layout.item_comment_head, null);
                    ViewHolder viewHolder = new ViewHolder(commentHead);
                    commentHead.setTag(viewHolder);
                    viewHolder.mTvCommentCount.setText("评论(" + exam.size() + ")");
                    mLvComment.addHeaderView(commentHead);  //评论数量

                }
                mCommentAdapter = new CommentAdapter(getActivity(), exam,leve);
                mLvComment.setAdapter(mCommentAdapter);     //评论列表
                mCommentAdapter.notifyDataSetChanged();

                break;
            case 3:
                Toast.makeText(DepartDayjournalFragment.this.getContext(), "成功", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    protected int Rlayout() {
        return R.layout.fragment_department_dayjournal;
    }

    @Override
    protected void init() {

        presenter = new DepartDayPresenter(this,getContext());
        postData(3066,"2017-06-26");

    }


    @OnClick({R.id.iv_calender,R.id.tv_write_comment})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.iv_calender:           //选择日期
                if (Calendarwindow == null){
                    Calendarwindow = new CalendarPopupWindow(getContext(),llcalendar,this);
                    Calendarwindow.show();
                }else {
                    Calendarwindow.dismiss();
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

        postData(3066,datetext);

    }


    //提交评论
    @Override
    public void clickOk(String leve, String context) {
        CommintRQ commintRQ = new CommintRQ();
        commintRQ.setUserId(3066);
        commintRQ.setLogId(Logid);
        commintRQ.setLeve(leve);
        commintRQ.setExamineText(context);
        presenter.postDayCommint(commintRQ);

    }

    private void postData(int userid,String data){

        DayDetailsRQ dayDetailsRQ = new DayDetailsRQ();
        dayDetailsRQ.setUserId(userid);
        dayDetailsRQ.setType("day");
        dayDetailsRQ.setIsPlan(true);
        dayDetailsRQ.setNowDate(data);
        presenter.postDepartDay(dayDetailsRQ);

        DayDetailsRQ dayDetailsRQ2 = new DayDetailsRQ();
        dayDetailsRQ2.setUserId(userid);
        dayDetailsRQ2.setType("day");
        dayDetailsRQ2.setIsPlan(false);
        dayDetailsRQ2.setNowDate(data);
        presenter.postDaysum(dayDetailsRQ2);

    }


    static class ViewHolder {
        @BindView(R.id.tv_comment_count)
        TextView mTvCommentCount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
