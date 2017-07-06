package com.wtcrmandroid.model.requestdata;

/**
 * Created by wt-pc on 2017/6/26.
 * 客户列表
 */

public class SearchSalerCustomerRQ {
    private int userId;
    private int pageSize=1;
    private String customerKind="";
    private String personalRecord="";
    private String province="" ;
    private String city ="";
    private String area ="";

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getCustomerKind() {
        return customerKind;
    }

    public void setCustomerKind(String customerKind) {
        if (customerKind.equals("全部")) {
            this.customerKind = "";
        } else
            this.customerKind = customerKind;
    }

    public String getPersonalRecord() {
        return personalRecord;
    }

    public void setPersonalRecord(String personalRecord) {
        if(personalRecord.equals("全部")) {
            this.personalRecord = "";
        }else if(personalRecord.equals("主库挑选")){
            this.personalRecord = "SelectA";
        }else if(personalRecord.equals("自己添加")){
            this.personalRecord = "Hand";
        }else
            this.personalRecord = "Assign";
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
