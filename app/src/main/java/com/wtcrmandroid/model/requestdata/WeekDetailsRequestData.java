package com.wtcrmandroid.model.requestdata;

/**
 * Created by ZSC on 2017/7/1.
 */

public class WeekDetailsRequestData {

    private int userId;
    private String type;
    private String isPlan;
    private String weekBegin;
    private String weekEnd;

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

    public String getIsPlan() {
        return isPlan;
    }

    public void setIsPlan(String isPlan) {
        this.isPlan = isPlan;
    }

    public String getWeekBegin() {
        return weekBegin;
    }

    public void setWeekBegin(String weekBegin) {
        this.weekBegin = weekBegin;
    }

    public String getWeekEnd() {
        return weekEnd;
    }

    public void setWeekEnd(String weekEnd) {
        this.weekEnd = weekEnd;
    }
}
