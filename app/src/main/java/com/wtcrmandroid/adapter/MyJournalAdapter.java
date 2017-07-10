package com.wtcrmandroid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.model.MyjournalRponseData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxd on 2017/6/8
 */

public class MyJournalAdapter extends BaseAdapter {

    private List<MyjournalRponseData> mDatas;
    private ItemClickListener itemClickListener;

    public MyJournalAdapter(ItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public List<MyjournalRponseData> getmDatas() {
        return mDatas;
    }

    public void setmDatas(List<MyjournalRponseData> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = null;
        if (view == null || !(view.getTag() instanceof ViewHolder)){

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_journal, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        final MyjournalRponseData myjournalRponseData = mDatas.get(position);

        viewHolder.mTvJournalContent.setText(myjournalRponseData.getContent());

        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.llitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (myjournalRponseData.getType()){
                    case "dayPlan":
                        finalViewHolder.mTvJournalTitle.setText(getDayTitle(myjournalRponseData.getShortRecordDate())+"日计划");

                        finalViewHolder.mTvJournalType.setImageResource(R.mipmap.ic_type_dayplan);
                        itemClickListener.DayPlanClick(position);
                        break;
                    case "dayWork":
                        finalViewHolder.mTvJournalTitle.setText(getDayTitle(myjournalRponseData.getShortRecordDate())+"日总结");
                        finalViewHolder.mTvJournalType.setImageResource(R.mipmap.ic_type_daysum);
                        itemClickListener.DaySumClick(position);
                        break;
                    case "weekPlan":
                        finalViewHolder.mTvJournalTitle.setText(getWeekTitle(myjournalRponseData.getWeekBegin(),myjournalRponseData.getWeekEnd()) +"周计划");
                        finalViewHolder.mTvJournalType.setImageResource(R.mipmap.ic_type_weekplan);
                        itemClickListener.WeekPlanClick(position);
                        break;
                    case "weekWork":
                        finalViewHolder.mTvJournalTitle.setText(getWeekTitle(myjournalRponseData.getWeekBegin(),myjournalRponseData.getWeekEnd()) +"周总结");
                        finalViewHolder.mTvJournalType.setImageResource(R.mipmap.ic_type_weeksum);
                        itemClickListener.WeekSumClick(position);
                        break;

                }

            }
        });

        return view;
    }

    private String getDayTitle(String time){
        String title = "";
        String[] split = time.split("-");
        title = split[0]+"年"+split[1]+"月"+split[2]+"日";
        return title;
    }

    private String getWeekTitle(String weekbegin,String weekEnd){
        String weekTitle = "";
        String[] splitbegin = weekbegin.split("-");
        String[] splitend = weekEnd.split("-");

        weekTitle = splitbegin[0]+"年"+splitbegin[1]+"月"+splitbegin[2]+"日"+"至"+splitend[0]+"年"+splitend[1]+"月"+splitend[2]+"日";

        return weekTitle;
    }

    public interface ItemClickListener{
        void DayPlanClick(int position);
        void DaySumClick(int position);
        void WeekPlanClick(int position);
        void WeekSumClick(int position);
    }


    static class ViewHolder {
        @BindView(R.id.tv_journal_title)
        TextView mTvJournalTitle;
        @BindView(R.id.tv_journal_type)
        ImageView mTvJournalType;
        @BindView(R.id.tv_journal_content)
        TextView mTvJournalContent;
        @BindView(R.id.ll_item)
        LinearLayout llitem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
