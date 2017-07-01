package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wtcrmandroid.R;

import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/6/19.
 */

public class PersonerRankingAdapter extends BaseRecycleAdapter<String, PersonerRankingAdapter.ViewHolder> {


    /**
     * @param context //上下文
     */
    public PersonerRankingAdapter(Context context) {
        super(context, R.layout.item_team_ranking);
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }


    @Override
    protected void convert(ViewHolder holder, String bean,int position) {


    }


    static class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}
