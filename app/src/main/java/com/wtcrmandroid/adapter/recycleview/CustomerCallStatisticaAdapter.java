package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.thinkcool.circletextimageview.CircleTextImageView;
import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.field.CustomerVisitDetailsListActivity;
import com.wtcrmandroid.model.reponsedata.VisitStatisticalRP;
import com.wtcrmandroid.utils.TextUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/6/19.
 * 客户拜访统计
 */

public class CustomerCallStatisticaAdapter extends BaseRecycleAdapter<VisitStatisticalRP,CustomerCallStatisticaAdapter.ViewHolder> {
    /**
     * @param context  //上下文
     */
    public CustomerCallStatisticaAdapter(Context context) {
        super(context, R.layout.item_field);
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }


    @Override
    protected void convert(ViewHolder holder,VisitStatisticalRP bean,int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,CustomerVisitDetailsListActivity.class));
            }
        });
        holder.tvNumber.setText(bean.getVisitCount());
        holder.tvName.setText(bean.getUserName());
        holder.tvDescribe.setText(bean.getRoleName()+"   客户拜访数量：");
        holder.ctivPhoto.setText(TextUtils.NameText(bean.getUserName()));


    }


    static class ViewHolder extends RecyclerView.ViewHolder{
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
