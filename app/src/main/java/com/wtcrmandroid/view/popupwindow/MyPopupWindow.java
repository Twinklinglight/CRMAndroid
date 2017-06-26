package com.wtcrmandroid.view.popupwindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.wtcrmandroid.R;

/**
 * Created by wt-pc on 2017/6/16.
 */

public class MyPopupWindow extends PopupWindow{
    public MyPopupWindow(View contentView, int width, int height, boolean focusable, View view) {
        super(contentView, width, height, focusable);
        showAsDropDown(view);
    }

    public MyPopupWindow(Context context, View view) {

        View contentView = LayoutInflater.from(context).inflate(R.layout.popupwindow_home, null);
        WindowManager mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        setContentView(contentView);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setFocusable(false);
        showAsDropDown(view);

    }


}
