package com.wtcrmandroid.adapter.listview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.model.MajorCustomerData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxd on 2017/6/12
 */

public class MajorCustomerAdapter extends MySmallBaseAdapter<MajorCustomerData, MajorCustomerAdapter.ViewHolder> {

    public MajorCustomerAdapter(Activity activity, List<MajorCustomerData> list) {
        super(activity, list);
    }

    @Override
    protected void convert(ViewHolder holder, int position) {

        holder.mTvWorkSort.setText(list.get(position).getWorkSort());
        holder.mTvCustomrname.setText(list.get(position).getCustomerName());
        holder.mTvAnalysis.setText(list.get(position).getWorkAnalysis());


    }

    @Override
    protected View onCreateViewHolder() {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_major_fragment, null);
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
        @BindView(R.id.tv_customrname)
        TextView mTvCustomrname;
        @BindView(R.id.tv_analysis)
        TextView mTvAnalysis;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
