package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.field.CustomerCallSituationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/6/19.
 */

public class MyCallRecordAdapter extends BaseRecycleAdapter<String, MyCallRecordAdapter.ViewHolder> {


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
    protected void convert(ViewHolder holder, String bean,int position) {
        holder.llCompanyName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, CustomerCallSituationActivity.class));
            }
        });

    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_company_name)
        LinearLayout llCompanyName;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}
