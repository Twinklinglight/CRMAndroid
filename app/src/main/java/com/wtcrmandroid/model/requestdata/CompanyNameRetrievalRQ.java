package com.wtcrmandroid.model.requestdata;

/**
 * Created by wt-pc on 2017/6/30.
 * 公司名称检索
 */

public class CompanyNameRetrievalRQ {
    private int pageSize = 1;
    private String attribution = "WT";
    private String companyName ;
    private int userId;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
