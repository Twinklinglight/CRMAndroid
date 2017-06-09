package com.wtcrmandroid.adapter.listview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.model.HtDayplanDetailsData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxd on 2017/6/9
 */

public class HtDayplanDetailsAdapter extends MySmallBaseAdapter<HtDayplanDetailsData, HtDayplanDetailsAdapter.ViewHolder> {

    public HtDayplanDetailsAdapter(Activity activity, List<HtDayplanDetailsData> list) {
        super(activity, list);
    }

    @Override
    protected void convert(ViewHolder holder, int position) {

        HtDayplanDetailsData htDayplanDetailsData = list.get(position);
        holder.mTvWorkSort.setText(htDayplanDetailsData.getWorkSort());
        holder.mTvDayplanPerson.setText(htDayplanDetailsData.getWorkPerson());
        holder.mTvDayplanPercent.setText(htDayplanDetailsData.getWorkPercent());
        holder.mTvDayplanContent.setText(htDayplanDetailsData.getWorkContent());

    }

    @Override
    protected View onCreateViewHolder() {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_dayplan_details, null);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tv_work_sort)
        TextView mTvWorkSort;
        @BindView(R.id.tv_dayplan_person)
        TextView mTvDayplanPerson;
        @BindView(R.id.tv_dayplan_percent)
        TextView mTvDayplanPercent;
        @BindView(R.id.tv_dayplan_content)
        TextView mTvDayplanContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
