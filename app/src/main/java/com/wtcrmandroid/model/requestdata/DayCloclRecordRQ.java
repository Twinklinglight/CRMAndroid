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

    private String userId;
    private String ToDay;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToDay() {
        return ToDay;
    }

    public void setToDay(String ToDay) {
        this.ToDay = ToDay;
    }
}
