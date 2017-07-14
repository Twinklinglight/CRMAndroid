package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.model.reponsedata.SalesRankingRP;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/6/19.
 */

public class PersonerRankingAdapter extends BaseRecycleAdapter<SalesRankingRP, PersonerRankingAdapter.ViewHolder> {


    /**
     * @param context //上下文
     */
    public PersonerRankingAdapter(Context context) {
        super(context, R.layout.item_team_ranking);
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }


    @Override
    protected void convert(ViewHolder holder, SalesRankingRP bean,int position) {
        holder.tvTeamName.setText(bean.getUsername());
        holder.tvCaptain.setText("所属团队：" + bean.getTeamname());
        holder.tvPeopleNumber.setText("");
        holder.tvOrders.setText(bean.getRidan());
        holder.tvMoney.setText(bean.getRijine());
        holder.tvTotalOrders.setText(bean.getZongdan());
        holder.tvOrderMoney.setText(bean.getOldjine());
        holder.tvContinueMoney.setText(bean.getNewjine());
        holder.tvTotalMoney.setText(bean.getJine());
        switch (position){
            case 0:
                holder.tvRanking.setBackgroundResource(R.mipmap.ic_gold_cup);
                holder.tvRanking.setText("");
                break;
            case 1:
                holder.tvRanking.setBackgroundResource(R.mipmap.ic_silver_trophy);
                holder.tvRanking.setText("");
                break;
            case 2:
                holder.tvRanking.setBackgroundResource(R.mipmap.ic_bronze_trophy);
                holder.tvRanking.setText("");
                break;
            default:
                holder.tvRanking.setText("\n第"+(position+1)+"名");
                holder.tvRanking.setBackgroundResource(0);
                break;
        }

    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_team_name)
        TextView tvTeamName;
        @BindView(R.id.tv_captain)
        TextView tvCaptain;
        @BindView(R.id.tv_people_number)
        TextView tvPeopleNumber;
        @BindView(R.id.tv_orders)
        TextView tvOrders;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_total_orders)
        TextView tvTotalOrders;
        @BindView(R.id.tv_order_money)
        TextView tvOrderMoney;
        @BindView(R.id.tv_continue_money)
        TextView tvContinueMoney;
        @BindView(R.id.tv_total_money)
        TextView tvTotalMoney;
        @BindView(R.id.tv_ranking)
        TextView tvRanking;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}
