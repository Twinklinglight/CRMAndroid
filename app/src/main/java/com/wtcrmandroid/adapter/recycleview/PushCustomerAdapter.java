package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.model.reponsedata.PushCustomerRP;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/6/23.
 * 我的地推客户和完善客户
 */

public class PushCustomerAdapter extends BaseRecycleAdapter<PushCustomerRP, PushCustomerAdapter.ViewHolder> {
    private int style = 0;
    public void setStyle(int style) {
        this.style = style;
    }

    /**
     * @param context //上下文
     */
    public PushCustomerAdapter(Context context) {
        super(context, R.layout.item_client_library);
    }


    @Override
    protected void convert(ViewHolder holder, final PushCustomerRP bean, int position) {
        holder.tvCompanyName.setText(bean.getComName());
        holder.tvCompanyAddress.setVisibility(View.GONE);
        holder.tvTime.setText("注册时间：" + bean.getTime());
        holder.tvKind.setText(bean.getCust_kind());
        holder.tvLibraryKind.setVisibility(View.GONE);
        holder.btView.setVisibility(View.GONE);
        switch (bean.getVerify()){
            case 0:
                holder.tvSource.setText("初始状态");
                break;
            case 1:
                holder.tvSource.setText("等待初审");
                break;
            case 2:
                holder.tvSource.setText("初审不通");
                break;
            case 3:
                holder.tvSource.setText("等待复审");
                break;
            case 4:
                holder.tvSource.setText("复审不通过");
                break;
            case 5:
                holder.tvSource.setText("复审通过");
                break;
        }
        holder.tvSource.setBackgroundResource(R.drawable.shape_rounded_rectangle5_orange_line_bg);
        holder.tvSource.setTextColor(Color.parseColor("#F76129"));
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (style){
//                    case 0:
//                        break;
//                    case 1:
//                        Intent intent = new Intent();
//                        intent.putExtra("customername", bean.getCompanyName());
//                        intent.putExtra("customerid", bean.getCustomerID());
//                        MyClientLibrary activity= (MyClientLibrary) context;
//                        activity.setResult(RESULT_OK, intent); // 设置结果数据
//                        activity.finish(); // 关闭Activity
//                        break;
//                }
//
//            }
//        });

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
