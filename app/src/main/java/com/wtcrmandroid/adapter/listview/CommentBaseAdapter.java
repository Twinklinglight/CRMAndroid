package com.wtcrmandroid.adapter.listview;


import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.List;

/**
 * Created by zxd on 2017/7/12.
 */

public abstract class CommentBaseAdapter<T, T1> extends android.widget.BaseAdapter {

    protected List<T> list;
    protected Activity activity;

    public CommentBaseAdapter(Activity activity, List list) {
        this.list = list;
        this.activity = activity;
    }

    public void setList(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<T> getList() {
        return list;
    }

    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        T1 holder = null;
        convertView = onCreateViewHolder();
        holder = (T1) convertView.getTag();
        convert(holder, position);

        return convertView;
    }


    protected abstract void convert(T1 holder, int position);

    protected abstract View onCreateViewHolder();

}
