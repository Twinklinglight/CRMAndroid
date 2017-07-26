package com.wtcrmandroid.adapter.listview;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.model.reponsedata.GetSingleCustomerData;
import com.wtcrmandroid.view.dialog.IfCaiDianDialog;
import com.wtcrmandroid.view.dialog.SelectionJobCategoriesDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxd on 2017/6/14
 */

public class GetSingleAtAdapter extends MySmallBaseAdapter<GetSingleCustomerData, GetSingleAtAdapter.ViewHolder> implements SelectionJobCategoriesDialog.WorkLinstener,IfCaiDianDialog.CaiListener {

    public GetSingleAtAdapter(Activity activity, List<GetSingleCustomerData> list) {
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

        GetSingleCustomerData customerData = list.get(position);
        holder.mTvGetSingleSort.setText(customerData.getWorkSort());
        holder.mEtGetSingleName.setText(customerData.getWorkName());
        holder.mTvIfGet.setText(customerData.getIfGet());
        holder.mEtSingleAnalysis.setText(customerData.getReasonAnalysis());
        holder.mEtSinglePlan.setText(customerData.getGenjinplan());

        holder.mEtGetSingleName.addTextChangedListener(new MyTextWatcher(customerData,
                position,0));
        holder.mEtSingleAnalysis.addTextChangedListener(new MyTextWatcher(customerData,
                position,1));
        holder.mEtSinglePlan.addTextChangedListener(new MyTextWatcher(customerData,
                position,2));

        //事项等级
        holder.mRlSingleSort.setOnClickListener(new View.OnClickListener() {
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
                new SelectionJobCategoriesDialog(activity,GetSingleAtAdapter.this,position,tag).show();
            }
        });

        //是否踩中
        holder.mLlIfGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ifGet = list.get(position).getIfGet();
                int tag = 0;
                if (ifGet != null){
                    switch (ifGet){
                        case "是":
                            tag = 1;
                            break;
                        case "否":
                            tag = 2;
                            break;
                    }
                }
                new IfCaiDianDialog(activity,GetSingleAtAdapter.this,position,tag).show();
            }
        });

    }

    @Override
    protected View onCreateViewHolder() {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_get_single_customer, null);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    protected View onCreateNullViewholder() {
        return null;
    }

    //事件等级的回调
    @Override
    public void WorkSelect(String workSort, int position) {
        list.get(position).setWorkSort(workSort);
        notifyDataSetChanged();
    }

    //是否选中的回调
    @Override
    public void ChooseClick(String text, int position) {
        list.get(position).setIfGet(text);
        notifyDataSetChanged();
    }

    static class ViewHolder {
        @BindView(R.id.tv_get_single_sort)
        TextView mTvGetSingleSort;
        @BindView(R.id.iv_delete)
        ImageView mIvDelete;
        @BindView(R.id.rl_single_sort)
        RelativeLayout mRlSingleSort;
        @BindView(R.id.et_get_single_name)
        EditText mEtGetSingleName;
        @BindView(R.id.tv_if_get)
        TextView mTvIfGet;
        @BindView(R.id.ll_if_get)
        LinearLayout mLlIfGet;
        @BindView(R.id.et_single_analysis)
        EditText mEtSingleAnalysis;
        @BindView(R.id.et_single_plan)
        EditText mEtSinglePlan;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class MyTextWatcher implements TextWatcher{

        private GetSingleCustomerData mData;
        private int position;
        private int type;

        public MyTextWatcher(GetSingleCustomerData data, int position, int type) {
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
                    mData.setReasonAnalysis(s.toString());
                    break;
                case 2:
                    mData.setGenjinplan(s.toString());
                    break;
                default:
                    break;

            }
            list.set(position,mData);
        }
    }
}
