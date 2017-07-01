package com.wtcrmandroid.utils;

import java.util.Calendar;

/**
 * Created by wt-pc on 2017/7/1.
 */

public class DateUtils {
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
