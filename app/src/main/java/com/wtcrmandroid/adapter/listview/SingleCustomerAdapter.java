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
import com.wtcrmandroid.model.SingleCustomerData;
import com.wtcrmandroid.view.dialog.SelectionJobCategoriesDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxd on 2017/6/14
 */

public class SingleCustomerAdapter extends MySmallBaseAdapter<SingleCustomerData, SingleCustomerAdapter.ViewHolder> implements SelectionJobCategoriesDialog.WorkLinstener {

    public SingleCustomerAdapter(Activity activity, List<SingleCustomerData> list) {
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

        holder.mTvSingleSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String workSort = list.get(position).getWorkSort();
                int tag = 0;
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
                new SelectionJobCategoriesDialog(activity,SingleCustomerAdapter.this,position,tag).show();
            }
        });
        holder.mTvSingleSort.setText(list.get(position).getWorkSort());
        holder.mEtSingleName.setText(list.get(position).getWorkName());
        holder.mEtSingleAnalysis.setText(list.get(position).getWorkAnalysis());
        holder.mEtSinglePercent.setText(list.get(position).getWorkPercent());

        holder.mEtSingleName.addTextChangedListener(new MyTextWatcher(list.get(position),position,0));
        holder.mEtSingleAnalysis.addTextChangedListener(new MyTextWatcher(list.get(position),position,1));
        holder.mEtSinglePercent.addTextChangedListener(new MyTextWatcher(list.get(position),position,2));


    }

    @Override
    protected View onCreateViewHolder() {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_single_customer, null);
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
        @BindView(R.id.tv_single_sort)
        TextView mTvSingleSort;
        @BindView(R.id.iv_delete)
        ImageView mIvDelete;
        @BindView(R.id.rl_single_sort)
        RelativeLayout mRlSingleSort;
        @BindView(R.id.et_single_name)
        EditText mEtSingleName;
        @BindView(R.id.et_single_analysis)
        EditText mEtSingleAnalysis;
        @BindView(R.id.et_single_percent)
        EditText mEtSinglePercent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class MyTextWatcher implements TextWatcher{

        private SingleCustomerData mData;
        private int position;
        private int type;

        public MyTextWatcher(SingleCustomerData data, int position, int type) {
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
                    mData.setWorkName(s.toString());
                    break;
                case 1:
                    mData.setWorkAnalysis(s.toString());
                    break;
                case 2:
                    mData.setWorkPercent(s.toString());
                    break;
                default:
                    break;
            }
            list.set(position,mData);
        }
    }
}
