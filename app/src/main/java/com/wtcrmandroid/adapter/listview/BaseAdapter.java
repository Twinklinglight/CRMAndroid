package com.wtcrmandroid.adapter.listview;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 1363655717 on 2017-06-06.
 * item数量不是太多的情况下使用
 */

public abstract class BaseAdapter<T, T1> extends android.widget.BaseAdapter {
    protected List<T> list;
    protected Activity activity;

    public BaseAdapter(Activity activity, List<T> list) {
        this.activity = activity;
        this.list = list;
    }

    public void setList(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
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
        View view = null;
        if (convertView == null) {
            view = CretaView();
            holder = onCreateViewHolder(view);
            view.setTag(holder);
        }else {
            view = convertView;
            holder = (T1) view.getTag();
        }
        convert(holder, position);
        return view;
    }

    protected abstract void convert(T1 holder, int position);

    protected abstract T1 onCreateViewHolder(View v);

    protected abstract View CretaView();
}
