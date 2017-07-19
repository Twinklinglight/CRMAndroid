package com.wtcrmandroid.model.requestdata;

/**
 * Created by wt-pc on 2017/7/17.
 * 拜访统计
 */

public class VisitStatisticalRQ {

    /**
     * userId : 2627
     * departmentId : 0
     * date : 2017-07-14
     * pageindex : 1
     */

    private String userId;
    private String departmentId;
    private String date;
    private int pageindex;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPageindex() {
        return pageindex;
    }

    public void setPageindex(int pageindex) {
        this.pageindex = pageindex;
    }
}
