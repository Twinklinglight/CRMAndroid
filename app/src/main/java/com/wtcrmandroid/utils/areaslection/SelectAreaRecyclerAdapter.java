package com.wtcrmandroid.utils.areaslection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wtcrmandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 适配器，根据参数whichToShow选择将要显示的地区等级(0-省；1-市；2-县)
 * Created by Mr-Zhang on 2016/4/6.
 */
public class SelectAreaRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int SHOW_PROVINCE = 0;
    public static final int SHOW_CITY = 1;
    public static final int SHOW_TOWN = 2;
    private List<Area> areas;
    private int whichToShow = 0;
    private Context context;
    private OnAreaClickListener onAreaClickListener;
    private ArrayList<String> areaStringList;

    public interface OnAreaClickListener{
        void onAreaClick(Area area, int whichToShow);
    }

    public void setOnAreaClickListener(OnAreaClickListener onAreaClickListener) {
        this.onAreaClickListener = onAreaClickListener;
    }

    public SelectAreaRecyclerAdapter(List<Area> areas, Context context) {
        this.areas = areas;
        this.context = context;
        initStringList();
    }

    public SelectAreaRecyclerAdapter(List<Area> areas, int whichToShow, Context context) {
        this.areas = areas;
        this.whichToShow = whichToShow;
        this.context = context;
        initStringList();
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public void initStringList() {
        int size=areas.size();
        areaStringList=new ArrayList<>();
        switch (whichToShow){
            case SHOW_PROVINCE:
                for (int i=0;i<size;i++){
                    String areaString=areas.get(i).getSheng();
                    areaStringList.add(areaString);
                }
                break;
            case SHOW_CITY:
                for (int i=0;i<size;i++){
                    String areaString=areas.get(i).getShi();
                    areaStringList.add(areaString);
                }
                break;
            case SHOW_TOWN:
                for (int i=0;i<size;i++){
                    String areaString=areas.get(i).getXian();
                    areaStringList.add(areaString);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AreaViewHolder areaViewHolder = new AreaViewHolder
                (LayoutInflater.from(context).
                        inflate(R.layout.item_recyclerview_select_area, parent, false));
        return areaViewHolder;
    }

    public void setWhichToShow(int whichToShow) {
        this.whichToShow = whichToShow;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        AreaViewHolder areaViewHolder=(AreaViewHolder) holder;
        areaViewHolder.tvArea.setText(areaStringList.get(position));
        areaViewHolder.tvArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAreaClickListener.onAreaClick(areas.get(position),whichToShow);
            }
        });
    }

    @Override
    public int getItemCount() {
        return areaStringList.size();
    }

    class AreaViewHolder extends RecyclerView.ViewHolder {
        TextView tvArea;

        public AreaViewHolder(View itemView) {
            super(itemView);
            tvArea = (TextView) itemView.findViewById(R.id.btn_item_area);
        }
    }
}
