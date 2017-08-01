package com.wtcrmandroid.utils;

import android.util.Log;

import com.iflytek.cloud.thirdparty.Y;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wt-pc on 2017/7/1.
 */

public class DateUtil {
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
    public static String getToday(){
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        return df.format(new Date());

    }

    public static String getTodayString(){
        DateFormat df = new SimpleDateFormat("yyyy年M月d日");
        return df.format(new Date());

    }

    public static String getSubToaday(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date());

    }

    /**
     * 返回所选日期所在周 周一的日期
     */

    public  Date getMondey(Date selectDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(selectDate);

        int Year = calendar.get(Calendar.YEAR);
        int Month = calendar.get(Calendar.MONTH)+1;
        int Day = calendar.get(Calendar.DAY_OF_MONTH);
        int Hour = calendar.get(Calendar.HOUR_OF_DAY);
        int Minute = calendar.get(Calendar.MINUTE);
        int Second = calendar.get(Calendar.SECOND);
        int whichDay = calendar.get(Calendar.DAY_OF_WEEK);

        return getNowWeek(whichDay, Year,Month,Day,Hour,Minute,Second,calendar);
    }


    public Date getNowWeek(int whichday,int Year,int Month,int Day,int Hour,int Minute,int Second,Calendar calendar) {
        Date date = null;
        switch (whichday) {
            case 1:         //今天是周日
                calendar.set(Year,Month-1,Day,0,0,0);
                calendar.add(Calendar.DAY_OF_MONTH, -(6));
                date = calendar.getTime();
                break;
            case 2:         //今天是周一
                calendar.set(Year,Month-1,Day,0,0,0);
                calendar.add(Calendar.DAY_OF_MONTH, -(0));
                date = calendar.getTime();
                break;
            case 3:         //今天是周二
                calendar.set(Year,Month-1,Day,0,0,0);
                calendar.add(Calendar.DAY_OF_MONTH, -(1));
               date = calendar.getTime();
                break;
            case 4:         //今天是周三
                calendar.set(Year,Month-1,Day,0,0,0);
                calendar.add(Calendar.DAY_OF_MONTH, -(2));
                date = calendar.getTime();
                Log.i("--------","date = "+date.toString());
                break;
            case 5:         //今天是周四
                calendar.set(Year,Month-1,Day,0,0,0);
                calendar.add(Calendar.DAY_OF_MONTH, -(3));
                date = calendar.getTime();
                break;
            case 6:         //今天是周五
                calendar.set(Year,Month-1,Day,0,0,0);
                calendar.add(Calendar.DAY_OF_MONTH, -(4));
                date = calendar.getTime();
                Log.i("--------","date = "+date.toString());
                break;
            case 7:         //今天是周六
                calendar.set(Year,Month-1,Day,0,0,0);
                calendar.add(Calendar.DAY_OF_MONTH, -(5));
                date = calendar.getTime();
                break;
            default:
                break;
        }
        return date;
    }

    /**
     * 判断俩个日期是否是同一天
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                .get(Calendar.YEAR);
        boolean isSameMonth = isSameYear
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                .get(Calendar.DAY_OF_MONTH);

        return isSameDate;
    }

    //把字符串转为日期
    public static Date ConverToDate(String strDate) throws Exception
    {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return df.parse(strDate);
    }

    //把字符串yyyy.M.d 转化为 yyyy-MM-dd
    public static String  ConverToDate2(String strDate) throws Exception
    {
        DateFormat df = new SimpleDateFormat("yyyy.M.d");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return simpleDateFormat.format(df.parse(strDate));
    }
}
