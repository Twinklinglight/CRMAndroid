package com.wtcrmandroid.fragment.journalmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.R;
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

    private HtDaysumDetailsAdapter mDetailsAdapter;
    private List<HtDaysumDetailsData>mDataList;


    @Override
    protected int Rlayout() {
        return R.layout.fragment_daysum_workplan;
    }

    @Override
    protected void init() {

        mDataList = new ArrayList<>();
        mCommentDatas = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            CommentData commentData = new CommentData();
            HtDaysumDetailsData htDaysumDetailsData = new HtDaysumDetailsData();
            htDaysumDetailsData.setWorkSort("A类");
            commentData.setCommentPerson("总监");
            commentData.setCommentTime("2017-6-12");
            commentData.setCommentJob("弄着、弄那");
            commentData.setCommentContent("除了这你还弄啥类");
            mCommentDatas.add(commentData);
            mDataList.add(htDaysumDetailsData);
        }

        mDetailsAdapter = new HtDaysumDetailsAdapter(getActivity(),mDataList);
        mLvWorkPlan.setAdapter(mDetailsAdapter);
        mDetailsAdapter.notifyDataSetChanged();

        mCommentAdapter = new CommentAdapter(getActivity(), mCommentDatas,"");
        mIvComment.setAdapter(mCommentAdapter);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_comment_head, null);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        viewHolder.mTvCommentCount.setText("评论("+mCommentDatas.size()+")");
        mIvComment.addHeaderView(view);


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
