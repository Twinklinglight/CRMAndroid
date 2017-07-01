package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wtcrmandroid.R;

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

    private Context context;

    @Override
    public int getItemViewType(int position) {
        if (position == 4)
            return  WORD;
        else if (position == 5)
            return  BOTTOM;
        else
            return HEAD;
    }

    public FieldClockAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        RecyclerView.ViewHolder holder;
        if (viewType == HEAD) {
            v = LayoutInflater.from(context).inflate(R.layout.item_timeline, parent, false);
            holder = new HeadViewHolder(v);
        } else if (viewType == WORD){
            v = LayoutInflater.from(context).inflate(R.layout.item_clock_bottom, parent, false);
            holder = new ViewHolder(v);
        }else {
            v = LayoutInflater.from(context).inflate(R.layout.item_clock_button, parent, false);
            holder = new ButtonViewHolder(v);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==HEAD){
            HeadViewHolder headViewHolder= (HeadViewHolder) holder;
            if(position==0)
                headViewHolder.vTop.setVisibility(View.INVISIBLE);
            else
                headViewHolder.vTop.setVisibility(View.VISIBLE);
        }else {

        }

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    static class HeadViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.v_top)
        View vTop;
        @BindView(R.id.v_bottom)
        View vBottom;

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
}
