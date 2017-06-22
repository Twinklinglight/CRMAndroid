package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wtcrmandroid.R;

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

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == 3 || position == 6)
            return HEAD;
        else
            return WORD;
    }

    public ClockRecordAdapter(Context context) {
        this.context = context;
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
        if(getItemViewType(position)==HEAD){
            HeadViewHolder headViewHolder= (HeadViewHolder) holder;
        }else {
            ViewHolder viewHolder= (ViewHolder) holder;
            if(position==1||position==4||position==7)
                viewHolder.vTop.setVisibility(View.INVISIBLE);
            else
                viewHolder.vTop.setVisibility(View.VISIBLE);
            if(position==2||position==5||position==8)
                viewHolder.vBottom.setVisibility(View.INVISIBLE);
            else
                viewHolder.vBottom.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return 9;
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

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
