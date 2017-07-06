package com.wtcrmandroid.model.requestdata;

/**
 * Created by wt-pc on 2017/7/4.
 * 外勤统计详情
 */

public class FieldStatisticsDetailsRQ {


    /**
     * pageSize : 1
     * createTime : 2017/6/27
     * userId : 3066
     */

    private String pageSize;
    private String createTime;
    private String userId;

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
