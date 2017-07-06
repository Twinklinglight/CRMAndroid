package com.wtcrmandroid.model.reponsedata;

/**
 * Created by wt-pc on 2017/7/5.
 * 外勤统计
 */

public class FieldStatisticsRP {


    /**
     * userId : 3066
     * deptName : 技术移动事业部
     * userName : 申中佳
     * SignCount : 3
     */

    private String userId;
    private String deptName;
    private String userName;
    private String SignCount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSignCount() {
        return SignCount;
    }

    public void setSignCount(String SignCount) {
        this.SignCount = SignCount;
    }
}
