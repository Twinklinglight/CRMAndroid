package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.iflytek.cloud.thirdparty.V;
import com.wtcrmandroid.R;
import com.wtcrmandroid.model.reponsedata.DocumentListRpData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxd on 2017/7/5.
 */

public class DocumentDealAdapter extends BaseRecycleAdapter<DocumentListRpData, DocumentDealAdapter.ViewHolder> {


    private UpdateListener listener;

    private int style = 0;  //状态标识，0 通过审批；1 未通过审批

    public void setStyle(int style) {
        this.style = style;
    }
    /**
     * @param context //上下文
     */
    public DocumentDealAdapter(Context context, List<DocumentListRpData> list) {
        super(context, R.layout.item_document_deal);
        addList(list);
    }

    @Override
    protected void convert(ViewHolder holder, DocumentListRpData bean, int position) {

        DocumentListRpData documentListRpData = list.get(position);
        holder.tvTitle.setText(documentListRpData.getTitle());
        holder.tvName.setText("申请人:"+documentListRpData.getName());
        holder.tvDate.setText("发表于:"+documentListRpData.getDate());
        holder.tvState.setText(documentListRpData.getState());

        switch (style){
            case 0:
                holder.tvUpdate.setVisibility(View.GONE);
                break;
            case 1:
                holder.tvUpdate.setVisibility(View.VISIBLE);
                holder.tvUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null)
                        listener.updateClick();
                    }
                });
        }

    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return null;
    }

    //需要点击修改按钮时，要先设置listener
    public void  setListener(UpdateListener mListener){
        this.listener = mListener;
    }


    public interface UpdateListener{
        void updateClick();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_update)
        TextView tvUpdate;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
