package com.wtcrmandroid.adapter.expandablelistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.model.ContactsChild;
import com.wtcrmandroid.model.ContactsGroup;
import com.wtcrmandroid.model.reponsedata.ContactRP;
import com.wtcrmandroid.model.reponsedata.ContactsDpmentRP;

import java.util.List;

/**
 * Created by wt-pc on 2017/6/21
 */

public class GroupEListViewAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<ContactsDpmentRP> parentList;

    public GroupEListViewAdapter(Context context, List<ContactsDpmentRP> parentList) {
        this.context = context;
        this.parentList = parentList;
    }

    @Override
    public int getGroupCount() {
        return parentList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return parentList.get(groupPosition).getUsers().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parentList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return parentList.get(groupPosition).getUsers();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_contacts_group, parent, false);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        if (isExpanded)
            groupViewHolder.ivArrow.setImageResource(R.mipmap.ic_arrow_up);
        else
            groupViewHolder.ivArrow.setImageResource(R.mipmap.ic_arrow_down);
        groupViewHolder.tvDepartmentName.setText(parentList.get(groupPosition).getDepartmentName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_contacts_child, parent, false);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        ContactRP contactRP = parentList.get(groupPosition).getUsers().get(childPosition);

        childViewHolder.tvName.setText(contactRP.getUserName());
        childViewHolder.tvDepartment.setText(contactRP.getRoleName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private class GroupViewHolder {
        private TextView tvDepartmentName;
        private ImageView ivArrow;

        private GroupViewHolder(View itemView) {
            tvDepartmentName = (TextView) itemView.findViewById(R.id.tv_department_name);
            ivArrow = (ImageView) itemView.findViewById(R.id.iv_arrow);
        }
    }

    private class ChildViewHolder {
        private ImageView ivHead;
        private TextView tvName;
        private TextView tvDepartment;

        private ChildViewHolder(View itemView) {
            ivHead = (ImageView) itemView.findViewById(R.id.iv_head);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvDepartment = (TextView) itemView.findViewById(R.id.tv_department);
        }
    }
}
