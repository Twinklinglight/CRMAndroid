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
 * Created by wt-pc on 2017/6/21.
 */

public class ExpandableRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public final int VIEW_TYPE_GROUPITEM = 0;
    public final int VIEW_TYPE_SUBITEM = 1;
    private Context context;

    public ExpandableRecycleViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        RecyclerView.ViewHolder holder;
        if (viewType == VIEW_TYPE_GROUPITEM) {
            v = LayoutInflater.from(context).inflate(R.layout.item_contacts_group, parent, false);
            holder = new GroupViewHolder(v);
        } else {
            v = LayoutInflater.from(context).inflate(R.layout.item_contacts_child, parent, false);
            holder = new SubitemViewHolder(v);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if(getItemViewType(position)==VIEW_TYPE_GROUPITEM){
            GroupViewHolder groupViewHolder= (GroupViewHolder) holder;
            groupViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notifyItemRangeRemoved(position + 1, 2);
                }
            });
        }else {
            SubitemViewHolder subitemViewHolder= (SubitemViewHolder) holder;

        }
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == 3 || position == 6)
            return VIEW_TYPE_GROUPITEM;
        else
            return VIEW_TYPE_SUBITEM;
    }

    static class GroupViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_department_name)
        TextView tvDepartmentName;

        public GroupViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
    static class SubitemViewHolder extends RecyclerView.ViewHolder{

        public SubitemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
