package com.wtcrmandroid.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.listview.CommentAdapter;
import com.wtcrmandroid.adapter.listview.HtDaysumDetailsAdapter;
import com.wtcrmandroid.custompricing.TitleBar;
import com.wtcrmandroid.http.retrofit2.data.BaseData;
import com.wtcrmandroid.model.CommentData;
import com.wtcrmandroid.model.HtDaysumDetailsData;
import com.wtcrmandroid.view.MyListView;

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

public class HtDaysumDetailsActivity extends BaseActivity {

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
    protected void initview() {
        mDtailsList = new ArrayList<>();
        mCommentList = new ArrayList<>();
        mTitlebar.setTitletext("日志详情");
        getData();
        mDetailsAdapter = new HtDaysumDetailsAdapter(this, mDtailsList);
        mLvWork.setAdapter(mDetailsAdapter);
        mDetailsAdapter.notifyDataSetChanged();

        if (mCommentList.size() > 0) {
            View view = LayoutInflater.from(this).
                    inflate(R.layout.item_comment_head, null);
            ViewHolder viewHolder = new ViewHolder(view);
            mLvComment.addHeaderView(view);
            viewHolder.mTvCommentCount.setText("评论("+mCommentList.size()+")");
        }
        mCommentAdapter = new CommentAdapter(this, mCommentList);
        mLvComment.setAdapter(mCommentAdapter);
        mCommentAdapter.notifyDataSetChanged();

    }

    @Override
    public void returnData(int key, BaseData data) {

    }

    public void getData() {
        for (int i = 0; i < 3; i++) {
            HtDaysumDetailsData htDaysumDetailsData = new HtDaysumDetailsData();
            htDaysumDetailsData.setWorkComplete("基本完成");
            htDaysumDetailsData.setWorkContent("你干了啥说就是啥");
            htDaysumDetailsData.setWorkPercent("20%");
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
        }
    }

    static class ViewHolder {
        @BindView(R.id.tv_comment_count)
        TextView mTvCommentCount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
