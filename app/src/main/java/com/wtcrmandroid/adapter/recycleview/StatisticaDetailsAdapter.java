package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.model.reponsedata.ClockRecordRP;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/6/20.
 * 外勤打卡记录统计详情适配器
 */

public class StatisticaDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int NULLDATA = 0;
    private final int HEAD = 1;
    private final int WORD = 2;


    private String date;
    private Context context;
    private List<ClockRecordRP> list = new ArrayList<>();

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int getItemViewType(int position) {
        if (list.size() == 0)
            return NULLDATA;
        else if (position == 0)
            return HEAD;
        else
            return WORD;
    }

    public StatisticaDetailsAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<ClockRecordRP> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<ClockRecordRP> getList() {
        return list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        RecyclerView.ViewHolder holder;
        if (viewType == NULLDATA) {
            v = LayoutInflater.from(context).inflate(R.layout.view_null_data, parent, false);
            holder = new NullDataViewHolder(v);
        } else if (viewType == HEAD) {
            v = LayoutInflater.from(context).inflate(R.layout.item_header_time_text, parent, false);
            holder = new HeadViewHolder(v);
        } else {
            v = LayoutInflater.from(context).inflate(R.layout.item_timeline, parent, false);
            holder = new ViewHolder(v);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == HEAD) {
            HeadViewHolder headViewHolder = (HeadViewHolder) holder;
            headViewHolder.tvTime.setText(date);
        } else if (getItemViewType(position) == WORD) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.tvTime.setText(list.get(position-1).getCreateTime().split(" ")[1]);
            viewHolder.tvAddress.setText(list.get(position-1).getAddress());
            if (position == 1) {
                viewHolder.vTop.setVisibility(View.INVISIBLE);
            } else {
                viewHolder.vTop.setVisibility(View.VISIBLE);
            }
            if (position == list.size()) {
                viewHolder.vBottom.setVisibility(View.INVISIBLE);
            } else {
                viewHolder.vBottom.setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size() + 1;
        } else
            return 1;
    }


    static class NullDataViewHolder extends RecyclerView.ViewHolder {
        public NullDataViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class HeadViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_time)
        TextView tvTime;

        public HeadViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.v_top)
        View vTop;
        @BindView(R.id.v_bottom)
        View vBottom;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_address)
        TextView tvAddress;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
