package com.wtcrmandroid.adapter.listview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wtcrmandroid.R;
import com.wtcrmandroid.model.DepartmentData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by zxd on 2017/6/15
 */

public class DepartmentJournalAdapter extends MySmallBaseAdapter<DepartmentData, DepartmentJournalAdapter.ViewHolder> {

    public DepartmentJournalAdapter(Activity activity, List<DepartmentData> list) {
        super(activity, list);
    }

    @Override
    protected void convert(ViewHolder holder, int position) {

        DepartmentData departmentData = list.get(position);
        holder.mTvName.setText(departmentData.getPersonName());
        holder.mTvCommentCount.setText(departmentData.getCommentCount());
        Glide.with(activity)
                .load(R.mipmap.ic_home_scan)
                .bitmapTransform(new CropCircleTransformation(activity))
                .into(holder.mIvHeadpicture);
    }

    @Override
    protected View onCreateViewHolder() {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_department_journal, null);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.iv_headpicture)
        ImageView mIvHeadpicture;
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_comment_count)
        TextView mTvCommentCount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
