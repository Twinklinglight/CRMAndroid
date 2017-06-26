package com.wtcrmandroid.activity.journalmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.BaseActivity;
import com.wtcrmandroid.adapter.listview.CommentAdapter;
import com.wtcrmandroid.adapter.listview.WeeksumDetailsAdapter;
import com.wtcrmandroid.custompricing.TitleBar;
import com.wtcrmandroid.model.CommentData;
import com.wtcrmandroid.model.WeeksumDetailsData;
import com.wtcrmandroid.view.MyListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * 周总计详情
 *
 * @author zxd
 * @date 2017/6/12
 */

public class WeeksumDetailsActivity extends BaseActivity {

    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.tv_weeksum)
    TextView mTvWeeksum;            //详情日期
    @BindView(R.id.lv_weeksum)
    MyListView mLvWeeksum;            //总结列表
    @BindView(R.id.lv_comment)
    MyListView mLvComment;          //评论列表

    private WeeksumDetailsAdapter mAdapter;
    private CommentAdapter mCommentAdapter;

    private List<WeeksumDetailsData> mDetailsDatas;
    private List<CommentData> mCommentDatas;

    @Override
    protected int layout() {
        return R.layout.activity_weeksum_details;
    }

    @Override
    protected void initview() {
        mDetailsDatas = new ArrayList<>();
        mCommentDatas = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            CommentData commentData = new CommentData();
            WeeksumDetailsData mWeekData = new WeeksumDetailsData();
            mWeekData.setWeekTitle("本周总结"+(i+1));
            commentData.setCommentPerson("总监");
            commentData.setCommentTime("2017-6-12");
            commentData.setCommentJob("弄着、弄那");
            commentData.setCommentContent("除了这你还弄啥类");
            mCommentDatas.add(commentData);
            mDetailsDatas.add(mWeekData);
        }
        mAdapter = new WeeksumDetailsAdapter(this, mDetailsDatas);
        mLvWeeksum.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();


        mCommentAdapter = new CommentAdapter(this, mCommentDatas);
        mLvComment.setAdapter(mCommentAdapter);
        View headView = LayoutInflater.from(this).inflate(R.layout.item_comment_head, null);
        ViewHolder viewHolder = new ViewHolder(headView);
        headView.setTag(viewHolder);
        viewHolder.mTvCommentCount.setText("评论("+mCommentDatas.size()+")");
        mLvComment.addHeaderView(headView);
    }


    @Override
    public void returnData(int key, Object data) {

    }

    static class ViewHolder {
        @BindView(R.id.tv_comment_count)
        TextView mTvCommentCount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
