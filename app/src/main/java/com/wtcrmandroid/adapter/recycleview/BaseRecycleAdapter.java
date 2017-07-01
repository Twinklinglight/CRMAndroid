package com.wtcrmandroid.adapter.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wtcrmandroid.utils.L;

import java.util.List;

/**
 * Created by 1363655717 on 2017/3/23.
 */

public abstract class BaseRecycleAdapter<T,T1 extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T1> {
    private int layoutId;
    protected List <T> list;
    protected Context context;
    private OnItemClickListner onItemClickListner;//单击事件
    private OnItemLongClickListner onItemLongClickListner;//长按单击事件
    private boolean clickFlag = true;//单击事件和长单击事件的屏蔽标识

    /**
     *
     * @param context //上下文
     * @param layoutId  //布局id
     */
    public BaseRecycleAdapter(Context context, int layoutId) {
        this.layoutId = layoutId;

        this.context = context;
    }
    public void addList(List <T> list){
        this.list = list;
        notifyDataSetChanged();

    }

    public List<T> getList() {
        return list;
    }

    @Override
    public T1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layoutId, parent, false);
        final T1 holder = getViewHolder(v);
        //单击事件回调
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickFlag) {
                    if(onItemClickListner!=null)
                    onItemClickListner.onItemClickListner(v,holder.getLayoutPosition());
                }
                clickFlag = true;
            }
        });
        //单击长按事件回调
        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(onItemLongClickListner!=null)
                onItemLongClickListner.onItemLongClickListner(v,holder.getLayoutPosition());
                clickFlag = false;
                return false;
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(T1 holder, int position) {
        if(list!=null){
            convert(holder, list.get(position),position);
            L.e("youshuju");
        }
    }

    protected abstract  void convert(T1 holder, T bean, int position);

    @Override
    public int getItemCount() {
        if(list!=null) {
            return list.size();
        }
        return 5;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void setOnItemClickListner(OnItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public void setOnItemLongClickListner(OnItemLongClickListner onItemLongClickListner) {
        this.onItemLongClickListner = onItemLongClickListner;
    }


    public abstract T1 getViewHolder(View v) ;

    public interface OnItemClickListner {
        void onItemClickListner(View v, int position);
    }

    public interface OnItemLongClickListner {
        void onItemLongClickListner(View v, int position);
    }
}