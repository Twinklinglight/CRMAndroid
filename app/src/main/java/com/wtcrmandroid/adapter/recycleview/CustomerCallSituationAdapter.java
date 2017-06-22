package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wtcrmandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/6/19.
 */

public class CustomerCallSituationAdapter extends BaseRecycleAdapter<String,CustomerCallSituationAdapter.ViewHolder> {



    /**
     * @param context //上下文
     */
    public CustomerCallSituationAdapter(Context context) {
        super(context, R.layout.item_customer_call_situation);
    }

    @Override
    protected void convert(ViewHolder holder, String bean,int position) {
        if(position==0) {
            holder.vTop.setVisibility(View.INVISIBLE);

        }else {
            holder.vTop.setVisibility(View.VISIBLE);
        }
        if(position==4)
            holder.vBottom.setVisibility(View.INVISIBLE);
        else
            holder.vBottom.setVisibility(View.VISIBLE);
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return  new ViewHolder(v);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.v_top)
        View vTop;
        @BindView(R.id.v_bottom)
        View vBottom;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}
