package com.wtcrmandroid.adapter.listview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.thinkcool.circletextimageview.CircleTextImageView;
import com.wtcrmandroid.R;
import com.wtcrmandroid.model.DepartmentData;
import com.wtcrmandroid.model.reponsedata.DepartmentRponseData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by zxd on 2017/6/15
 */

public class DepartmentJournalAdapter extends MySmallBaseAdapter<DepartmentRponseData, DepartmentJournalAdapter.ViewHolder> {

    public DepartmentJournalAdapter(Activity activity, List<DepartmentRponseData> list) {
        super(activity, list);
    }

    @Override
    protected void convert(ViewHolder holder, int position) {

        DepartmentRponseData departmentData = list.get(position);
        holder.mTvName.setText(departmentData.getUsername());
        holder.mTvCommentCount.setText("本月已被评论"+departmentData.getColumn1()+"条");
        String username = departmentData.getUsername();
        int begin = username.length()-2;
        int end = username.length();
        holder.mIvHeadpicture.setText(username.substring(begin,end));


    }

    @Override
    protected View onCreateViewHolder() {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_department_journal, null);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    protected View onCreateNullViewholder() {
        return null;
    }

    static class ViewHolder {
        @BindView(R.id.iv_headpicture)
        CircleTextImageView mIvHeadpicture;
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_comment_count)
        TextView mTvCommentCount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
