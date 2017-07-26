package com.wtcrmandroid.adapter.listview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.model.reponsedata.SingleCustomerData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxd on 2017/6/21
 */

public class SingleFragmentAdapter extends MySmallBaseAdapter<SingleCustomerData, SingleFragmentAdapter.ViewHolder> {

    public SingleFragmentAdapter(Activity activity, List<SingleCustomerData> list) {
        super(activity, list);
    }

    @Override
    protected void convert(ViewHolder holder, int position) {

        SingleCustomerData singleCustomerData = list.get(position);

        holder.mTvWorkSort.setText(singleCustomerData.getWorkSort());
        holder.mTvCustomerName.setText(singleCustomerData.getWorkName());
        holder.mTvDealPercent.setText(singleCustomerData.getWorkPercent());
        holder.mTvAnalysis.setText(singleCustomerData.getWorkAnalysis());

    }

    @Override
    protected View onCreateViewHolder() {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_single_details, null);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    protected View onCreateNullViewholder() {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_list_null,null);
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tv_work_sort)
        TextView mTvWorkSort;
        @BindView(R.id.tv_customer_name)
        TextView mTvCustomerName;
        @BindView(R.id.tv_deal_percent)
        TextView mTvDealPercent;
        @BindView(R.id.tv_analysis)
        TextView mTvAnalysis;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
