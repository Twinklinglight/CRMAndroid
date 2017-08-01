package com.wtcrmandroid.fragment.journalmanager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.journalmanager.XsDaysumDetailsActivity;
import com.wtcrmandroid.adapter.listview.CommentAdapter;
import com.wtcrmandroid.adapter.listview.HtDaysumDetailsAdapter;
import com.wtcrmandroid.BaseFragment;
import com.wtcrmandroid.model.reponsedata.CommentData;
import com.wtcrmandroid.model.reponsedata.HtDaysumDetailsData;
import com.wtcrmandroid.view.listview.MyListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 工作总计fragment
 * Created by zxd on 2017/6/12
 */

public class DaysumWorkPlanFragment extends BaseFragment {

    @BindView(R.id.lv_work_plan)
    MyListView mLvWorkPlan;
    @BindView(R.id.iv_comment)
    MyListView mIvComment;
    private CommentAdapter mCommentAdapter;
    private List<CommentData> mCommentDatas;
    private String level= "";
    private String Learning = "";

    private HtDaysumDetailsAdapter mDetailsAdapter;
    private List<HtDaysumDetailsData>mDataList;


    @Override
    protected int Rlayout() {
        return R.layout.fragment_daysum_workplan;
    }

    @Override
    public void init() {

        XsDaysumDetailsActivity activity = (XsDaysumDetailsActivity)getActivity();

        mDataList = activity.DaysumData.getWork().getWorkdetail();
        mCommentDatas = activity.DaysumData.getExam();
        Learning = activity.DaysumData.getLearning();

        mDetailsAdapter = new HtDaysumDetailsAdapter(activity,mDataList);
        mLvWorkPlan.setAdapter(mDetailsAdapter);
        View footview = LayoutInflater.from(activity).inflate(R.layout.item_sum_foot,null);
        TextView tvLearn = (TextView)footview.findViewById(R.id.tv_daysum_xxfx);
        tvLearn.setText(Learning);
        mLvWorkPlan.addFooterView(footview);
        if (mDataList.size() == 0) footview.setVisibility(View.GONE);
        mDetailsAdapter.notifyDataSetChanged();

        mCommentAdapter = new CommentAdapter(getActivity(), mCommentDatas);
        mIvComment.setAdapter(mCommentAdapter);

        if (mCommentDatas.size()>0){

            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_comment_head, null);
            ViewHolder viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
            viewHolder.mTvCommentCount.setText("评论("+mCommentDatas.size()+")");
            mIvComment.addHeaderView(view);
        }
        mCommentAdapter.notifyDataSetChanged();



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
