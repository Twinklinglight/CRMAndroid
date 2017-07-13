package com.wtcrmandroid.adapter.listview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.model.reponsedata.HtDaysumDetailsData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxd on 2017/6/9
 */

public class HtDaysumDetailsAdapter extends MySmallBaseAdapter<HtDaysumDetailsData, HtDaysumDetailsAdapter.ViewHolder> {

    public HtDaysumDetailsAdapter(Activity activity, List<HtDaysumDetailsData> list) {
        super(activity, list);
    }

    @Override
    protected void convert(ViewHolder holder, int position) {

        HtDaysumDetailsData htDaysumDetailsData = list.get(position);
        holder.mTvWorkSort.setText(htDaysumDetailsData.getWorkSort());
        holder.mTvDaysumContent.setText(htDaysumDetailsData.getWorkContent());
        holder.mTvDaysumPerson.setText(htDaysumDetailsData.getWorkPerson());
        if (htDaysumDetailsData.getWorkUnfinishedReason() == ""||htDaysumDetailsData.getWorkUnfinishedReason() == null){
            holder.mTvDaysumWcqk.setText(htDaysumDetailsData.getWorkComplete());
        }else {
            holder.mTvDaysumWcqk.setText(htDaysumDetailsData.getWorkComplete()
                    +htDaysumDetailsData.getWorkUnfinishedReason()+" 下次完成时间"+htDaysumDetailsData.getWorkNextFinishTime());
        }
    }

    @Override
    protected View onCreateViewHolder() {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_daysum_work_details
                ,null);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    protected View onCreateNullViewholder() {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_list_null, null);
        return view;
    }

    class ViewHolder {
        @BindView(R.id.tv_work_sort)
        TextView mTvWorkSort;
        @BindView(R.id.tv_daysum_person)
        TextView mTvDaysumPerson;
        @BindView(R.id.tv_daysum_content)
        TextView mTvDaysumContent;
        @BindView(R.id.tv_daysum_wcqk)
        TextView mTvDaysumWcqk;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
