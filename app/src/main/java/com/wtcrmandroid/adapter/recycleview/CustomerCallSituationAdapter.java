package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.thinkcool.circletextimageview.CircleTextImageView;
import com.wtcrmandroid.R;
import com.wtcrmandroid.model.reponsedata.CompanyVisitDetailsRP;
import com.wtcrmandroid.utils.TextUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/6/19.
 * 客户拜访情况
 */

public class CustomerCallSituationAdapter extends BaseRecycleAdapter<CompanyVisitDetailsRP, CustomerCallSituationAdapter.ViewHolder> {




    /**
     * @param context //上下文
     */
    public CustomerCallSituationAdapter(Context context) {
        super(context, R.layout.item_customer_call_situation);
    }

    @Override
    protected void convert(ViewHolder holder, CompanyVisitDetailsRP bean, int position) {
        if (position == 0) {
            holder.vTop.setVisibility(View.INVISIBLE);

        } else {
            holder.vTop.setVisibility(View.VISIBLE);
        }
        if (position == list.size() - 1)
            holder.vBottom.setVisibility(View.INVISIBLE);
        else
            holder.vBottom.setVisibility(View.VISIBLE);
        holder.tvTime.setText(bean.getCreateTime());
        holder.tvName.setText(bean.getCustomerName());
        holder.tvWant.setText("客户意向："+bean.getRemarks());
        holder.tvAddress.setText(bean.getAddressDetail());
        String[] dateArray = bean.getCreateTime().split(" ");
        String[] timeArray = dateArray[0].split("/");
        holder.tvMonth.setText(timeArray[1] + "月");
        holder.tvDay.setText(timeArray[2] + "日");
        holder.ctivPhoto.setText(TextUtils.NameText(bean.getUserName()));
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.v_top)
        View vTop;
        @BindView(R.id.v_bottom)
        View vBottom;
        @BindView(R.id.tv_month)
        TextView tvMonth;
        @BindView(R.id.tv_day)
        TextView tvDay;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_want)
        TextView tvWant;
        @BindView(R.id.tv_address)
        TextView tvAddress;
        @BindView(R.id.ctiv_photo)
        CircleTextImageView ctivPhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}
