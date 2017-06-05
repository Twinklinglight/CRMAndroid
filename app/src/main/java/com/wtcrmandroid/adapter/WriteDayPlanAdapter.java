package com.wtcrmandroid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.wtcrmandroid.R;
import com.wtcrmandroid.view.WorkSortDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxd on 2017/6/3.
 */

public class WriteDayPlanAdapter extends BaseAdapter {

    private List<String> mList;

    public WriteDayPlanAdapter(List<String> list) {
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = null;
        if (view == null || !(view.getTag() instanceof ViewHolder)) {

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_write_dayplan, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.mRlDayplanSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WorkSortDialog(parent.getContext()).show();
            }
        });

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.rl_dayplan_sort)
        RelativeLayout mRlDayplanSort;
        @BindView(R.id.tv_dayplan_content)
        EditText mTvDayplanContent;
        @BindView(R.id.tv_dayplan_person)
        EditText mTvDayplanPerson;
        @BindView(R.id.tv_dayplan_persent)
        EditText mTvDayplanPersent;
        @BindView(R.id.tv_dayplan_remark)
        EditText mTvDayplanRemark;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
