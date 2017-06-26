package com.wtcrmandroid.model.requestdata;

/**
 * Created by wt-pc on 2017/6/26.
 */

public class SearchCustomerRequestData {
    private int pageSize=1;
    private String attribution ="WT";
    private String customerKind="";
    private String currentStatus ="";
    private String province="" ;
    private String city ="";
    private String area ="";



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
        this.customerKind = customerKind;
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

    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }
}
