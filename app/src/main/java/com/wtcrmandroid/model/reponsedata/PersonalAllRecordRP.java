package com.wtcrmandroid.model.reponsedata;

import java.io.Serializable;

/**
 * Created by wt-pc on 2017/7/17.
 * 某个人的所有拜访记录
 */

public class PersonalAllRecordRP implements Serializable {

    /**
     * rowid : 1
     * customerid : 1997661
     * customerName : 徐先生
     * lat : 113.706292
     * lng : 34.754151
     * addressDetail : 在恒泰国际附近不了
     * remarks :
     * createTime : 2017/7/14 20:00:14
     * address :
     */

    private String rowid;
    private String customerid;
    private String customerName;
    private Double lat;
    private Double lng;
    private String addressDetail;
    private String remarks;
    private String createTime;
    private String address="";

    public String getRowid() {
        return rowid;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
