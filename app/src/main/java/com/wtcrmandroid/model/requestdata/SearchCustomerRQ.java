package com.wtcrmandroid.model.requestdata;

/**
 * Created by wt-pc on 2017/6/26.
 */

public class SearchCustomerRQ {
    private int pageSize = 1;
    private String attribution = "WT";
    private String customerKind = "";
    private String currentStatus = "";
    private String province = "";
    private String city = "";
    private String area = "";


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
        if (currentStatus.equals("全部")) {
            this.currentStatus = "";
        } else if (currentStatus.equals("公海")) {
            this.currentStatus = "free";
        } else if (currentStatus.equals("销售库")) {
            this.currentStatus = "Sale";
        } else if (currentStatus.equals("成单库")) {
            this.currentStatus = "Order";
        } else
            this.currentStatus = "VerifyIsOne";
    }
}
