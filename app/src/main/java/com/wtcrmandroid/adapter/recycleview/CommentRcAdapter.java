package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.model.reponsedata.CommentData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxd on 2017/7/19.
 */

public class CommentRcAdapter extends BaseRecycleAdapter<CommentData, CommentRcAdapter.ViewHolder> {

   private String level;

    /**
     * @param context //上下文
     */
    public CommentRcAdapter(Context context, List<CommentData> list,String level) {
        super(context, R.layout.item_comment);
        addList(list);
        this.level = level;
    }



    @Override
    protected void convert(ViewHolder holder, CommentData bean, int position) {
        holder.tvCommentContent.setText(list.get(position).getCommentContent());
        holder.tvCommentJob.setText("("+list.get(position).getCommentJob()+")");
        holder.tvCommentPerson.setText(list.get(position).getCommentPerson());
        holder.tvCommentTime.setText("日志等级 "+level+" "+"发表于 "+list.get(position).getCommentTime());
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_comment_person)
        TextView tvCommentPerson;
        @BindView(R.id.tv_comment_job)
        TextView tvCommentJob;
        @BindView(R.id.tv_comment_time)
        TextView tvCommentTime;
        @BindView(R.id.tv_comment_content)
        TextView tvCommentContent;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
