package com.wtcrmandroid.adapter.listview;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by 1363655717 on 2017-06-06.
 * item数量不是太多的情况下使用
 */

public abstract class MySmallBaseAdapter<T, T1> extends BaseAdapter {

    private final int nomalType = 1;
    private final int nullType = 2;

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
        Log.i("zxd","getCount = "+list.size());
        return list.size()>0 ?list.size() : 1;

    }

    @Override
    public Object getItem(int position) {
        Log.i("zxd","getItem执行了");
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        T1 holder = null;
//        if (convertView == null) {
        if (getItemViewType(position) == nomalType){

            convertView = onCreateViewHolder();
            holder = (T1) convertView.getTag();
            convert(holder, position);
        }else {
            convertView = onCreateNullViewholder();
        }
//        }

        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        Log.i("zxd","getItemViewType执行了");
        return (list.size()>0) ? nomalType : nullType;

    }

    protected abstract void convert(T1 holder, int position);

    protected abstract View onCreateViewHolder();
    protected abstract View onCreateNullViewholder();
}
