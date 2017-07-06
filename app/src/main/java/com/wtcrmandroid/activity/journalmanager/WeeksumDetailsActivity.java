package com.wtcrmandroid.activity.journalmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.journalmanager.present.HtWeekSumDetailsPresenter;
import com.wtcrmandroid.adapter.listview.CommentAdapter;
import com.wtcrmandroid.adapter.listview.WeeksumDetailsAdapter;
import com.wtcrmandroid.model.WeeksumDetailsData;
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

    private WeeksumDetailsAdapter mAdapter;
    private CommentAdapter mCommentAdapter;

//    private List<WeeksumDetailsData> mDetailsDatas;
//    private List<CommentData> mCommentDatas;

    @Override
    protected int layout() {
        return R.layout.activity_weeksum_details;
    }

    @Override
    protected void initView() {
        presenter = new HtWeekSumDetailsPresenter(this,this);
        WeekDetailsRequestData weekDetailsRequestData = new WeekDetailsRequestData();
        weekDetailsRequestData.setUserId(3066);
        weekDetailsRequestData.setType("week");
        weekDetailsRequestData.setPlan(false);
        weekDetailsRequestData.setWeekBegin("2017-06-12");
        weekDetailsRequestData.setWeekEnd("2017-06-18");
        presenter.GetWeeSumDeails(weekDetailsRequestData);
//        mDetailsDatas = new ArrayList<>();
//        mCommentDatas = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
//            CommentData commentData = new CommentData();
//            WeeksumDetailsData mWeekData = new WeeksumDetailsData();
//            mWeekData.setWeekTitle("本周总结"+(i+1));
//            commentData.setCommentPerson("总监");
//            commentData.setCommentTime("2017-6-12");
//            commentData.setCommentJob("弄着、弄那");
//            commentData.setCommentContent("除了这你还弄啥类");
//            mCommentDatas.add(commentData);
//            mDetailsDatas.add(mWeekData);
        }

    }


    @Override
    public void returnData(int key, WeekSumDetailsRpData data) {

        List<WeeksumDetailsData> work = data.getWork();
        List<CommentData> exam = data.getExam();
        String leve = data.getLeve();
        String learning = data.getLearning();

        mAdapter = new WeeksumDetailsAdapter(this, work);
        mLvWeeksum.setAdapter(mAdapter);
        View footsum = LayoutInflater.from(this).inflate(R.layout.item_sum_foot,null);
        TextView learnText = (TextView)footsum.findViewById(R.id.tv_daysum_xxfx);
        learnText.setText(learning);
        mLvWeeksum.addFooterView(footsum);
        mAdapter.notifyDataSetChanged();

        if (exam.size() > 0){

            mCommentAdapter = new CommentAdapter(this, exam,leve);
            mLvComment.setAdapter(mCommentAdapter);

            View headView = LayoutInflater.from(this).inflate(R.layout.item_comment_head, null);
            ViewHolder viewHolder = new ViewHolder(headView);
            headView.setTag(viewHolder);
            viewHolder.mTvCommentCount.setText("评论("+exam.size()+")");
            mLvComment.addHeaderView(headView);

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
