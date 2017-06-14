package com.wtcrmandroid.adapter.listview;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by 1363655717 on 2017-06-06.
 * item数量不是太多的情况下使用
 */

public abstract class MySmallBaseAdapter<T, T1> extends BaseAdapter {
    protected List<T> list;
    protected Activity activity;

    public MySmallBaseAdapter(Activity activity, List<T> list) {
        this.activity = activity;
        this.list = list;
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        T1 holder = null;
//        if (convertView == null) {
            convertView = onCreateViewHolder();
//        }
        holder = (T1) convertView.getTag();
        convert(holder, position);
        return convertView;
    }

    protected abstract void convert(T1 holder, int position);

    protected abstract View onCreateViewHolder();
}
