package com.wtcrmandroid.adapter.listview;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.thinkcool.circletextimageview.CircleTextImageView;
import com.wtcrmandroid.R;
import com.wtcrmandroid.model.reponsedata.DepartmentRponseData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxd on 2017/6/15
 */

public class DepartmentJournalAdapter extends BaseAdapter<DepartmentRponseData, DepartmentJournalAdapter.ViewHolder> {

    public DepartmentJournalAdapter(Activity activity, List<DepartmentRponseData> list) {
        super(activity, list);
    }

    @Override
    protected void convert(ViewHolder holder, int position) {

        DepartmentRponseData departmentData = list.get(position);

        holder.mTvName.setText(departmentData.getUsername());
        holder.mTvCommentCount.setText("本月已被评论"+departmentData.getColumn1()+"条");

        setColor(departmentData.getRoleLevel(),holder);

        String username = departmentData.getUsername();
        int begin = username.length()-2;
        int end = username.length();
        holder.mIvHeadpicture.setText(username.substring(begin,end));

    }

    @Override
    protected ViewHolder onCreateViewHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    protected View CretaView() {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_department_journal, null);
        return view;
    }

    //设置图片颜色
    private void setColor(String type,ViewHolder holder){
        switch (type){
            case "普通员工":
                holder.mIvHeadpicture.setFillColor(Color.parseColor("#39BAFF"));
                break;
            case "团队经理":
                holder.mIvHeadpicture.setFillColor(Color.parseColor("#F7BE29"));
                break;
            case "部长":
                holder.mIvHeadpicture.setFillColor(Color.parseColor("#94D301"));
                break;
            case "总监":
                holder.mIvHeadpicture.setFillColor(Color.parseColor("#F76129"));
                break;
        }
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
