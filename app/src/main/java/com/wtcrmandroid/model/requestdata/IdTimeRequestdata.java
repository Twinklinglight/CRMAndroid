package com.wtcrmandroid.model.requestdata;

/**
 * Created by wt-pc on 2017/7/1.
 */

public class IdTimeRequestdata {
    private int userId;
    private String startTime;
    private String endTime;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
