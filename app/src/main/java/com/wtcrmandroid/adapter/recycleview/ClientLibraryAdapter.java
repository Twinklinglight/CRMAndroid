package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/6/23.
 */

public class ClientLibraryAdapter extends BaseRecycleAdapter<String, ClientLibraryAdapter.ViewHolder> {
    /**
     * @param context  //上下文
     */
    public ClientLibraryAdapter(Context context) {
        super(context, R.layout.item_client_library);
    }

    @Override
    protected void convert(ViewHolder holder, String bean, int position) {

    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }








    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_company_name)
        TextView tvCompanyName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
