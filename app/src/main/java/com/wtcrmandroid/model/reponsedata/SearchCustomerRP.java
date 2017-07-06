package com.wtcrmandroid.model.reponsedata;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wt-pc on 2017/6/26.
 */

public class SearchCustomerRP {

    /**
     * site : 河北省-邯郸市-市辖区
     * Attribution : WT
     * DataBaseID : 1879941
     * CustomerKind : 配货信息部
     * CustomerID : 1987196
     * CurrentStatus : Sale
     * RecordTime : 2017/6/26 16:38:55
     * CompanyName : 1583105
     * rowid : 1
     */
    @SerializedName("site")
    private String site;
    private String Attribution;
    private String DataBaseID;
    private String CustomerKind;
    private String CustomerID;
    private String CurrentStatus;
    private String RecordTime;
    private String CompanyName;
    private String rowid;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getAttribution() {
        return Attribution;
    }

    public void setAttribution(String Attribution) {
        this.Attribution = Attribution;
    }

    public String getDataBaseID() {
        return DataBaseID;
    }

    public void setDataBaseID(String DataBaseID) {
        this.DataBaseID = DataBaseID;
    }

    public String getCustomerKind() {
        return CustomerKind;
    }

    public void setCustomerKind(String CustomerKind) {
        this.CustomerKind = CustomerKind;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }

    public String getCurrentStatus() {
        return CurrentStatus;
    }

    public void setCurrentStatus(String CurrentStatus) {
        this.CurrentStatus = CurrentStatus;
    }

    public String getRecordTime() {
        return RecordTime;
    }

    public void setRecordTime(String RecordTime) {
        this.RecordTime = RecordTime;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public String getRowid() {
        return rowid;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid;
    }
}
