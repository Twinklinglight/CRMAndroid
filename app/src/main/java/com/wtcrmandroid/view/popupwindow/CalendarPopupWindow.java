package com.wtcrmandroid.view.popupwindow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.wtcrmandroid.R;
import com.wtcrmandroid.view.dialog.CalendarDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/7/4.
 * 日期选择
 */

public class CalendarPopupWindow extends PopupWindow {

    private View view;
    String DateTime;
    Date data;
    private SimpleDateFormat simpleDateFormat;

    private CalendarDialog.CalendarListener listener;
    public CalendarPopupWindow(Context context, View view,CalendarDialog.CalendarListener mlistener) {
        this.listener = mlistener;
        View contentView = LayoutInflater.from(context).inflate(R.layout.popupwindow_calendar_select, null);
        ViewHolder holder = new ViewHolder(contentView);
        WindowManager mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        setContentView(contentView);
        Calendar instance = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        DateTime = simpleDateFormat.format(instance.getTime());
        data=instance.getTime();
        holder.calender.setSelectedDate(instance.getTime());
        holder.calender.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                if (selected) {
                    DateTime = simpleDateFormat.format(widget.getSelectedDate().getDate());
                    data=date.getDate();
                }
            }
        });

        //确定
        holder.btSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.CalendarSelcet(DateTime,data);
                dismiss();
            }
        });

        //取消
        holder.btCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        int[] locations = new int[2];
        view.getLocationOnScreen(locations);
        int screenHeight = context.getApplicationContext().getResources().getDisplayMetrics().heightPixels;
        int height = screenHeight - view.getHeight() - locations[1];
        setHeight(height);
        setFocusable(false);
        this.view = view;

    }
    public void show() {
        showAsDropDown(view);
    }

    static class ViewHolder {
        @BindView(R.id.calender)
        MaterialCalendarView calender;
        @BindView(R.id.bt_sure)
        Button btSure;
        @BindView(R.id.bt_cancle)
        Button btCancle;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
