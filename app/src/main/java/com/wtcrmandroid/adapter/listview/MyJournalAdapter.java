package com.wtcrmandroid.adapter.listview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.model.reponsedata.MyjournalRponseData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxd on 2017/6/8
 */

public class MyJournalAdapter extends BaseAdapter {

    private List<MyjournalRponseData> mDatas;
    private ItemClickListener itemClickListener;

    public static final int NomalType = 1;
    public static final int NullType = 2;

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

        if (mDatas == null){
            return 1;
        }else if (mDatas.size()>0){
            return mDatas.size();
        }else {
            return 1;
        }
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

        View view = null;
        switch (getItemViewType(position)){
            case NomalType:
                view = convertView;
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
                setJournalType(finalViewHolder,myjournalRponseData);    //设置日志类型图标，title

                viewHolder.llitem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        switch (myjournalRponseData.getType()){
                            case "dayPlan":
                                itemClickListener.DayPlanClick(position);
                                break;
                            case "dayWork":
                                itemClickListener.DaySumClick(position);
                                break;
                            case "weekPlan":
                                itemClickListener.WeekPlanClick(position);
                                break;
                            case "weekWork":
                                itemClickListener.WeekSumClick(position);
                                break;
                        }
                    }
                });

                break;
            case NullType:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_null,null);
                TextView tvNull = (TextView)view.findViewById(R.id.tv_listnull);
                tvNull.setText("本周暂无数据");
                break;
        }

        return view;
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas == null){
            return NullType;
        }else if (mDatas.size()>0){
            return NomalType;
        }else {
            return NullType;
        }
    }

    private void setJournalType(ViewHolder finalViewHolder, MyjournalRponseData myjournalRponseData){
        switch (myjournalRponseData.getType()){
            case "dayPlan":
                finalViewHolder.mTvJournalTitle.setText(getDayTitle(myjournalRponseData.getShortRecordDate())+"日计划");
                finalViewHolder.mTvJournalType.setImageResource(R.mipmap.ic_type_dayplan);
                break;
            case "dayWork":
                finalViewHolder.mTvJournalTitle.setText(getDayTitle(myjournalRponseData.getShortRecordDate())+"日总结");
                finalViewHolder.mTvJournalType.setImageResource(R.mipmap.ic_type_daysum);
                break;
            case "weekPlan":
                finalViewHolder.mTvJournalTitle.setText(getWeekTitle(myjournalRponseData.getWeekBegin(),myjournalRponseData.getWeekEnd()) +"周计划");
                finalViewHolder.mTvJournalType.setImageResource(R.mipmap.ic_type_weekplan);
                break;
            case "weekWork":
                finalViewHolder.mTvJournalTitle.setText(getWeekTitle(myjournalRponseData.getWeekBegin(),myjournalRponseData.getWeekEnd()) +"周总结");
                finalViewHolder.mTvJournalType.setImageResource(R.mipmap.ic_type_weeksum);
                break;

        }
    }

    private String getDayTitle(String time){
        String title = "";
        Log.i("======",time);
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
