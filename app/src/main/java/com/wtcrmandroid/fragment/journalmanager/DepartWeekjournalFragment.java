package com.wtcrmandroid.fragment.journalmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wtcrmandroid.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.listview.CommentAdapter;
import com.wtcrmandroid.adapter.listview.WeekDayplanAdapter;
import com.wtcrmandroid.adapter.listview.WeeksumDetailsAdapter;
import com.wtcrmandroid.fragment.journalmanager.presenter.DepartWeekPresenter;
import com.wtcrmandroid.model.reponsedata.WeekSumDetailsRpData;
import com.wtcrmandroid.model.requestdata.CommintRQ;
import com.wtcrmandroid.model.requestdata.WeekDetailsRequestData;
import com.wtcrmandroid.view.dialog.CommentDialog;
import com.wtcrmandroid.BaseFragment;
import com.wtcrmandroid.model.reponsedata.CommentData;
import com.wtcrmandroid.model.WeeksumDetailsData;
import com.wtcrmandroid.model.WriterWeekPlaneData;
import com.wtcrmandroid.view.dialog.WeekDialog;
import com.wtcrmandroid.view.listview.MyListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 部门员工 周日志 详情
 * Created by zxd on 2017/6/23
 */

public class DepartWeekjournalFragment extends BaseFragment<DepartWeekPresenter,Object> implements CommentDialog.submitListener, WeekDialog.WeekListener {

    @BindView(R.id.tv_date)
    TextView mTvDate;
    @BindView(R.id.ll_week_date)
    LinearLayout mLlWeekDate;
    @BindView(R.id.lv_weekplan_details)
    MyListView mLvWeekplanDetails;
    @BindView(R.id.lv_weeksum_details)
    MyListView mLvWeeksumDetails;
    @BindView(R.id.lv_comment)
    MyListView mLvComment;
    Unbinder unbinder;

    private List<WriterWeekPlaneData> mWeekplanDataList;
    private WeekDayplanAdapter mWeekplanAdapter;

    private List<WeeksumDetailsData> mWeeksumDataList;
    private WeeksumDetailsAdapter mWeeksumAdapter;

    private List<CommentData> mCommentDataList;
    private CommentAdapter mCommentAdapter;
    private int logId;

    private int position;

    @Override
    public void returnData(int key, Object data) {

        switch (key){
            case 1:
                mWeekplanDataList = (List<WriterWeekPlaneData>)data;
                mWeekplanAdapter = new WeekDayplanAdapter(getActivity(), mWeekplanDataList);
                mLvWeekplanDetails.setAdapter(mWeekplanAdapter);
                mWeekplanAdapter.notifyDataSetChanged();
                break;
            case 2:
                WeekSumDetailsRpData weeksumData = (WeekSumDetailsRpData)data;
                mWeeksumDataList = weeksumData.getWork();
                mCommentDataList = weeksumData.getExam();
                String learning = weeksumData.getLearning();
                String leve = weeksumData.getLeve();
                logId = weeksumData.getLogId();
                mWeeksumAdapter = new WeeksumDetailsAdapter(getActivity(), mWeeksumDataList);
                mLvWeeksumDetails.setAdapter(mWeeksumAdapter);
                mWeeksumAdapter.notifyDataSetChanged();

                View footview = LayoutInflater.from(getContext()).inflate(R.layout.item_sum_foot,null);
                TextView learnStudy = (TextView) footview.findViewById(R.id.tv_daysum_xxfx);
                learnStudy.setText(learning);
                mLvWeeksumDetails.addFooterView(footview);

                if (mCommentDataList.size()>0){
                    View commenthead = LayoutInflater.from(getActivity()).inflate(R.layout.item_comment_head, null);
                    ViewHolder viewHolder = new ViewHolder(commenthead);
                    commenthead.setTag(viewHolder);
                    viewHolder.mTvCommentCount.setText("评价(" + mCommentDataList.size() + ")");
                    mLvComment.addHeaderView(commenthead);

                    mCommentAdapter = new CommentAdapter(getActivity(), mCommentDataList,leve);
                    mLvComment.setAdapter(mCommentAdapter);
                }
                break;
            case 3:
                Toast.makeText(getContext(),"成功",Toast.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    protected int Rlayout() {
        return R.layout.fragment_department_weekjournal;
    }

    @Override
    protected void init() {
        presenter = new DepartWeekPresenter(this,getContext());
        postData(3066,"2017-06-12","2017-06-18");

    }

    @OnClick({R.id.ll_week_date, R.id.tv_write_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_week_date:         //获取日期
                new WeekDialog(getContext(),this,position).show();
                break;
            case R.id.tv_write_comment:     //写评论
                new CommentDialog(getContext(),this).show();
                break;
        }
    }


    // 提交评论
    @Override
    public void clickOk(String leve, String context) {
        CommintRQ commintRQ = new CommintRQ();
        commintRQ.setUserId(MyApplication.application.getLoginData().getUserID());
        commintRQ.setLogId(logId);
        commintRQ.setLeve(leve);
        commintRQ.setExamineText(context);
        presenter.postWeekCommint(commintRQ);
    }

    //日期选择的回调
    @Override
    public void weekSelect(String weekText, int position) {

        this.position = position;
        mTvDate.setText(weekText);

    }

    private void postData(int userid,String begintime,String endtime){
        WeekDetailsRequestData weekDetailsRequestData = new WeekDetailsRequestData();
        weekDetailsRequestData.setUserId(userid);
        weekDetailsRequestData.setType("week");
        weekDetailsRequestData.setPlan(true);
        weekDetailsRequestData.setWeekBegin(begintime);
        weekDetailsRequestData.setWeekEnd(endtime);
        presenter.postDepartWeek(weekDetailsRequestData);

        WeekDetailsRequestData weekDetailsRequestData2 = new WeekDetailsRequestData();
        weekDetailsRequestData2.setUserId(userid);
        weekDetailsRequestData2.setType("week");
        weekDetailsRequestData2.setPlan(false);
        weekDetailsRequestData2.setWeekBegin(begintime);
        weekDetailsRequestData2.setWeekEnd(endtime);
        presenter.postWeeksum(weekDetailsRequestData2);

    }

    static class ViewHolder {
        @BindView(R.id.tv_comment_count)
        TextView mTvCommentCount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
