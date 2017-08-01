package com.wtcrmandroid.activity.journalmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.base.BaseActivity;
import com.wtcrmandroid.base.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.journalmanager.present.HtWeekSumDetailsPresenter;
import com.wtcrmandroid.adapter.listview.CommentAdapter;
import com.wtcrmandroid.adapter.listview.WeeksumDetailsAdapter;
import com.wtcrmandroid.model.reponsedata.WeeksumDetailsData;
import com.wtcrmandroid.model.reponsedata.CommentData;
import com.wtcrmandroid.model.reponsedata.WeekSumDetailsRpData;
import com.wtcrmandroid.model.requestdata.WeekDetailsRequestData;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.view.listview.MyListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * 周总计详情
 *
 * @author zxd
 * @date 2017/6/12
 */

public class WeeksumDetailsActivity extends BaseActivity<HtWeekSumDetailsPresenter,WeekSumDetailsRpData> {

    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.tv_weeksum)
    TextView mTvWeeksum;            //详情日期
    @BindView(R.id.lv_weeksum)
    MyListView mLvWeeksum;            //总结列表
    @BindView(R.id.lv_comment)
    MyListView mLvComment;          //评论列表

    private String weekStart;
    private String weekEnd;
    View footsum;
    View headView;
    ViewHolder viewHolder;
    TextView learnText;

    private WeeksumDetailsAdapter mAdapter;
    private CommentAdapter mCommentAdapter;


    @Override
    protected int layout() {
        return R.layout.activity_weeksum_details;
    }

    @Override
    protected void initView() {
        presenter = new HtWeekSumDetailsPresenter(this,this);
        mTitlebar.setTitletext("日志详情");
        mTitlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeeksumDetailsActivity.this.finish();
            }
        });
        weekStart = getIntent().getStringExtra("wsbegin");
        weekEnd = getIntent().getStringExtra("wsend");
        mTvWeeksum.setText(weekStart+"-"+weekEnd+"周总结");

        WeekDetailsRequestData weekDetailsRequestData = new WeekDetailsRequestData();
        weekDetailsRequestData.setUserId(MyApplication.application.getLoginData().getUserID());
        weekDetailsRequestData.setType("week");
        weekDetailsRequestData.setIsPlan("false");
        weekDetailsRequestData.setWeekBegin(weekStart);
        weekDetailsRequestData.setWeekEnd(weekEnd);
        presenter.GetWeeSumDeails(weekDetailsRequestData);

        footsum = LayoutInflater.from(this).inflate(R.layout.item_sum_foot,null);
        learnText = (TextView)footsum.findViewById(R.id.tv_daysum_xxfx);
        mLvWeeksum.addFooterView(footsum);


        headView = LayoutInflater.from(this).inflate(R.layout.item_comment_head, null);
        viewHolder = new ViewHolder(headView);
        headView.setTag(viewHolder);
        mLvComment.addHeaderView(headView);

    }


    @Override
    public void returnData(int key, WeekSumDetailsRpData data) {

        List<WeeksumDetailsData> work = data.getWork();
        List<CommentData> exam = data.getExam();
        String learning = data.getLearning();

        if (work.size()>0){
            footsum.setVisibility(View.VISIBLE);
        }else {
            footsum.setVisibility(View.GONE);
        }
        mAdapter = new WeeksumDetailsAdapter(this, work);
        learnText.setText(learning);
        mLvWeeksum.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        if (exam.size() > 0){
            headView.setVisibility(View.VISIBLE);
        }else {
            headView.setVisibility(View.GONE);
        }
        mCommentAdapter = new CommentAdapter(this, exam);
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
