package com.wtcrmandroid.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.wtcrmandroid.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 日历弹窗对话框
 * Created by ZSC on 2017/6/27.
 */

public class CalendarDialog extends Dialog {

    @BindView(R.id.calender)
    MaterialCalendarView calender;
    @BindView(R.id.bt_sure)
    Button btSure;
    @BindView(R.id.bt_cancle)
    Button btCancle;
    private Context context;
    private CalendarListener listener;
    String DateTime;
    Date data;
    private SimpleDateFormat simpleDateFormat;

    public CalendarDialog(Context context, CalendarListener mlistener) {
        super(context, R.style.Dialog);
        this.context = context;
        this.listener = mlistener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.dialog_calendar_select);
        initWindowParams();
        ButterKnife.bind(this);

        Calendar instance = Calendar.getInstance();

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd EEEE");
        DateTime = simpleDateFormat.format(instance.getTime());
        data=instance.getTime();
        calender.setSelectedDate(instance.getTime());
        calender.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                if (selected) {
                    DateTime = simpleDateFormat.format(widget.getSelectedDate().getDate());
                    data=date.getDate();
                }
            }
        });

        //确定
        btSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.CalendarSelcet(DateTime,data);
                dismiss();
            }
        });

        //取消
        btCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


    }

    private void initWindowParams() {
        Window dialogWindow = getWindow();
        // 获取屏幕宽、高用
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density1 = dm.density;
        int width3 = dm.widthPixels;

        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (width3 * 0.85); // 宽度设置为屏幕的0.85

        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setAttributes(lp);
    }

    //日期回调接口
    public interface CalendarListener {

        void CalendarSelcet(String datetext, Date date);
    }
}
