package com.wtcrmandroid.model.reponsedata;

/**
 * Created by wt-pc on 2017/7/19.
 * 我的地推客户和完善客户实体类
 */

public class PushCustomerRP {

    /**
     * Verify : 2
     * id : 1737725
     * cust_kind : 货源提供商
     * time : 2016-05-17T17:52:16.813
     * rowid : 1
     * comName : null
     * shiMingState : 0
     */

    private int Verify;
    private int id;
    private String cust_kind;
    private String time;
    private int rowid;
    private String  comName;
    private int shiMingState;

    public int getVerify() {
        return Verify;
    }

    public void setVerify(int Verify) {
        this.Verify = Verify;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCust_kind() {
        return cust_kind;
    }

    public void setCust_kind(String cust_kind) {
        this.cust_kind = cust_kind;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getRowid() {
        return rowid;
    }

    public void setRowid(int rowid) {
        this.rowid = rowid;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public int getShiMingState() {
        return shiMingState;
    }

    public void setShiMingState(int shiMingState) {
        this.shiMingState = shiMingState;
    }
}
