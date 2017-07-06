package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.model.dealdata.GroupingClockRecordDD;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/6/20.
 * 打卡记录适配器
 */

public class ClockRecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int HEAD = 0;
    private final int WORD = 1;



    private Context context;
    private List<GroupingClockRecordDD> list;

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getType() == 0)
            return HEAD;
        else
            return WORD;
    }

    public ClockRecordAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<GroupingClockRecordDD> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<GroupingClockRecordDD> getList() {
        return list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        RecyclerView.ViewHolder holder;
        if (viewType == HEAD) {
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
            headViewHolder.tvTime.setText(list.get(position).getTime());

        } else {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.tvTime.setText(list.get(position).getTime());
            viewHolder.tvAddress.setText(list.get(position).getAddress());
            if (position != list.size() - 1) {
                if (getItemViewType(position - 1) == HEAD)
                    viewHolder.vTop.setVisibility(View.INVISIBLE);
                else
                    viewHolder.vTop.setVisibility(View.VISIBLE);
                if (getItemViewType(position + 1) == HEAD)
                    viewHolder.vBottom.setVisibility(View.INVISIBLE);
                else
                    viewHolder.vBottom.setVisibility(View.VISIBLE);

            } else {
                if (getItemViewType(position - 1) == HEAD)
                    viewHolder.vTop.setVisibility(View.INVISIBLE);
                else
                    viewHolder.vTop.setVisibility(View.VISIBLE);
                viewHolder.vBottom.setVisibility(View.INVISIBLE);
            }

        }

    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else
            return 0;
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
