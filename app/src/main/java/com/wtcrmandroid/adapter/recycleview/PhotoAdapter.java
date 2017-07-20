package com.wtcrmandroid.adapter.recycleview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.ImageBrowsingActivity;
import com.wtcrmandroid.view.custompricing.SquareImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/7/20.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    Activity activity;
    List<String> path=new ArrayList<String>();

    public PhotoAdapter(Activity activity, List<String> path) {
        this.activity = activity;
        this.path = path;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.item_photo, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(activity).load(path.get(position)).into(holder.sivPhoto);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(activity, ImageBrowsingActivity.class)
                        .putStringArrayListExtra("path", (ArrayList<String>) path)
                        .putExtra("position",position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return path.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.siv_photo)
        SquareImageView sivPhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
