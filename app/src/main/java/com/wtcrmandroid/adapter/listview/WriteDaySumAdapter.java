package com.wtcrmandroid.adapter.listview;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.model.WriteDaysumData;
import com.wtcrmandroid.view.dialog.CompleteConditionDialog;
import com.wtcrmandroid.view.dialog.SelectionJobCategoriesDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxd on 2017/6/13
 */

public class WriteDaySumAdapter extends MySmallBaseAdapter<WriteDaysumData, WriteDaySumAdapter.ViewHolder> implements SelectionJobCategoriesDialog.WorkLinstener, CompleteConditionDialog.CompeleteListener {


    public WriteDaySumAdapter(Activity activity, List<WriteDaysumData> list) {
        super(activity, list);
    }

    @Override
    protected void convert(ViewHolder holder, final int position) {

        if (list.size() > 1) {     //条目数量大于一，删除按钮显示
            holder.mIvDelete.setVisibility(View.VISIBLE);
            holder.mIvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(position);
                    notifyDataSetChanged();
                }
            });
        }

        final WriteDaysumData writeDaysumData = list.get(position);
        holder.mTvDaysumSort.setText(writeDaysumData.getWorkSort());
        holder.mTvDaysumSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String workSort = writeDaysumData.getWorkSort();
                int tag = 0;
                switch (workSort) {
                    case "A类 紧急又重要":
                        tag = 1;
                        break;
                    case "B类 较重要":
                        tag = 2;
                        break;
                    case "C类 重要":
                        tag = 3;
                        break;
                    case "D类 次重要":
                        tag = 4;
                        break;
                }
                new SelectionJobCategoriesDialog(activity, WriteDaySumAdapter.this, position, tag).show();
            }
        });
        holder.mTvDaysumContent.setText(writeDaysumData.getWorkContent());
        holder.mEtDaysumPerson.setText(writeDaysumData.getWorkPerson());

        //第一次为null 不展示
        if (writeDaysumData.getWorkComplete()==null && writeDaysumData.getWorkUnfinishedReason() == null){
            holder.mTvDaysumComplete.setText("");
        }else {
            holder.mTvDaysumComplete.setText(writeDaysumData.getWorkComplete()+ writeDaysumData.getWorkUnfinishedReason()+
                    writeDaysumData.getWorkNextFinishTime());
        }

        holder.llComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CompleteConditionDialog(activity,WriteDaySumAdapter.this,position).show();
            }
        });

        holder.mTvDaysumContent.addTextChangedListener(new MyTextWatch(writeDaysumData, position, 0));
        holder.mEtDaysumPerson.addTextChangedListener(new MyTextWatch(writeDaysumData, position, 1));

    }

    @Override
    protected View onCreateViewHolder() {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_write_daysum, null);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    protected View onCreateNullViewholder() {
        return null;
    }

    //工作等级选择回调
    @Override
    public void WorkSelect(String workSort, int position) {
        list.get(position).setWorkSort(workSort);
        notifyDataSetChanged();
    }

    //完成情况的回调
    @Override
    public void completeCondition(String text,int position) {
        list.get(position).setWorkUnfinishedReason("");
        list.get(position).setWorkNextFinishTime("");
        list.get(position).setWorkComplete("已完成 "+text);
        notifyDataSetChanged();
    }
    //未完成情况的回调
    @Override
    public void unCompleteReason(String reason, String time, int position) {
        list.get(position).setWorkComplete("未完成 ");
        list.get(position).setWorkUnfinishedReason(" "+reason);
        list.get(position).setWorkNextFinishTime(" "+time);
        notifyDataSetChanged();
    }

    static class ViewHolder {
        @BindView(R.id.tv_daysum_sort)
        TextView mTvDaysumSort;
        @BindView(R.id.iv_delete)
        ImageView mIvDelete;
        @BindView(R.id.tv_daysum_content)
        EditText mTvDaysumContent;
        @BindView(R.id.ib_daypsum_yy)
        ImageButton mIbDaypsumYy;
        @BindView(R.id.et_daysum_person)
        EditText mEtDaysumPerson;
        @BindView(R.id.tv_daysum_complete)
        TextView mTvDaysumComplete;
        @BindView(R.id.ll_complete)
        LinearLayout llComplete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class MyTextWatch implements TextWatcher {

        private WriteDaysumData mData;
        private int Type;               //输入框类型
        private int position;

        public MyTextWatch(WriteDaysumData data, int position, int type) {
            mData = data;
            Type = type;
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            switch (Type) {
                case 0:
                    mData.setWorkContent(s.toString());
                    break;
                case 1:
                    mData.setWorkPerson(s.toString());
                    break;
                default:
                    break;
            }
            list.set(position, mData);
        }
    }
}
