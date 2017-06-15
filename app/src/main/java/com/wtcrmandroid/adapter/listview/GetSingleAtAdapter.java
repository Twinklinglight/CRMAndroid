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
import com.wtcrmandroid.model.GetSingleCustomerData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxd on 2017/6/14
 */

public class GetSingleAtAdapter extends MySmallBaseAdapter<GetSingleCustomerData, GetSingleAtAdapter.ViewHolder> {

    public GetSingleAtAdapter(Activity activity, List<GetSingleCustomerData> list) {
        super(activity, list);
    }

    @Override
    protected void convert(ViewHolder holder, int position) {

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

            }
        });

        //是否踩中
        holder.mLlIfGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    protected View onCreateViewHolder() {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_get_single_customer, null);
        return null;
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
