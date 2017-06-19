package com.wtcrmandroid.adapter.listview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.model.WriterWeekPlaneData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxd on 2017/6/12
 */

public class WeekDayplanAdapter extends BaseAdapter<WriterWeekPlaneData, WeekDayplanAdapter.ViewHolder> {

    public WeekDayplanAdapter(Activity activity, List<WriterWeekPlaneData> list) {
        super(activity, list);
    }

    @Override
    protected void convert(ViewHolder holder, int position) {

        holder.mTvWeekplanTitle.setText(list.get(position).getWorkNumber());
        holder.mTvWeekplanContent.setText(list.get(position).getWorkContent());
        holder.mTvWeekplanMb.setText(list.get(position).getWorkPlanning());
        holder.mTvWeekplanPercent.setText(list.get(position).getWorkPercentage());
    }

    @Override
    protected View onCreateViewHolder(int position) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_weekplan_details, null);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(position,viewHolder);
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tv_weekplan_title)
        TextView mTvWeekplanTitle;
        @BindView(R.id.tv_weekplan_content)
        TextView mTvWeekplanContent;
        @BindView(R.id.tv_weekplan_mb)
        TextView mTvWeekplanMb;
        @BindView(R.id.tv_weekplan_percent)
        TextView mTvWeekplanPercent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
