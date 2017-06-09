package com.wtcrmandroid.adapter.listview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.model.HtDaysumDetailsData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxd on 2017/6/9
 */

public class HtDaysumDetailsAdapter extends MySmallBaseAdapter<HtDaysumDetailsData, HtDaysumDetailsAdapter.ViewHolder> {

    public HtDaysumDetailsAdapter(Activity activity, List<HtDaysumDetailsData> list) {
        super(activity, list);
    }

    @Override
    protected void convert(ViewHolder holder, int position) {

        holder.mTvWorkSort.setText(list.get(position).getWorkSort());
        holder.mTvDaysumContent.setText(list.get(position).getWorkContent());
        holder.mTvDaysumPercent.setText(list.get(position).getWorkPercent());
        holder.mTvDaysumPerson.setText(list.get(position).getWorkPerson());
        holder.mTvDaysumWcqk.setText(list.get(position).getWorkComplete());
        holder.mTvDaysumXxfx.setText(list.get(position).getWorkStudy());

    }

    @Override
    protected View onCreateViewHolder() {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_daysum_work_details
                ,null);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

     class ViewHolder {
        @BindView(R.id.tv_work_sort)
        TextView mTvWorkSort;
        @BindView(R.id.tv_daysum_person)
        TextView mTvDaysumPerson;
        @BindView(R.id.tv_daysum_percent)
        TextView mTvDaysumPercent;
        @BindView(R.id.tv_daysum_content)
        TextView mTvDaysumContent;
        @BindView(R.id.tv_daysum_wcqk)
        TextView mTvDaysumWcqk;
        @BindView(R.id.tv_daysum_xxfx)
        TextView mTvDaysumXxfx;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
