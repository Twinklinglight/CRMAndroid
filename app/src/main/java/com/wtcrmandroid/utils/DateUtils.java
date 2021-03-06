package com.wtcrmandroid.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ZSC on 2017/7/1.
 */

public class DateUtils {
    private Calendar calendar;
    private int Year,Month,Day;
    private String startDay;    //起始日期
    private String endDay;      //结束日期
    private String nowDay;      //当前日期
    private int whichday;
    private SimpleDateFormat format;

    private String StartTime;
    private String EndTime;

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
            default:
                StartTime = startDay;
                EndTime = endDay;
                break;
        }
        return startDay + "-" + endDay;
    }

    public String getWantDate(String daytime){
        String dateTime= "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateTime = simpleDateFormat.format(format.parse(daytime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime;
    }
}
