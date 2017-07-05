package com.wtcrmandroid.fragment.journalmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.listview.CommentAdapter;
import com.wtcrmandroid.adapter.listview.WeekDayplanAdapter;
import com.wtcrmandroid.adapter.listview.WeeksumDetailsAdapter;
import com.wtcrmandroid.view.dialog.CommentDialog;
import com.wtcrmandroid.BaseFragment;
import com.wtcrmandroid.model.reponsedata.CommentData;
import com.wtcrmandroid.model.WeeksumDetailsData;
import com.wtcrmandroid.model.WriterWeekPlaneData;
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

public class DepartWeekjournalFragment extends BaseFragment {

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

    @Override
    public void returnData(int key, Object data) {

    }

    @Override
    protected int Rlayout() {
        return R.layout.fragment_department_weekjournal;
    }

    @Override
    protected void init() {
        mWeekplanDataList = new ArrayList<>();
        mWeeksumDataList = new ArrayList<>();
        mCommentDataList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            WriterWeekPlaneData writerWeekPlaneData = new WriterWeekPlaneData();
            WeeksumDetailsData weeksumDetailsData = new WeeksumDetailsData();
            CommentData commentData = new CommentData();

            writerWeekPlaneData.setWorkNumber("本周计划" + (i + 1));
            weeksumDetailsData.setWeekTitle("本周总结" + (i + 1));
            commentData.setCommentJob("老大");

            mWeekplanDataList.add(writerWeekPlaneData);
            mWeeksumDataList.add(weeksumDetailsData);
            mCommentDataList.add(commentData);
        }

        mWeekplanAdapter = new WeekDayplanAdapter(getActivity(), mWeekplanDataList);
        mWeeksumAdapter = new WeeksumDetailsAdapter(getActivity(), mWeeksumDataList);
        mCommentAdapter = new CommentAdapter(getActivity(), mCommentDataList,"");

        mLvWeekplanDetails.setAdapter(mWeekplanAdapter);
        mLvWeeksumDetails.setAdapter(mWeeksumAdapter);
        mLvComment.setAdapter(mCommentAdapter);
        View commenthead = LayoutInflater.from(getActivity()).inflate(R.layout.item_comment_head, null);

        ViewHolder viewHolder = new ViewHolder(commenthead);
        commenthead.setTag(viewHolder);
        viewHolder.mTvCommentCount.setText("评价(" + mCommentDataList.size() + ")");
        mLvComment.addHeaderView(commenthead);

    }

    @OnClick({R.id.ll_week_date, R.id.tv_write_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_week_date:         //获取日期
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
