package com.wtcrmandroid.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wtcrmandroid.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ZSC on 2017/6/30.
 */

public class WeekDialog extends Dialog {

    @BindView(R.id.befor_week)
    RadioButton beforWeek;
    @BindView(R.id.now_week)
    RadioButton nowWeek;
    @BindView(R.id.next_week)
    RadioButton nextWeek;
    @BindView(R.id.last_week)
    RadioButton lastWeek;
    @BindView(R.id.rgp)
    RadioGroup rgp;
    private Context context;

    private Calendar calendar;
    private int Year,Month,Day;
    private String startDay;    //起始日期
    private String endDay;      //结束日期
    private String nowDay;      //当前日期
    private int whichday;
    private SimpleDateFormat format;
    private WeekListener listener;
    private int positon;

    public WeekDialog(@NonNull Context context,WeekListener weekListener,int position) {
        super(context, R.style.Dialog);
        this.context = context;
        this.listener = weekListener;
        this.positon = position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.dialog_week);
        initWindowParams();
        ButterKnife.bind(this);
        format = new SimpleDateFormat("yyyy.M.d");
        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH)+1;
        Day = calendar.get(Calendar.DAY_OF_MONTH);

        nowDay = format.format(calendar.getTime());
        whichday = calendar.get(Calendar.DAY_OF_WEEK);
        Log.i("-------------",nowDay+"which = "+whichday);

        nowWeek.setText(getNowWeek(whichday));
        beforWeek.setText(getShowDay(1));
        nextWeek.setText(getShowDay(2));
        lastWeek.setText(getShowDay(3));

        BackSetColor(positon);

    }

    private void BackSetColor(int tag) {
        switch (tag){
            case 1:
                beforWeek.setChecked(true);
                break;
            case 2:
                nowWeek.setChecked(true);
                break;
            case 3:
                nextWeek.setChecked(true);
                break;
            case 4:
                lastWeek.setChecked(true);
                break;
        }
    }

    public String getNowWeek(int whichday) {
        switch (whichday) {
            case 1:         //今天是周日
                calendar.set(Year,Month-1,Day);
                calendar.add(Calendar.DAY_OF_MONTH, -(6));
                startDay = format.format(calendar.getTime());
                endDay = nowDay;
                break;
            case 2:         //今天是周一
                calendar.set(Year,Month-1,Day);
                calendar.add(Calendar.DAY_OF_MONTH, +(6));
                endDay = format.format(calendar.getTime());
                startDay = nowDay;
                break;
            case 3:         //今天是周二
                calendar.set(Year,Month-1,Day);
                calendar.add(Calendar.DAY_OF_MONTH, +(5));
                endDay = format.format(calendar.getTime());
                calendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
                calendar.add(Calendar.DAY_OF_MONTH, -(1));
                startDay = format.format(calendar.getTime());
                break;
            case 4:         //今天是周三
                calendar.set(Year,Month-1,Day);
                calendar.add(Calendar.DAY_OF_MONTH, +(4));
                endDay = format.format(calendar.getTime());
                calendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
                calendar.add(Calendar.DAY_OF_MONTH, -(2));
                startDay = format.format(calendar.getTime());
                break;
            case 5:         //今天是周四
                calendar.set(Year,Month-1,Day);
                calendar.add(Calendar.DAY_OF_MONTH, +(3));
                endDay = format.format(calendar.getTime());
                calendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
                calendar.add(Calendar.DAY_OF_MONTH, -(3));
                startDay = format.format(calendar.getTime());
                break;
            case 6:         //今天是周五
                calendar.set(Year, Month-1, Day);
                calendar.add(Calendar.DAY_OF_MONTH, +(2));

                endDay = format.format(calendar.getTime());
                calendar.set(Year,Month-1,Day);
                calendar.add(Calendar.DAY_OF_MONTH, -(4));
                startDay = format.format(calendar.getTime());
                Log.i("--------","startDay = "+startDay + "   endDay = "+endDay);
                break;
            case 7:         //今天是周六
                calendar.set(Year,Month-1,Day);
                calendar.add(Calendar.DAY_OF_MONTH, +(1));
                endDay = format.format(calendar.getTime());
                calendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
                calendar.add(Calendar.DAY_OF_MONTH, -(5));
                startDay = format.format(calendar.getTime());
                break;
        }
        return startDay + "-" + endDay;
    }

    public String getShowDay(int position) {
        String backDay = "";
        switch (position) {
            case 1:
                try {
                    Date beginDate = format.parse(startDay);
                    calendar.setTime(beginDate);
                    calendar.add(Calendar.DAY_OF_MONTH, -(7));
                    String startBefore = format.format(calendar.getTime());

                    Date startDate = format.parse(endDay);
                    calendar.setTime(startDate);
                    calendar.add(Calendar.DAY_OF_MONTH, -(7));
                    String endBefore = format.format(calendar.getTime());

                    backDay = startBefore + "-" + endBefore;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    Date beginDate = format.parse(startDay);
                    calendar.setTime(beginDate);
                    calendar.add(Calendar.DAY_OF_MONTH, +(7));
                    String startBefore = format.format(calendar.getTime());

                    Date startDate = format.parse(endDay);
                    calendar.setTime(startDate);
                    calendar.add(Calendar.DAY_OF_MONTH, +(7));
                    String endBefore = format.format(calendar.getTime());

                    backDay = startBefore + "-" + endBefore;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    Date beginDate = format.parse(startDay);
                    calendar.setTime(beginDate);
                    calendar.add(Calendar.DAY_OF_MONTH, +(14));
                    String startBefore = format.format(calendar.getTime());

                    Date startDate = format.parse(endDay);
                    calendar.setTime(startDate);
                    calendar.add(Calendar.DAY_OF_MONTH, +(14));
                    String endBefore = format.format(calendar.getTime());

                    backDay = startBefore + "-" + endBefore;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
        return backDay;
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

    @OnClick({R.id.befor_week, R.id.now_week, R.id.next_week, R.id.last_week,R.id.tv_cancle})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.befor_week:
                listener.weekSelect(beforWeek.getText().toString(),1);
                dismiss();
                break;
            case R.id.now_week:
                listener.weekSelect(nowWeek.getText().toString(),2);
                dismiss();
                break;
            case R.id.next_week:
                listener.weekSelect(nextWeek.getText().toString(),3);
                dismiss();
                break;
            case R.id.last_week:
                listener.weekSelect(lastWeek.getText().toString(),4);
                dismiss();
                break;
            case R.id.tv_cancle:
                dismiss();
                break;
        }
    }

    public interface WeekListener{
        void weekSelect(String weekText,int position);
    }
}
