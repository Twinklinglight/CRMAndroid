package com.wtcrmandroid.adapter.listview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.model.WeeksumDetailsData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxd on 2017/6/12
 */

public class WeeksumDetailsAdapter extends MySmallBaseAdapter<WeeksumDetailsData, WeeksumDetailsAdapter.ViewHolder> {

    public WeeksumDetailsAdapter(Activity activity, List<WeeksumDetailsData> list) {
        super(activity, list);
    }

    @Override
    protected void convert(ViewHolder holder, int position) {
        holder.mTvWeekpsumTitle.setText(list.get(position).getWeekTitle());
        holder.mTvWeeksumContent.setText(list.get(position).getWeekContent());
        holder.mTvWeekTarget.setText(list.get(position).getWeekTarget());
        holder.mTvWeeksumComplete.setText(list.get(position).getWeekComplete());
        holder.mTvWeeksumPercent.setText(list.get(position).getWeekPercent());
        holder.mTvWeeksumStudy.setText(list.get(position).getWeekStudy());
    }

    @Override
    protected View onCreateViewHolder() {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_weeksum_details, null);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tv_weekpsum_title)
        TextView mTvWeekpsumTitle;
        @BindView(R.id.tv_weeksum_content)
        TextView mTvWeeksumContent;
        @BindView(R.id.tv_week_target)
        TextView mTvWeekTarget;
        @BindView(R.id.tv_weeksum_percent)
        TextView mTvWeeksumPercent;
        @BindView(R.id.tv_weeksum_complete)
        TextView mTvWeeksumComplete;
        @BindView(R.id.tv_weeksum_study)
        TextView mTvWeeksumStudy;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}