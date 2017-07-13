package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.thinkcool.circletextimageview.CircleTextImageView;
import com.wtcrmandroid.R;
import com.wtcrmandroid.model.reponsedata.GetSelcetpersonRP;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxd on 2017/7/13.
 */

public class DocSelectpersonAdapter extends BaseRecycleAdapter<GetSelcetpersonRP, DocSelectpersonAdapter.ViewHolder> {


    /**
     * @param context  //上下文
     * @param layoutId //布局id
     */
    public DocSelectpersonAdapter(Context context, int layoutId) {
        super(context, R.layout.item_doc_selectperson);
    }

    @Override
    protected void convert(ViewHolder holder, GetSelcetpersonRP bean, int position) {
        holder.ivHead.setText(bean.getName().substring(bean.getName().length()-2,bean.getName().length()));
        holder.tvJob.setText(bean.getJob());
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_head)
        CircleTextImageView ivHead;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_job)
        TextView tvJob;
        @BindView(R.id.iv_select)
        RadioButton ivSelect;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
