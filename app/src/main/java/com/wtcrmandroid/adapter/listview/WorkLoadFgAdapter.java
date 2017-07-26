package com.wtcrmandroid.adapter.listview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.model.reponsedata.GetMoneyData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxd on 2017/7/19.
 */

public class WorkLoadFgAdapter extends MySmallBaseAdapter<GetMoneyData, WorkLoadFgAdapter.ViewHolder> {

    public WorkLoadFgAdapter(Activity activity, List<GetMoneyData> list) {
        super(activity, list);
    }

    @Override
    protected void convert(ViewHolder holder, int position) {

        GetMoneyData getMoneyData = list.get(position);
        holder.tvDaysumHkdd.setText(getMoneyData.getCustomerName());
        holder.tvCustomerType.setText(getMoneyData.getCustomerType());
        holder.tvProductType.setText(getMoneyData.getProductType());
        holder.tvGetmoney.setText(getMoneyData.getBackMoney());

    }

    @Override
    protected View onCreateViewHolder() {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_workload, null);
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
        @BindView(R.id.tv_daysum_hkdd)
        TextView tvDaysumHkdd;
        @BindView(R.id.tv_customer_type)
        TextView tvCustomerType;
        @BindView(R.id.tv_product_type)
        TextView tvProductType;
        @BindView(R.id.tv_getmoney)
        TextView tvGetmoney;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
