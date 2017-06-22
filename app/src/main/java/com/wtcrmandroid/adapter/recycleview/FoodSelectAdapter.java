package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wtcrmandroid.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 选择会员类型、产品类型或者公司类型的adapter
 *
 * Created by zxd on 2017/6/20
 */

public class FoodSelectAdapter extends RecyclerView.Adapter<FoodSelectAdapter.ViewHolder> {

    private List<String> mList;
    private Context mContext;
    private SelectListener mSelectListener; //选择回调
    private int selectPosition;      //上次选中位置
    private int mtype;              //适配器类型 1、会员  2、产品 或者 1、会员  2、公司类型

    public FoodSelectAdapter(List<String> list,SelectListener mlistener,
                             int position,Context context,int type) {

        mList = list;
        this.mSelectListener = mlistener;
        this.selectPosition = position;
        this.mContext = context;
        this.mtype = type;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_select, null);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.mTvContent.setText(mList.get(position));

        if (selectPosition == position){
            holder.mIvSelected.setVisibility(View.VISIBLE);
            holder.mTvContent.setTextColor(ContextCompat.getColor(mContext,R.color.colorPrimary));
        }
        holder.mLlItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectListener.itemClick(position,mtype);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_content)
        TextView mTvContent;
        @BindView(R.id.iv_selected)
        ImageView mIvSelected;
        @BindView(R.id.ll_item)
        LinearLayout mLlItem;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface SelectListener{

        void itemClick(int position,int type);
    }
}
