package com.wtcrmandroid.model.reponsedata;

/**
 * Created by wt-pc on 2017/7/14.
 * 某公司拜访记录
 */

public class CompanyVisitDetailsRP {


    /**
     * rowid : 1
     * userid : 2627
     * customerId : 1986948
     * customerName : 高照明
     * lat : 0
     * lng : 0
     * addressDetail : 山东省临沂市市辖区
     * remarks : 测试数据1
     * createTime : 2017/7/12 10:09:44
     * path :
     */

    private String rowid;
    private String userid;
    private String customerId;
    private String customerName;
    private String lat;
    private String lng;
    private String addressDetail;
    private String remarks;
    private String createTime;
    private String path;

    public String getRowid() {
        return rowid;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
