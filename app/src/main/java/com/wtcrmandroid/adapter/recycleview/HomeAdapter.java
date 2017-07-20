package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wtcrmandroid.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.aboutdocument.DocumentProcessActivity;
import com.wtcrmandroid.activity.crm.BattlefieldReportActivity;
import com.wtcrmandroid.activity.crm.MainClientLibrary;
import com.wtcrmandroid.activity.crm.MyClientLibrary;
import com.wtcrmandroid.activity.crm.MyPushCustomerActivity;
import com.wtcrmandroid.activity.crm.PublicSeaActivity;
import com.wtcrmandroid.activity.field.FieldActivity;
import com.wtcrmandroid.activity.foodpullcustomer.PullintoCustomerActivity;
import com.wtcrmandroid.activity.journalmanager.JournalManagerActivity;
import com.wtcrmandroid.activity.salepullcustomer.SalePullintoCustomerActivity;
import com.wtcrmandroid.model.data.HomeItemD;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/7/19.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private Context context;
    private List<HomeItemD> list;


    public HomeAdapter(Context context, List<HomeItemD> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_home, parent, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        if (list.get(position).getUrl() == 0) {
//            if (position == 0)
//                holder.v.setVisibility(View.GONE);
//            else holder.v.setVisibility(View.VISIBLE);
            holder.tvTitle.setText(list.get(position).getName());
            holder.ivImage.setVisibility(View.GONE);
            holder.tvName.setVisibility(View.GONE);
        } else {
//            holder.v.setVisibility(View.GONE);
            holder.tvTitle.setVisibility(View.GONE);
            holder.ivImage.setBackgroundResource(list.get(position).getUrl());
            holder.tvName.setText(list.get(position).getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Click(list.get(position).getClick());
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        //        @BindView(R.id.v)
//        View v;
        @BindView(R.id.tv_title)
        TextView tvTitle;
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
            case 3://公文审批
                context.startActivity(new Intent(context, DocumentProcessActivity.class));
                break;
            case 4://外勤点击事件
                context.startActivity(new Intent(context, FieldActivity.class));
                break;
            case 8://战报点击事件
                context.startActivity(new Intent(context, BattlefieldReportActivity.class));
                break;
            case 9://扫一扫
                break;
            case 10: //录入客户
                if (!MyApplication.application.getLoginData().getAttribution().equals("WT")) {
                    context.startActivity(new Intent(context, PullintoCustomerActivity.class));
                } else {
                    context.startActivity(new Intent(context, SalePullintoCustomerActivity.class));
                }
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

        }
    }
}
