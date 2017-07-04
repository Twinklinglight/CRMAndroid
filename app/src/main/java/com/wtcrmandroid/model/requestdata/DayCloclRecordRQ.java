package com.wtcrmandroid.model.requestdata;

/**
 * Created by wt-pc on 2017/7/3.
 * 天的打卡记录
 */

public class DayCloclRecordRQ {

    /**
     * userId : 3066
     * ToDay : 2017/07/03
     */

    private int userId;
    private String ToDay;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToDay() {
        return ToDay;
    }

    public void setToDay(String ToDay) {
        this.ToDay = ToDay;
    }
}
