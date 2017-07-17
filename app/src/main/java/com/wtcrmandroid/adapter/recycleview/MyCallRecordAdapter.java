package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.field.CustomerCallSituationActivity;
import com.wtcrmandroid.model.reponsedata.PersonalAllRecordRP;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/6/19.
 */

public class MyCallRecordAdapter extends BaseRecycleAdapter<PersonalAllRecordRP, MyCallRecordAdapter.ViewHolder> {




    /**
     * @param context //上下文
     */
    public MyCallRecordAdapter(Context context) {
        super(context, R.layout.item_my_call_record);
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }


    @Override
    protected void convert(ViewHolder holder, final PersonalAllRecordRP bean, int position) {
        holder.llCompanyName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, CustomerCallSituationActivity.class).putExtra("customerid",bean.getCustomerid()).putExtra("customerName",bean.getCustomerName()));
            }
        });
        holder.tvCustomerName.setText(bean.getCustomerName());
        holder.tvRemarks.setText("客户意向:"+bean.getRemarks());
        holder.tvCreateTime.setText(bean.getCreateTime());
        holder.tvAddressDetail.setText(bean.getAddress()+bean.getAddressDetail());

    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_company_name)
        LinearLayout llCompanyName;
        @BindView(R.id.tv_customerName)
        TextView tvCustomerName;
        @BindView(R.id.tv_remarks)
        TextView tvRemarks;
        @BindView(R.id.tv_createTime)
        TextView tvCreateTime;
        @BindView(R.id.tv_addressDetail)
        TextView tvAddressDetail;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}
