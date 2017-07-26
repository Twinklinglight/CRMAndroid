package com.wtcrmandroid.adapter.listview;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.model.reponsedata.GetMoneyData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxd on 2017/6/14
 */

public class GetMoneyAtAdapter extends MySmallBaseAdapter<GetMoneyData, GetMoneyAtAdapter.ViewHolder> {

    public GetMoneyAtAdapter(Activity activity, List<GetMoneyData> list) {
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

        holder.mEtBackmoney.setText(list.get(position).getBackMoney());
        holder.mEtCustomerType.setText(list.get(position).getCustomerType());
        holder.mEtGetBackmoneyName.setText(list.get(position).getCustomerName());
        holder.mEtProductType.setText(list.get(position).getProductType());

        holder.mEtGetBackmoneyName.addTextChangedListener(new MyTextWatcher(list.get(position),
                position,0));
        holder.mEtCustomerType.addTextChangedListener(new MyTextWatcher(list.get(position),
                position,1));
        holder.mEtProductType.addTextChangedListener(new MyTextWatcher(list.get(position),
                position,2));
        holder.mEtBackmoney.addTextChangedListener(new MyTextWatcher(list.get(position),
                position,3));
    }

    @Override
    protected View onCreateViewHolder() {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_backmoney, null);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    protected View onCreateNullViewholder() {
        return null;
    }

    static class ViewHolder {
        @BindView(R.id.et_get_backmoney_name)
        EditText mEtGetBackmoneyName;
        @BindView(R.id.iv_delete)
        ImageView mIvDelete;
        @BindView(R.id.et_Customer_type)
        EditText mEtCustomerType;
        @BindView(R.id.et_product_type)
        EditText mEtProductType;
        @BindView(R.id.et_backmoney)
        EditText mEtBackmoney;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class MyTextWatcher implements TextWatcher{

        private GetMoneyData mGetMoneyData;
        private int position;
        private int type;

        public MyTextWatcher(GetMoneyData getMoneyData, int position, int type) {
            mGetMoneyData = getMoneyData;
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
                    mGetMoneyData.setCustomerName(s.toString());
                    break;
                case 1:
                    mGetMoneyData.setCustomerType(s.toString());
                    break;
                case 2:
                    mGetMoneyData.setProductType(s.toString());
                    break;
                case 3:
                    mGetMoneyData.setBackMoney(s.toString());
                    break;
                default:
                    break;
            }
            list.set(position,mGetMoneyData);
        }
    }
}
