package com.wtcrmandroid.adapter.recycleview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.view.custompricing.SquareImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/6/27.
 */

public class PhotoChooseAdapter extends RecyclerView.Adapter<PhotoChooseAdapter.ViewHolder> {

    private List<String> list;
    private int size;

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<String> getList() {
        return list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_ppcustomer, null);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position == size) {
            holder.ivPhoto.setImageResource(R.mipmap.ic_photo_upload);
            holder.ivDelete.setVisibility(View.GONE);
        }
        else{
//            holder.ivPhoto.setImageResource(R.mipmap.ic_dog);
            holder.ivDelete.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        size = list.size();
        if (size < 5)
            return size + 1;
        else return 5;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.iv_photo)
        SquareImageView ivPhoto;
        @BindView(R.id.iv_delete)
        ImageView ivDelete;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
