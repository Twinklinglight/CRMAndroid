package com.wtcrmandroid.model.reponsedata;

/**
 * Created by wt-pc on 2017/7/17.
 * 拜访统计
 */

public class VisitStatisticalRP {

    /**
     * rowid : 1
     * userId : 2627
     * userName : 贾信河
     * roleName : 物通董事长
     * visitCount : 4
     */

    private String rowid;
    private String userId;
    private String userName;
    private String roleName;
    private String visitCount;

    public String getRowid() {
        return rowid;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(String visitCount) {
        this.visitCount = visitCount;
    }
}
