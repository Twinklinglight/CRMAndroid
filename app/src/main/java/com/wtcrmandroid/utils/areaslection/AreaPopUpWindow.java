package com.wtcrmandroid.utils.areaslection;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wtcrmandroid.R;

import java.util.List;

/**
 * Created by Mr-Zhang on 2016/4/27.
 */
public class AreaPopUpWindow {
    private View parentView;
    private Context context;
    private RecyclerView recyclerView;
    private SelectAreaRecyclerAdapter mAdapter;
    private List<Area> areas;
    private TextView tvSelectedArea;
    private TextView tvBackUp;
    public PopupWindow popupWindow;
    private IAreaModule areaModule;
    private SelectAreaListener selectAreaListener;
    private Area areaProvince;
    private int toShow;

    public interface SelectAreaListener {
        void selectAreaOk(Area area, View parentView);
    }

    public AreaPopUpWindow(Context context, View parentView) {
        this.context = context;
        this.parentView = parentView;
        areaModule = new AreaImpl();
        initData();
        initPopUpWindow();
    }

    private void initData() {
        if (areas != null) {
            areas.clear();
        }
        areas = areaModule.getAllProvince();
        mAdapter = new SelectAreaRecyclerAdapter(areas, context);
        itemClick();
    }

    public void setSelectAreaListener(SelectAreaListener selectAreaListener) {
        this.selectAreaListener = selectAreaListener;
    }

    private void initPopUpWindow() {
        View contentView = LayoutInflater.from(context).inflate(R.layout.layout_area_pop_up_window, null);
        recyclerView = (RecyclerView) contentView.findViewById(R.id.rv_area_pop_window);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 4));
        recyclerView.setAdapter(mAdapter);
        tvSelectedArea = (TextView) contentView.findViewById(R.id.tv_area_pop_window_selected);
        tvBackUp = (TextView) contentView.findViewById(R.id.tv_area_pop_window_backup);
        backUp();
        tvBackUp.setText("全国");
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);

//        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                return false;
//            }
//        });
        popupWindow.setBackgroundDrawable(context.getResources().getDrawable(R.color.black_alpha));
    }

    public void showPopWindow(View parentView) {
        this.parentView = parentView;
        popupWindow.showAsDropDown(parentView);
    }

    public void disMissPopWindow() {
        if (popupWindow != null) {
            popupWindow.dismiss();
            initData();
            initPopUpWindow();
        }
    }

    private void itemClick() {
        mAdapter.setOnAreaClickListener(new SelectAreaRecyclerAdapter.OnAreaClickListener() {
            @Override
            public void onAreaClick(Area area, int whichToShow) {

                switch (whichToShow) {
                    case SelectAreaRecyclerAdapter.SHOW_PROVINCE:
                        showCity(area);
                        tvBackUp.setText("返回上一级");
                        toShow = SelectAreaRecyclerAdapter.SHOW_CITY;
                        break;
                    case SelectAreaRecyclerAdapter.SHOW_CITY:
                        showTown(area);
                        toShow = SelectAreaRecyclerAdapter.SHOW_TOWN;
                        break;
                    case SelectAreaRecyclerAdapter.SHOW_TOWN:
                        selectAreaListener.selectAreaOk(area, parentView);
                        disMissPopWindow();
                        toShow = SelectAreaRecyclerAdapter.SHOW_PROVINCE;
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void backUp() {
        tvBackUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (toShow) {
                    case SelectAreaRecyclerAdapter.SHOW_PROVINCE:
                        Area area=new Area();
                        area.setSheng("");
                        area.setShi("");
                        area.setXian("");
                        selectAreaListener.selectAreaOk(area, parentView);
                        disMissPopWindow();

                        break;
                    case SelectAreaRecyclerAdapter.SHOW_CITY:
                        showProvince();
                        tvBackUp.setText("全国");
                        toShow = SelectAreaRecyclerAdapter.SHOW_PROVINCE;
                        break;
                    case SelectAreaRecyclerAdapter.SHOW_TOWN:
                        showCity(areaProvince);
                        toShow=SelectAreaRecyclerAdapter.SHOW_CITY;
                        break;
                }
            }
        });
    }

    private void showCity(Area area) {
        areaProvince = area;
        tvSelectedArea.setText(area.getSheng());
        areas.clear();
        areas = areaModule.selectByProvince(area.getSheng());
        mAdapter.setWhichToShow(SelectAreaRecyclerAdapter.SHOW_CITY);
        mAdapter.setAreas(areas);
        mAdapter.initStringList();
        mAdapter.notifyDataSetChanged();
    }

    private void showTown(Area area) {
        tvSelectedArea.setText(area.getShi());
        areas.clear();
        areas = areaModule.selectByCity(area.getSheng(), area.getShi());
        mAdapter.setWhichToShow(SelectAreaRecyclerAdapter.SHOW_TOWN);
        mAdapter.setAreas(areas);
        mAdapter.initStringList();
        mAdapter.notifyDataSetChanged();
    }

    private void showProvince() {
        areas.clear();
        areas = areaModule.getAllProvince();
        mAdapter.setWhichToShow(SelectAreaRecyclerAdapter.SHOW_PROVINCE);
        mAdapter.setAreas(areas);
        mAdapter.initStringList();
        mAdapter.notifyDataSetChanged();
    }
}
