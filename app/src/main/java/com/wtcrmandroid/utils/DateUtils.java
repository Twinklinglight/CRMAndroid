package com.wtcrmandroid.utils;

import java.util.Calendar;

/**
 * Created by wt-pc on 2017/7/1.
 */

public class DateUtils {

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ZSC on 2017/6/30.
 */

    private Calendar calendar;
    private int Year,Month,Day;
    private String startDay;    //起始日期
    private String endDay;      //结束日期
    private String nowDay;      //当前日期
    private int whichday;
    private SimpleDateFormat format;

    public DateUtils() {
        calendar = Calendar.getInstance();
        format = new SimpleDateFormat("yyyy.M.d");
        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH)+1;
        Day = calendar.get(Calendar.DAY_OF_MONTH);

        nowDay = format.format(calendar.getTime());
        whichday = calendar.get(Calendar.DAY_OF_WEEK);
    }

    public String getNowWeek() {
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
                calendar.set(Year,Month-1,Day);
                calendar.add(Calendar.DAY_OF_MONTH, -(1));
                startDay = format.format(calendar.getTime());
                break;
            case 4:         //今天是周三
                calendar.set(Year,Month-1,Day);
                calendar.add(Calendar.DAY_OF_MONTH, +(4));
                endDay = format.format(calendar.getTime());
                calendar.set(Year,Month-1,Day);
                calendar.add(Calendar.DAY_OF_MONTH, -(2));
                startDay = format.format(calendar.getTime());
                break;
            case 5:         //今天是周四
                calendar.set(Year,Month-1,Day);
                calendar.add(Calendar.DAY_OF_MONTH, +(3));
                endDay = format.format(calendar.getTime());
                calendar.set(Year,Month-1,Day);
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
                calendar.set(Year,Month-1,Day);
                calendar.add(Calendar.DAY_OF_MONTH, -(5));
                startDay = format.format(calendar.getTime());
                break;
        }
        return startDay + "-" + endDay;
    }
    /**
     * 获取当月的 天数
     * */
    public static int getCurrentMonthDay() {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }
}
