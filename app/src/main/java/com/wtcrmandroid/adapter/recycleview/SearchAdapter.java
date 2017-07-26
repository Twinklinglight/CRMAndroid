package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.model.reponsedata.DepartmentRponseData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxd on 2017/7/24.
 */

public class SearchAdapter extends BaseRecycleAdapter<DepartmentRponseData, SearchAdapter.ViewHolder>{

    private ClickListener clickListener;
    public SearchAdapter(Context context,ClickListener listener) {
        super(context, R.layout.item_search_name);
        this.clickListener = listener;
    }

    public void setList(List<DepartmentRponseData> data){
        addList(data);
    }

    @Override
    protected void convert(ViewHolder holder, final DepartmentRponseData bean, final int position) {
        holder.tvSearchName.setText(bean.getUsername());
        holder.tvSearchName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.itemClick(bean.getRoleclass(),bean.getUserid());
            }
        });
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }


    public interface ClickListener{
        void itemClick(int rollcalss,int userId);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_search_name)
        TextView tvSearchName;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
