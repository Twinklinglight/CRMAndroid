package com.wtcrmandroid.model.requestdata;

/**
 * Created by wt-pc on 2017/7/17.
 * 某个人的所有拜访记录
 */

public class PersonalAllRecordRQ {
    private String userId;
    private int pageIndex;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
