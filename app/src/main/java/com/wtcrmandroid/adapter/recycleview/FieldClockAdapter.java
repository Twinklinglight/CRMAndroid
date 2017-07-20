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
 * 打卡记录适配器
 */

public class FieldClockAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int HEAD = 0;
    private final int WORD = 1;
    private final int BOTTOM = 2;
    List<ClockRecordRP> list = new ArrayList<>();

    private Context context;
    private OnItemOnlick onItemOnlick;

    @Override
    public int getItemViewType(int position) {
        if (position == list.size())
            return WORD;
        else if (position == list.size() + 1)
            return BOTTOM;
        else
            return HEAD;
    }

    public FieldClockAdapter(Context context) {
        this.context = context;
        notifyDataSetChanged();
    }


    public void setList(List<ClockRecordRP> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        RecyclerView.ViewHolder holder;
        if (viewType == HEAD) {
            v = LayoutInflater.from(context).inflate(R.layout.item_timeline, parent, false);
            holder = new HeadViewHolder(v);
        } else  /*(viewType == WORD)*/{
            v = LayoutInflater.from(context).inflate(R.layout.item_clock_bottom, parent, false);
            holder = new ViewHolder(v);
        } /*else {
            v = LayoutInflater.from(context).inflate(R.layout.item_clock_button, parent, false);
            holder = new ButtonViewHolder(v);
        }*/
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == HEAD) {
            HeadViewHolder headViewHolder = (HeadViewHolder) holder;
            if (position == 0)
                headViewHolder.vTop.setVisibility(View.INVISIBLE);
            else
                headViewHolder.vTop.setVisibility(View.VISIBLE);
            headViewHolder.tvTime.setText(list.get(position).getCreateTime());
            headViewHolder.tvAddress.setText(list.get(position).getAddress());
        } else if (getItemViewType(position) == BOTTOM) {
            ButtonViewHolder buttonViewHolder = (ButtonViewHolder) holder;
            buttonViewHolder.itemView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemOnlick != null)
                                onItemOnlick.ClockOnlick();
                        }
                    }
            );

        }

    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    static class HeadViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.v_top)
        View vTop;
        @BindView(R.id.v_bottom)
        View vBottom;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_address)
        TextView tvAddress;
        public HeadViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class ButtonViewHolder extends RecyclerView.ViewHolder {


        public ButtonViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemOnlick {
        public void ClockOnlick();

    }

    public void setOnItemOnlick(OnItemOnlick onItemOnlick) {
        this.onItemOnlick = onItemOnlick;
    }
}
