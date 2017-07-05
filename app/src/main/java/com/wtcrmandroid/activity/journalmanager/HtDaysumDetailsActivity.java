package com.wtcrmandroid.activity.journalmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.activity.journalmanager.present.HtDaysumDetailsPresenter;
import com.wtcrmandroid.adapter.listview.CommentAdapter;
import com.wtcrmandroid.adapter.listview.HtDaysumDetailsAdapter;
import com.wtcrmandroid.model.reponsedata.DaySumDetailsRpData;
import com.wtcrmandroid.model.requestdata.DayDetailsRequestData;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.model.reponsedata.CommentData;
import com.wtcrmandroid.model.reponsedata.HtDaysumDetailsData;
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

    private List<HtDaysumDetailsData> mDtailsList;
    private List<CommentData> mCommentList;
    private HtDaysumDetailsAdapter mDetailsAdapter;
    private CommentAdapter mCommentAdapter;


    @Override
    protected int layout() {
        return R.layout.activity_ht_daysum_details;
    }

    @Override
    protected void initView() {
        presenter = new HtDaysumDetailsPresenter(this);
        DayDetailsRequestData dayDetailsRequestData = new DayDetailsRequestData();
        dayDetailsRequestData.setUserId(3066);
        dayDetailsRequestData.setType("day");
        dayDetailsRequestData.setIsPlan(false);
        dayDetailsRequestData.setNowDate("2017-06-13");

        presenter.GetDaySumDetails(dayDetailsRequestData);

        mDtailsList = new ArrayList<>();
        mCommentList = new ArrayList<>();
        mTitlebar.setTitletext("日志详情");

        //返回
        mTitlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HtDaysumDetailsActivity.this.finish();
            }
        });
        getData();

    }

    public void getData() {
       /* for (int i = 0; i < 3; i++) {
            HtDaysumDetailsData htDaysumDetailsData = new HtDaysumDetailsData();
            htDaysumDetailsData.setWorkComplete("基本完成");
            htDaysumDetailsData.setWorkContent("你干了啥说就是啥");
            htDaysumDetailsData.setWorkPerson("网二");
            htDaysumDetailsData.setWorkSort("A类");
            htDaysumDetailsData.setWorkStudy("今天收获很大，恩，都有沙雷");

            CommentData commentData = new CommentData();
            commentData.setCommentContent("已经查看，继续努力！");
            commentData.setCommentJob("部长");
            commentData.setCommentPerson("郭靖");
            commentData.setCommentTime("2017-5-7");

            mDtailsList.add(htDaysumDetailsData);
            mCommentList.add(commentData);
        }*/
    }

    @Override
    public void returnData(int key, DaySumDetailsRpData data) {

        List<HtDaysumDetailsData> work = data.getWork();
        List<CommentData> exam = data.getExam();
        String leve = data.getLeve();
        String learning = data.getLearning();

        mDetailsAdapter = new HtDaysumDetailsAdapter(this, work);
        mLvWork.setDivider(null);       //去除分割线
        mLvWork.setAdapter(mDetailsAdapter);

        View footview = LayoutInflater.from(this).inflate(R.layout.item_sum_foot,null);
        TextView learnStudy = (TextView) footview.findViewById(R.id.tv_daysum_xxfx);
        learnStudy.setText(learning);
        mLvWork.addFooterView(footview);

        mDetailsAdapter.notifyDataSetChanged();

        if (exam.size() > 0) {
            View view = LayoutInflater.from(this).
                    inflate(R.layout.item_comment_head, null);
            ViewHolder viewHolder = new ViewHolder(view);
            mLvComment.addHeaderView(view);
            viewHolder.mTvCommentCount.setText("评论("+exam.size()+")");
        }
        mCommentAdapter = new CommentAdapter(this, exam,leve);
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
