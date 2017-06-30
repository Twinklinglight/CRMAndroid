package com.wtcrmandroid.model.reponsedata;

/**
 * Created by wt-pc on 2017/6/30.
 * 客户列表接口返回数据
 */

public class SearchSalerCustomerReponseData {

    /**
     * rowid : 9
     * CustomerID : 1093340
     * DataBaseID : 1078085
     * CompanyName : 北京诚信安达物流有限公司
     * PersonalRecord : Renew
     * CustomerKind : 配货信息部
     * site : 北京市-北京市-大兴区
     * SelectTime : 2017/6/29 5:08:44
     */

    private String rowid;
    private String CustomerID;
    private String DataBaseID;
    private String CompanyName;
    private String PersonalRecord;
    private String CustomerKind;
    private String site;
    private String SelectTime;

    public String getRowid() {
        return rowid;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }

    public String getDataBaseID() {
        return DataBaseID;
    }

    public void setDataBaseID(String DataBaseID) {
        this.DataBaseID = DataBaseID;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public String getPersonalRecord() {
        return PersonalRecord;
    }

    public void setPersonalRecord(String PersonalRecord) {
        this.PersonalRecord = PersonalRecord;
    }

    public String getCustomerKind() {
        return CustomerKind;
    }

    public void setCustomerKind(String CustomerKind) {
        this.CustomerKind = CustomerKind;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSelectTime() {
        return SelectTime;
    }

    public void setSelectTime(String SelectTime) {
        this.SelectTime = SelectTime;
    }
}
