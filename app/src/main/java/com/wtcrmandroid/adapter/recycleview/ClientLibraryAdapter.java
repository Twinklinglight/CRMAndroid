package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.model.reponsedata.SearchCustomerRP;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/6/23.
 */

public class ClientLibraryAdapter extends BaseRecycleAdapter<SearchCustomerRP, ClientLibraryAdapter.ViewHolder> {



    private int style = 0;

    public void setStyle(int style) {
        this.style = style;
    }

    /**
     * @param context //上下文
     */
    public ClientLibraryAdapter(Context context) {
        super(context, R.layout.item_client_library);
    }


    @Override
    protected void convert(ViewHolder holder, SearchCustomerRP bean, int position) {

        holder.tvCompanyName.setText(bean.getCompanyName());
        holder.tvCompanyAddress.setText("公司地址：" + bean.getSite());
        holder.tvTime.setText("入库时间：" + bean.getRecordTime());
        holder.tvKind.setText(bean.getCustomerKind());
        if (bean.getCurrentStatus().equals("Sale")) {
            holder.tvLibraryKind.setText("销售库");
        } else if (bean.getCurrentStatus().equals("Free")) {
            holder.tvLibraryKind.setText("公海");
        } else if (bean.getCurrentStatus().equals("Order")) {
            holder.tvLibraryKind.setText("成单库");
        } else {
            holder.tvLibraryKind.setText("过期物信通库");
        }
        switch (style){
            case 0:
                break;
            case 1:
                holder.tvSource.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_company_name)
        TextView tvCompanyName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_company_address)
        TextView tvCompanyAddress;
        @BindView(R.id.tv_kind)
        TextView tvKind;
        @BindView(R.id.tv_library_kind)
        TextView tvLibraryKind;
        @BindView(R.id.tv_source)
        TextView tvSource;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
