package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.crm.MyClientLibrary;
import com.wtcrmandroid.model.reponsedata.SearchSalerCustomerRP;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * Created by wt-pc on 2017/6/23.
 */

public class MyClientLibraryAdapter extends BaseRecycleAdapter<SearchSalerCustomerRP, MyClientLibraryAdapter.ViewHolder> {
    private int style = 0;
    public void setStyle(int style) {
        this.style = style;
    }

    /**
     * @param context //上下文
     */
    public MyClientLibraryAdapter(Context context) {
        super(context, R.layout.item_client_library);
    }


    @Override
    protected void convert(ViewHolder holder, final SearchSalerCustomerRP bean, int position) {
        holder.tvCompanyName.setText(bean.getCompanyName());
        holder.tvCompanyAddress.setText("公司地址：" + bean.getSite());
        holder.tvTime.setText("入库时间：" + bean.getSelectTime());
        holder.tvKind.setText(bean.getCustomerKind());
        holder.tvLibraryKind.setVisibility(View.GONE);
        holder.btView.setVisibility(View.GONE);
        if (bean.getPersonalRecord().equals("SelectA")) {
            holder.tvSource.setText("主库挑选");
        } else if (bean.getPersonalRecord().equals("Hand")) {
            holder.tvSource.setText("自己添加");
        } else if (bean.getPersonalRecord().equals("Assign")) {
            holder.tvSource.setText("上级分配");
        } else {
            holder.tvSource.setText("未知来源");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (style){
                    case 0:
                        break;
                    case 1:
                        Intent intent = new Intent();
                        intent.putExtra("customername", bean.getCompanyName());
                        intent.putExtra("customerid", bean.getCustomerID());
                        MyClientLibrary activity= (MyClientLibrary) context;
                        activity.setResult(RESULT_OK, intent); // 设置结果数据
                        activity.finish(); // 关闭Activity
                        break;
                }

            }
        });

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
        @BindView(R.id.bt_view)
        Button btView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
