package com.wtcrmandroid.adapter.listview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.model.GetSingleCustomerData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxd on 2017/6/22
 */

public class IfgetSingleDetailsAdapter extends MySmallBaseAdapter<GetSingleCustomerData, IfgetSingleDetailsAdapter.ViewHolder> {

    public IfgetSingleDetailsAdapter(Activity activity, List<GetSingleCustomerData> list) {
        super(activity, list);
    }

    @Override
    protected void convert(ViewHolder holder, int position) {

        GetSingleCustomerData getSingleCustomerData = list.get(position);
        holder.mTvWorkSort.setText(getSingleCustomerData.getWorkSort());
        holder.mTvCustomerName.setText(getSingleCustomerData.getWorkName());
        holder.mTvIfGet.setText(getSingleCustomerData.getIfGet());
        holder.mTvReasonAnalysis.setText(getSingleCustomerData.getReasonAnalysis());
        holder.mTvGenjinPlan.setText(getSingleCustomerData.getGenjinplan());
    }

    @Override
    protected View onCreateViewHolder() {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_getsingle_details, null);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    protected View onCreateNullViewholder() {
        return null;
    }

    static class ViewHolder {
        @BindView(R.id.tv_work_sort)
        TextView mTvWorkSort;
        @BindView(R.id.tv_customer_name)
        TextView mTvCustomerName;
        @BindView(R.id.tv_if_get)
        TextView mTvIfGet;
        @BindView(R.id.tv_reason_analysis)
        TextView mTvReasonAnalysis;
        @BindView(R.id.tv_genjin_plan)
        TextView mTvGenjinPlan;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
