package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.thinkcool.circletextimageview.CircleTextImageView;
import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.field.StatisticsDetailsActivity;
import com.wtcrmandroid.model.reponsedata.FieldStatisticsRP;
import com.wtcrmandroid.utils.TextUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/6/19.
 * 外勤统计
 */

public class FieldStatisticaAdapter extends BaseRecycleAdapter<FieldStatisticsRP, FieldStatisticaAdapter.ViewHolder> {

    private String date;

    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @param context //上下文
     */
    public FieldStatisticaAdapter(Context context) {
        super(context, R.layout.item_field);
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }


    @Override
    protected void convert(ViewHolder holder, final FieldStatisticsRP bean, int position) {
        holder.tvNumber.setText(bean.getSignCount());
        holder.tvName.setText(bean.getUserName());
        holder.tvDescribe.setText(bean.getDeptName()+"   打卡次数：");
        holder.ctivPhoto.setText(TextUtils.NameText(bean.getUserName()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, StatisticsDetailsActivity.class).putExtra("userId", bean.getUserId()).putExtra("date",date));
            }
        });

    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ctiv_photo)
        CircleTextImageView ctivPhoto;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_describe)
        TextView tvDescribe;

        @BindView(R.id.tv_number)
        TextView tvNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}
