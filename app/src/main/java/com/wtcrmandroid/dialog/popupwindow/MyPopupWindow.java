package com.wtcrmandroid.dialog.popupwindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.wtcrmandroid.R;

/**
 * Created by wt-pc on 2017/6/16.
 */

public class MyPopupWindow extends PopupWindow{
    public MyPopupWindow(Context context) {
        super(context);
        View contentView = LayoutInflater.from(context).inflate(R.layout.popupwindow_home, null);
        setContentView(contentView);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);

    }


}
