package com.wtcrmandroid.model.requestdata;

/**
 * Created by wt-pc on 2017/7/19.
 * 我的地图客户和完善客户实体类
 */

public class PushCustomerRQ {
    private int pageIndex;
    private int isDiTui;
    private String userId;
    private String comName;
    private String startTime ;
    private String endTime;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getIsDiTui() {
        return isDiTui;
    }

    public void setIsDiTui(int isDiTui) {
        this.isDiTui = isDiTui;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
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
