package com.wtcrmandroid.adapter.listview;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.model.reponsedata.MajorCustomerData;
import com.wtcrmandroid.view.dialog.SelectionJobCategoriesDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxd on 2017/6/14
 */

public class MajorCustomerAtAdapter extends MySmallBaseAdapter<MajorCustomerData, MajorCustomerAtAdapter.ViewHolder> implements SelectionJobCategoriesDialog.WorkLinstener {

    public MajorCustomerAtAdapter(Activity activity, List<MajorCustomerData> list) {
        super(activity, list);
    }

    @Override
    protected void convert(ViewHolder holder, final int position) {
        if (list.size()>1){
            holder.mIvDelete.setVisibility(View.VISIBLE);
            holder.mIvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(position);
                    notifyDataSetChanged();
                }
            });
        }
        holder.mRlMajorSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String workSort = list.get(position).getWorkSort();
                int tag = 0;
                if (workSort == null){
                    tag = 0;
                }else {
                    switch (workSort){
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
                }
                new SelectionJobCategoriesDialog(activity,MajorCustomerAtAdapter.this,position,tag).show();
            }
        });
        holder.mTvMajorSort.setText(list.get(position).getWorkSort());
        holder.mEtMajorName.setText(list.get(position).getCustomerName());
        holder.mEtMajorAnalysis.setText(list.get(position).getWorkAnalysis());

        holder.mEtMajorName.addTextChangedListener(new MyTextWatcher(list.get(position),position,0));
        holder.mEtMajorAnalysis.addTextChangedListener(new MyTextWatcher(list.get(position),position,1));

    }

    @Override
    protected View onCreateViewHolder() {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_major_customer, null);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    protected View onCreateNullViewholder() {
        return null;
    }

    @Override
    public void WorkSelect(String workSort, int position) {
        list.get(position).setWorkSort(workSort);
        notifyDataSetChanged();
    }

    static class ViewHolder {
        @BindView(R.id.tv_major_sort)
        TextView mTvMajorSort;
        @BindView(R.id.iv_delete)
        ImageView mIvDelete;
        @BindView(R.id.rl_major_sort)
        RelativeLayout mRlMajorSort;
        @BindView(R.id.et_major_name)
        EditText mEtMajorName;
        @BindView(R.id.et_major_analysis)
        EditText mEtMajorAnalysis;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class MyTextWatcher implements TextWatcher {

        private MajorCustomerData mData;
        private int position;
        private int type;

        public MyTextWatcher(MajorCustomerData data, int position, int type) {
            mData = data;
            this.position = position;
            this.type = type;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            switch (type){
                case 0:
                    mData.setCustomerName(s.toString());
                    break;
                case 1:
                    mData.setWorkAnalysis(s.toString());
                    break;
                default:
                    break;
            }
            list.set(position,mData);
        }
    }
}
