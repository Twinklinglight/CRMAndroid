package com.wtcrmandroid.adapter.expandablelistview;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wtcrmandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/6/21.
 */

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context context;

    public ExpandableListViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return 3;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 4;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_address_book_group, parent, false);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
            if(isExpanded)
                groupViewHolder.ivArrow.setBackground(context.getResources().getDrawable(R.mipmap.ic_graytop_arrow));
            else
                groupViewHolder.ivArrow.setBackground(context.getResources().getDrawable(R.mipmap.ic_graydown_arrow));
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        SubitemViewHolder subitemViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_address_book_subitem, parent, false);
            subitemViewHolder = new SubitemViewHolder(convertView);
            convertView.setTag(subitemViewHolder);
        } else {
            subitemViewHolder = (SubitemViewHolder) convertView.getTag();
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    static class GroupViewHolder {
        @BindView(R.id.tv_department_name)
        TextView tvDepartmentName;
        @BindView(R.id.iv_arrow)
        ImageView ivArrow;
        public GroupViewHolder(View itemView) {

            ButterKnife.bind(this, itemView);
        }

    }

    static class SubitemViewHolder {

        public SubitemViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }

    }
}
