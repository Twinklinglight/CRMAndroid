package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.model.reponsedata.HtDaysumDetailsData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxd on 2017/7/19.
 */

public class HtDaysumDetailsRcAdapter extends BaseRecycleAdapter<HtDaysumDetailsData, HtDaysumDetailsRcAdapter.ViewHolder> {
    /**
     * @param context  //上下文
     */

    public HtDaysumDetailsRcAdapter(Context context,List<HtDaysumDetailsData> list) {

        super(context, R.layout.item_daysum_work_details);
        addList(list);
    }

    @Override
    protected void convert(ViewHolder holder, HtDaysumDetailsData bean, int position) {

        HtDaysumDetailsData htDaysumDetailsData = list.get(position);
        holder.tvWorkSort.setText(htDaysumDetailsData.getWorkSort());
        holder.tvDaysumContent.setText(htDaysumDetailsData.getWorkContent());
        holder.tvDaysumPerson.setText(htDaysumDetailsData.getWorkPerson());
        if (htDaysumDetailsData.getWorkUnfinishedReason() == ""||htDaysumDetailsData.getWorkUnfinishedReason() == null){
            holder.tvDaysumWcqk.setText(htDaysumDetailsData.getWorkComplete());
        }else {
            holder.tvDaysumWcqk.setText(htDaysumDetailsData.getWorkComplete()
                    +htDaysumDetailsData.getWorkUnfinishedReason()+" 下次完成时间"+htDaysumDetailsData.getWorkNextFinishTime());
        }
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_work_sort)
        TextView tvWorkSort;
        @BindView(R.id.tv_daysum_person)
        TextView tvDaysumPerson;
        @BindView(R.id.tv_daysum_content)
        TextView tvDaysumContent;
        @BindView(R.id.tv_daysum_wcqk)
        TextView tvDaysumWcqk;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
