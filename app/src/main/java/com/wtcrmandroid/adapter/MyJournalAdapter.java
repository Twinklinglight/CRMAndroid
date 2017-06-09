package com.wtcrmandroid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.model.MyJournalData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxd on 2017/6/8
 */

public class MyJournalAdapter extends BaseAdapter {

    private List<MyJournalData> mDatas;

    public MyJournalAdapter(List<MyJournalData> datas) {
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = null;
        if (view == null || !(view.getTag() instanceof ViewHolder)){

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_journal, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tv_journal_title)
        TextView mTvJournalTitle;
        @BindView(R.id.tv_journal_type)
        ImageView mTvJournalType;
        @BindView(R.id.tv_journal_content)
        TextView mTvJournalContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
