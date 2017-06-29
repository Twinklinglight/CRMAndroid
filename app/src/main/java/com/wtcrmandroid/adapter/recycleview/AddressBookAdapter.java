package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.wtcrmandroid.R;
import com.wtcrmandroid.contacts.DepartmentActivity;
import com.wtcrmandroid.contacts.GroupsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/6/21.
 * 通讯录适配器
 */

public class AddressBookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int HEAD = 0;//头部
    private final int INDEX = 1;//索引
    private final int CONTENT = 2;

    private Context context;

    public AddressBookAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case HEAD:
                view = LayoutInflater.from(context).inflate(R.layout.item_address_book_head, parent, false);
                return new HeadViewHolder(view);
            case INDEX:
//                View word = LayoutInflater.from(context).inflate(R.layout.layout_word, parent, false);
//                return new WordViewHolder(word);
            case CONTENT:
//                View city = LayoutInflater.from(context).inflate(R.layout.layout_city, parent, false);
//                return new CityViewHolder(city);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case HEAD:
                HeadViewHolder headViewHolder= (HeadViewHolder) holder;
                headViewHolder.llDepartment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, DepartmentActivity.class));

                    }
                });
                headViewHolder.llGroup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, GroupsActivity.class));
                    }
                });
            case INDEX:

            case CONTENT:

        }

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        int count = 0;
        if (position == count) return HEAD;//下标为0的固定显示头部布局。
        return super.getItemViewType(position);
    }

    static class HeadViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ll_department)
        LinearLayout llDepartment;
        @BindView(R.id.ll_group)
        LinearLayout llGroup;

        public HeadViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
