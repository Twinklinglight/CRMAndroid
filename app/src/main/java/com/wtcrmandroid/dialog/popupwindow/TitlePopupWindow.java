package com.wtcrmandroid.dialog.popupwindow;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.recycleview.PoppupWindowTitleAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/6/16.
 */

public class TitlePopupWindow extends PopupWindow {

    private View view;
    PoppupWindowTitleAdapter adapter;

    public TitlePopupWindow(Context context, View view, List list, int style, int isCheckposition, PoppupWindowTitleAdapter.oNclicklistner onclicklistner) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.popupwindow_title, null);
        ViewHolder holder = new ViewHolder(contentView);
        holder.rvView.setLayoutManager(new LinearLayoutManager(context));
        holder.rvView.setAdapter(adapter = new PoppupWindowTitleAdapter(context, style, isCheckposition, onclicklistner));
        holder.cancleOnlick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        adapter.addList(list);
        WindowManager mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        setContentView(contentView);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setFocusable(false);
        this.view = view;


    }

    public void show() {
        showAsDropDown(view);
    }

    static class ViewHolder {
        @BindView(R.id.rv_view)
        RecyclerView rvView;
        @BindView(R.id.cancle_onlick)
        LinearLayout cancleOnlick;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}