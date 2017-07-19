package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.field.CustomerCallSituationActivity;
import com.wtcrmandroid.model.reponsedata.PersonalAllRecordRP;
import com.wtcrmandroid.view.custompricing.SquareImageView;

import java.util.List;

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
                context.startActivity(new Intent(context, CustomerCallSituationActivity.class).putExtra("customerid", bean.getCustomerid()).putExtra("customerName", bean.getCustomerName()));
            }
        });
        holder.tvCustomerName.setText(bean.getCustomerName());
        holder.tvRemarks.setText("客户意向:" + bean.getRemarks());
        holder.tvCreateTime.setText(bean.getCreateTime());
        holder.tvAddressDetail.setText(bean.getAddress() + bean.getAddressDetail());
        List<String> path= bean.getPath();
        if(path==null||path.size()>0){
            holder.llImage.setVisibility(View.VISIBLE);
            switch (path.size()){
                case 1:
                    Glide.with(context)
                            .load(path.get(0))
                            .into(holder.sivOne);
                    break;
                case 2:
                    Glide.with(context)
                            .load(path.get(0))
                            .into(holder.sivOne);
                    Glide.with(context)
                            .load(path.get(1))
                            .into(holder.sivTwo);
                    break;
                case 3:
                    Glide.with(context)
                            .load(path.get(0))
                            .into(holder.sivOne);
                    Glide.with(context)
                            .load(path.get(1))
                            .into(holder.sivTwo);
                    Glide.with(context)
                            .load(path.get(2))
                            .into(holder.sivThree);
                    break;
                case 4:
                    Glide.with(context)
                            .load(path.get(0))
                            .into(holder.sivOne);
                    Glide.with(context)
                            .load(path.get(1))
                            .into(holder.sivTwo);
                    Glide.with(context)
                            .load(path.get(2))
                            .into(holder.sivThree);
                    Glide.with(context)
                            .load(path.get(3))
                            .into(holder.sivFour);
                    break;
                case 5:
                    Glide.with(context)
                            .load(path.get(0))
                            .into(holder.sivOne);
                    Glide.with(context)
                            .load(path.get(1))
                            .into(holder.sivTwo);
                    Glide.with(context)
                            .load(path.get(2))
                            .into(holder.sivThree);
                    Glide.with(context)
                            .load(path.get(3))
                            .into(holder.sivFour);
                    Glide.with(context)
                            .load(path.get(4))
                            .into(holder.sivFive);
                    break;
            }

        }else {
            holder.llImage.setVisibility(View.GONE);
        }

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
        @BindView(R.id.ll_image)
        LinearLayout llImage;
        @BindView(R.id.siv_one)
        SquareImageView sivOne;
        @BindView(R.id.siv_two)
        SquareImageView sivTwo;
        @BindView(R.id.siv_three)
        SquareImageView sivThree;
        @BindView(R.id.siv_four)
        SquareImageView sivFour;
        @BindView(R.id.siv_five)
        SquareImageView sivFive;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}
