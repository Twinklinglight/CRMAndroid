package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wtcrmandroid.R;
import com.wtcrmandroid.model.reponsedata.SearchCustomerRP;

import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/6/23.
 */

public class StaffPositionSearchAdapter extends BaseRecycleAdapter<SearchCustomerRP, StaffPositionSearchAdapter.ViewHolder> {


    /**
     * @param context //上下文
     */
    public StaffPositionSearchAdapter(Context context) {
        super(context, R.layout.item_client_library);
    }


    @Override
    protected void convert(ViewHolder holder, final SearchCustomerRP bean, int position) {


    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }


}
