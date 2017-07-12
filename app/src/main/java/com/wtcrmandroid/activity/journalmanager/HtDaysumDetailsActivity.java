package com.wtcrmandroid.activity.journalmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.journalmanager.present.HtDaysumDetailsPresenter;
import com.wtcrmandroid.adapter.listview.CommentAdapter;
import com.wtcrmandroid.adapter.listview.HtDaysumDetailsAdapter;
import com.wtcrmandroid.model.reponsedata.CommentData;
import com.wtcrmandroid.model.reponsedata.DaySumDetailsRpData;
import com.wtcrmandroid.model.reponsedata.HtDaysumDetailsData;
import com.wtcrmandroid.model.requestdata.DayDetailsRQ;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.view.listview.MyListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 后台日总结详情
 *
 * @author zxd
 * @date 2017/6/9
 */

public class HtDaysumDetailsActivity extends BaseActivity<HtDaysumDetailsPresenter,DaySumDetailsRpData> {

    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.tv_journal_title)
    TextView mTvJournalTitle;       //总结title
    @BindView(R.id.lv_work)
    MyListView mLvWork;             //工作列表
    @BindView(R.id.lv_comment)
    MyListView mLvComment;          //评论列表

    private String time;
    View footview;
    View comentHead;
    TextView learnStudy;
    ViewHolder viewHolder;

    private HtDaysumDetailsAdapter mDetailsAdapter;
    private CommentAdapter mCommentAdapter;


    @Override
    protected int layout() {
        return R.layout.activity_ht_daysum_details;
    }

    @Override
    protected void initView() {
        presenter = new HtDaysumDetailsPresenter(this,this);

        time = getIntent().getStringExtra("dsdate");
        mTvJournalTitle.setText(time+"总结");

        DayDetailsRQ dayDetailsRequestData = new DayDetailsRQ();
        dayDetailsRequestData.setUserId(MyApplication.application.getLoginData().getUserID());
        dayDetailsRequestData.setType("day");
        dayDetailsRequestData.setIsPlan(false);
        dayDetailsRequestData.setNowDate(time);

        presenter.GetDaySumDetails(dayDetailsRequestData);

        mTitlebar.setTitletext("日志详情");

        //返回
        mTitlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HtDaysumDetailsActivity.this.finish();
            }
        });

        footview = LayoutInflater.from(this).inflate(R.layout.item_sum_foot,null);
        learnStudy = (TextView) footview.findViewById(R.id.tv_daysum_xxfx);
        mLvWork.addFooterView(footview);

        comentHead = LayoutInflater.from(this).
                inflate(R.layout.item_comment_head, null);
        viewHolder = new ViewHolder(comentHead);
        mLvComment.addHeaderView(comentHead);

    }

    @Override
    public void returnData(int key, DaySumDetailsRpData data) {

        List<HtDaysumDetailsData> work = data.getWork();
        List<CommentData> exam = data.getExam();
        String leve = data.getLeve();
        String learning = data.getLearning();

        if (work.size()>0){
            footview.setVisibility(View.VISIBLE);
        }else {
            footview.setVisibility(View.GONE);
        }
        mDetailsAdapter = new HtDaysumDetailsAdapter(this, work);
        mLvWork.setDivider(null);       //去除分割线
        mLvWork.setAdapter(mDetailsAdapter);
        learnStudy.setText(learning);
        mDetailsAdapter.notifyDataSetChanged();

        if (exam.size() > 0) {
            comentHead.setVisibility(View.VISIBLE);
        }else {
            comentHead.setVisibility(View.GONE);
        }
        mCommentAdapter = new CommentAdapter(this, exam,leve);
        viewHolder.mTvCommentCount.setText("评论("+exam.size()+")");
        mLvComment.setAdapter(mCommentAdapter);
        mCommentAdapter.notifyDataSetChanged();

    }

    static class ViewHolder {
        @BindView(R.id.tv_comment_count)
        TextView mTvCommentCount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
