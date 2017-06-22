package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wtcrmandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/6/20.
 */

public class PoppupWindowTitleAdapter extends BaseRecycleAdapter<String, PoppupWindowTitleAdapter.ViewHolder> {

    private int style;
    private int isCheckposition;
    public oNclicklistner onclicklistner;

    /**
     * @param context           //上下文
     * @param style//文本居中为1居左为0
     */
    public PoppupWindowTitleAdapter(Context context, int style, int isCheckposition, oNclicklistner onclicklistner) {

        super(context, R.layout.item_poppupwindow_title);
        this.style = style;
        this.isCheckposition = isCheckposition;
        this.onclicklistner = onclicklistner;
    }

    @Override
    protected void convert(ViewHolder holder, final String bean, final int position) {
        if (style == 0)
            holder.tvLeft.setText(bean);
        else
            holder.tvCenter.setText(bean);
        if (isCheckposition == position) {
            holder.ivRight.setVisibility(View.VISIBLE);
            holder.tvLeft.setTextColor(Color.parseColor("#3b9cff"));
            holder.tvCenter.setTextColor(Color.parseColor("#3b9cff"));
        } else {
            holder.ivRight.setVisibility(View.INVISIBLE);
            holder.tvLeft.setTextColor(Color.parseColor("#2b2f33"));
            holder.tvCenter.setTextColor(Color.parseColor("#2b2f33"));
        }
        holder.rlItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheckposition = position;
                notifyDataSetChanged();
                onclicklistner.oNclicklistner(bean, position);
            }
        });

    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rl_item)
        RelativeLayout rlItem;
        @BindView(R.id.tv_left)
        TextView tvLeft;
        @BindView(R.id.tv_center)
        TextView tvCenter;
        @BindView(R.id.iv_right)
        ImageView ivRight;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface oNclicklistner {
        void oNclicklistner(String data, int position);
    }
}
