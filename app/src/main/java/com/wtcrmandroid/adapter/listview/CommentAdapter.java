package com.wtcrmandroid.adapter.listview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.model.reponsedata.CommentData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxd on 2017/6/9
 */

public class CommentAdapter extends CommentBaseAdapter<CommentData, CommentAdapter.ViewHolder> {

    private String level;

    public CommentAdapter(Activity activity, List<CommentData> list) {
        super(activity, list);
    }

    @Override
    protected void convert(ViewHolder holder, int position) {

        holder.mTvCommentContent.setText(list.get(position).getCommentContent());
        holder.mTvCommentJob.setText("("+list.get(position).getCommentJob()+")");
        holder.mTvCommentPerson.setText(list.get(position).getCommentPerson());
        level = list.get(position).getLeve();
        holder.mTvCommentTime.setText("日志等级 "+level+" "+"发表于 "+list.get(position).getCommentTime());

    }

    @Override
    protected View onCreateViewHolder() {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_comment, null);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }


    class ViewHolder {
        @BindView(R.id.tv_comment_person)
        TextView mTvCommentPerson;
        @BindView(R.id.tv_comment_job)
        TextView mTvCommentJob;
        @BindView(R.id.tv_comment_time)
        TextView mTvCommentTime;
        @BindView(R.id.tv_comment_content)
        TextView mTvCommentContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
