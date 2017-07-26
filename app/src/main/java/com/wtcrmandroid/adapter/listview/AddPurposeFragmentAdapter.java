package com.wtcrmandroid.adapter.listview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.model.reponsedata.AddPurpostCtAtData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 日志详情 新增意向客户的adapter
 * Created by zxd on 2017/6/22
 */

public class AddPurposeFragmentAdapter extends MySmallBaseAdapter<AddPurpostCtAtData, AddPurposeFragmentAdapter.ViewHolder> {

    public AddPurposeFragmentAdapter(Activity activity, List<AddPurpostCtAtData> list) {
        super(activity, list);
    }

    @Override
    protected void convert(ViewHolder holder, int position) {

        AddPurpostCtAtData addPurpostCtAtData = list.get(position);
        holder.mTvWorkSort.setText(addPurpostCtAtData.getWorkSort());
        holder.mTvCustomerName.setText(addPurpostCtAtData.getCustomerName());
        holder.mTvFxgj.setText(addPurpostCtAtData.getAnalysisGjin());
    }

    @Override
    protected View onCreateViewHolder() {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_pourpose_details, null);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    protected View onCreateNullViewholder() {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_list_null, null);
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tv_work_sort)
        TextView mTvWorkSort;
        @BindView(R.id.tv_customer_name)
        TextView mTvCustomerName;
        @BindView(R.id.tv_fxgj)
        TextView mTvFxgj;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
