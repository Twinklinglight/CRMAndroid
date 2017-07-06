package com.wtcrmandroid.model.requestdata;

/**
 * Created by zxd on 2017/7/3.
 */

public class MyJournalRequestData {

    private int userId;
    private String type;
    private String toDay;
    private int weekIndex;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToDay() {
        return toDay;
    }

    public void setToDay(String toDay) {
        this.toDay = toDay;
    }

    public int getWeekIndex() {
        return weekIndex;
    }

    public void setWeekIndex(int weekIndex) {
        this.weekIndex = weekIndex;
    }
}
