package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.thinkcool.circletextimageview.CircleTextImageView;
import com.wtcrmandroid.R;
import com.wtcrmandroid.model.reponsedata.DocApplyDetailsRP;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxd on 2017/7/17.
 */

public class DocApplyRecordAdapter extends BaseRecycleAdapter<DocApplyDetailsRP, DocApplyRecordAdapter.ViewHolder> {


    /**
     * @param context //上下文
     */
    public DocApplyRecordAdapter(Context context, List<DocApplyDetailsRP> list) {
        super(context, R.layout.item_docment_details);
        addList(list);
    }

    @Override
    protected void convert(DocApplyRecordAdapter.ViewHolder holder, DocApplyDetailsRP bean, int position) {

        holder.ctvDoc.setText(bean.getName().substring(bean.getName().length()-2,bean.getName().length()));
        holder.tvName.setText(bean.getName());
        holder.tvState.setText(bean.getState());
        holder.tvTime.setText(bean.getDate());
        holder.tvReason.setText(bean.getReason());
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ctv_doc)
        CircleTextImageView ctvDoc;
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.tv_reason)
        TextView tvReason;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
