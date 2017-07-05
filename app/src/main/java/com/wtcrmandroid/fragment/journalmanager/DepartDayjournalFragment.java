package com.wtcrmandroid.fragment.journalmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.listview.CommentAdapter;
import com.wtcrmandroid.adapter.listview.HtDayplanDetailsAdapter;
import com.wtcrmandroid.adapter.listview.HtDaysumDetailsAdapter;
import com.wtcrmandroid.view.dialog.CommentDialog;
import com.wtcrmandroid.BaseFragment;
import com.wtcrmandroid.model.reponsedata.CommentData;
import com.wtcrmandroid.model.HtDayplanDetailsData;
import com.wtcrmandroid.model.reponsedata.HtDaysumDetailsData;
import com.wtcrmandroid.view.listview.MyListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 部门员工一天日志详情
 * Created by zxd on 2017/6/23
 */

public class DepartDayjournalFragment extends BaseFragment {

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

    private List<HtDayplanDetailsData> mDayplansDataList;
    private HtDayplanDetailsAdapter mDayplanAdapter;

    private List<HtDaysumDetailsData> mDaysumDataList;
    private HtDaysumDetailsAdapter mDysumAdapter;

    private List<CommentData> mCommentDataList;
    private CommentAdapter mCommentAdapter;


    @Override
    public void returnData(int key, Object data) {

    }

    @Override
    protected int Rlayout() {
        return R.layout.fragment_department_dayjournal;
    }

    @Override
    protected void init() {
        mDayplansDataList = new ArrayList<>();
        mDaysumDataList = new ArrayList<>();
        mCommentDataList = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            HtDayplanDetailsData htDayplanDetailsData = new HtDayplanDetailsData();
            HtDaysumDetailsData htDaysumDetailsData = new HtDaysumDetailsData();
            CommentData commentData = new CommentData();

            commentData.setCommentJob("主席");
            htDaysumDetailsData.setWorkSort("B类");
            htDayplanDetailsData.setWorkSort("A类");

            mDayplansDataList.add(htDayplanDetailsData);
            mDaysumDataList.add(htDaysumDetailsData);
            mCommentDataList.add(commentData);
        }
        mDayplanAdapter = new HtDayplanDetailsAdapter(getActivity(), mDayplansDataList);
        mLvDayplanDetails.setAdapter(mDayplanAdapter);

        mDysumAdapter = new HtDaysumDetailsAdapter(getActivity(), mDaysumDataList);
        mLvDaysumDetails.setAdapter(mDysumAdapter);

        View commentHead = LayoutInflater.from(getActivity()).inflate(R.layout.item_comment_head, null);
        ViewHolder viewHolder = new ViewHolder(commentHead);
        commentHead.setTag(viewHolder);
        viewHolder.mTvCommentCount.setText("评论(" + mCommentDataList.size() + ")");
        mLvComment.addHeaderView(commentHead);
        mCommentAdapter = new CommentAdapter(getActivity(), mCommentDataList,"");
        mLvComment.setAdapter(mCommentAdapter);

    }


    @OnClick({R.id.iv_calender,R.id.tv_write_comment})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.iv_calender:           //选择日期
                break;
            case R.id.tv_write_comment:     //写评论
                new CommentDialog(getContext()).show();
                break;
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
