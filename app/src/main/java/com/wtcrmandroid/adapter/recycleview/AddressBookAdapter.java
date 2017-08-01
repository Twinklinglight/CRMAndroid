package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.thinkcool.circletextimageview.CircleTextImageView;
import com.wtcrmandroid.R;
import com.wtcrmandroid.model.reponsedata.ContactRP;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/6/21.
 * 通讯录适配器
 */

public class AddressBookAdapter extends RecyclerView.Adapter<AddressBookAdapter.ViewHolder> {


    private Context context;
    private List<ContactRP> mlist;
    private ContactListener listener;

    public AddressBookAdapter(Context context) {
        this.context = context;
    }

    public ContactListener getListener() {
        return listener;
    }

    public void setListener(ContactListener listener) {
        this.listener = listener;
    }

    public void setMlist(List<ContactRP> mlist) {
        this.mlist = mlist;
    }


    @Override
    public AddressBookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(context).inflate(R.layout.item_contacts_child, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AddressBookAdapter.ViewHolder holder, final int position) {

        ViewHolder mholder = holder;
        ContactRP contactRP = mlist.get(position);
        String headerimg = contactRP.getHeaderimg();

        holder.ivHead.setText("");              //文字复用
        holder.ivHead.setBackgroundResource(0); //图片复用
        holder.ivHead.setImageResource(0);

        if (headerimg.length() == 0){

            Glide.clear(holder.ivHead);         //不再加载

            mholder.ivHead.setText(contactRP.getUserName()
                    .substring(contactRP.getUserName().length()-2,
                            contactRP.getUserName().length()));
            mholder.ivHead.setFillColorResource(R.color.colorPrimary);
        }else {
            Glide.with(context).load(headerimg)
                    .into(holder.ivHead);
        }

        mholder.tvJob.setText(contactRP.getRoleName());
        mholder.tvName.setText(contactRP.getUserName());
        mholder.llCOntact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.itemClick(position);
            }
        });

    }

    //view的回收方法

    @Override
    public void onViewRecycled(AddressBookAdapter.ViewHolder holder) {
        super.onViewRecycled(holder);

        Glide.clear(holder.ivHead); //当view被回收时，取消加载；
    }

    @Override
    public int getItemCount() {

        return mlist == null ? 0 : mlist.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.ll_contact)
        LinearLayout llCOntact;

        @BindView(R.id.iv_head)
        CircleTextImageView ivHead;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_department)
        TextView tvJob;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ContactListener{
        void itemClick(int position);

    }
}
