package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wtcrmandroid.R;
import com.wtcrmandroid.view.custompricing.SquareImageView;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/6/27.
 */

public class PhotoChooseAdapter extends RecyclerView.Adapter<PhotoChooseAdapter.ViewHolder> {

    private List<String> list;
    private int size;
    private MyOnClickListner myOnClickListner;
    private Context context;
    public PhotoChooseAdapter(Context context) {
        this.context = context;
    }

    public void setMyOnClickListner(MyOnClickListner myOnClickListner) {
        this.myOnClickListner = myOnClickListner;
    }

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<String> getList() {
        return list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_choose, null);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (position == size) {
            holder.ivPhoto.setImageResource(R.mipmap.ic_photo_upload);
            holder.ivDelete.setVisibility(View.GONE);
            holder.ivPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myOnClickListner.selectPhoto(position);

                }
            });

        } else {
            Glide.with(context).load(new File(list.get(position))).into(holder.ivPhoto);
            holder.ivDelete.setVisibility(View.VISIBLE);
            holder.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myOnClickListner.deletePhoto(position);
                }
            });
            holder.ivPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
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
        @BindView(R.id.iv_photo)
        SquareImageView ivPhoto;
        @BindView(R.id.iv_delete)
        ImageView ivDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public interface MyOnClickListner{
        void selectPhoto(int position);
        void deletePhoto(int position);
    }
}
