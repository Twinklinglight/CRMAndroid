package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.crm.BattlefieldReportActivity;
import com.wtcrmandroid.activity.crm.MainClientLibrary;
import com.wtcrmandroid.activity.crm.MyClientLibrary;
import com.wtcrmandroid.activity.crm.MyPushCustomerActivity;
import com.wtcrmandroid.activity.crm.PublicSeaActivity;
import com.wtcrmandroid.activity.field.FieldActivity;
import com.wtcrmandroid.activity.journalmanager.JournalManagerActivity;
import com.wtcrmandroid.activity.salepullcustomer.SalePullintoCustomerActivity;
import com.wtcrmandroid.model.data.HomeItemD;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/7/19.
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<HomeItemD> list;
    private final int HEAD = 0;
    private final int WORD = 1;

    public HomeAdapter(Context context, List<HomeItemD> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        if(list.get(position).getUrl()==0)
            return HEAD;
        else
            return WORD;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        RecyclerView.ViewHolder holder;
        if (viewType == HEAD) {
            v = LayoutInflater.from(context).inflate(R.layout.item_home_head, parent, false);
            holder = new HeadViewHolder(v);
        } else {
            v = LayoutInflater.from(context).inflate(R.layout.item_home, parent, false);
            holder = new ViewHolder(v);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(getItemViewType(position)==HEAD){
            HeadViewHolder headViewHolder= (HeadViewHolder) holder;
            headViewHolder.tvTitle.setText(list.get(position).getName());
        }else {
            ViewHolder viewHolder= (ViewHolder) holder;
            viewHolder.ivImage.setBackgroundResource(list.get(position).getUrl());
            viewHolder.tvName.setText(list.get(position).getName());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Click(list.get(position).getClick());
                }
            });
        }


//        if (list.get(position).getUrl() == 0) {
////            if (position == 0)
////                holder.v.setVisibility(View.GONE);
////            else holder.v.setVisibility(View.VISIBLE);
//            holder.tvTitle.setText(list.get(position).getName());
//            holder.ivImage.setVisibility(View.GONE);
//            holder.tvName.setVisibility(View.GONE);
//        } else {
////            holder.v.setVisibility(View.GONE);
//            holder.tvTitle.setVisibility(View.GONE);
//            holder.ivImage.setBackgroundResource(list.get(position).getUrl());
//            holder.tvName.setText(list.get(position).getName());
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Click(list.get(position).getClick());
//                }
//            });
//        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if(manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    int i=0;
                    switch (type){
                        case HEAD:
                            i=4;
                            break;
                        case WORD:
                            i=1;
                            break;
                    }
                    return i;
                }
            });
        }
    }
    static class HeadViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        public HeadViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        //        @BindView(R.id.v)
//        View v;

        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.tv_name)
        TextView tvName;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void Click(int i) {
        switch (i) {
            //日志管理点击事件
            case 1:
                context.startActivity(new Intent(context, JournalManagerActivity.class));
                break;
            case 2://通知公告
                Toast.makeText(context,R.string.Unrealized,Toast.LENGTH_SHORT).show();
                break;
            case 3://公文审批
                Toast.makeText(context,R.string.Unrealized,Toast.LENGTH_SHORT).show();
//                context.startActivity(new Intent(context, DocumentProcessActivity.class));
                break;
            case 4://外勤点击事件
                context.startActivity(new Intent(context, FieldActivity.class));
                break;
            case 5://会议室预约
                Toast.makeText(context,R.string.Unrealized,Toast.LENGTH_SHORT).show();
                break;
            case 6://员工管理
                Toast.makeText(context,R.string.Unrealized,Toast.LENGTH_SHORT).show();
                break;
            case 8://战报点击事件
                context.startActivity(new Intent(context, BattlefieldReportActivity.class));
                break;
            case 9://扫一扫
                Toast.makeText(context,R.string.Unrealized,Toast.LENGTH_SHORT).show();
                break;
            case 10: //录入客户
//                if (!MyApplication.application.getLoginData().getAttribution().equals("WT")) {
//                    context.startActivity(new Intent(context, PullintoCustomerActivity.class));
//                } else {
                    context.startActivity(new Intent(context, SalePullintoCustomerActivity.class));
//                }
                break;
            case 11://主客户库
                context.startActivity(new Intent(context, MainClientLibrary.class));
                break;
            case 12://我的客户库
                context.startActivity(new Intent(context, MyClientLibrary.class));
                break;
            case 13://续单公海
                context.startActivity(new Intent(context, PublicSeaActivity.class));
                break;
            case 14://我的地推客户
                context.startActivity(new Intent(context, MyPushCustomerActivity.class));
                break;
            case 16://产品建议
                Toast.makeText(context,R.string.Unrealized,Toast.LENGTH_SHORT).show();
                break;
            case 17://密条
                Toast.makeText(context,R.string.Unrealized,Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
