package com.wtcrmandroid.model.reponsedata;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wt-pc on 2017/7/17.
 * 某个人的所有拜访记录
 */

public class PersonalAllRecordRP implements Serializable {


    /**
     * customerName : ???
     * createTime : 2017/7/19 9:57:31
     * customerid : 1997630
     * address :
     * path : ["http://crm.chinawutong.com/uploadfiles/appimg/26271500033509487.jpg","http://crm.chinawutong.com/uploadfiles/appimg/26271500371731529.jpg","http://crm.chinawutong.com/uploadfiles/appimg/26271500371943994.jpg"]
     * remarks :
     * lng : 34.754166
     * addressDetail : ???????????
     * rowid : 1
     * lat : 113.706276
     */

    private String customerName;
    private String createTime;
    private String customerid;
    private String address="";
    private String remarks;
    private Double lng;
    private String addressDetail;
    private String rowid;
    private Double lat;
    private List<String> path;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public String getRowid() {
        return rowid;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }
}
