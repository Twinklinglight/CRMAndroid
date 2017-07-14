package com.wtcrmandroid.model.requestdata;

/**
 * Created by wt-pc on 2017/7/14.
 * 某公司拜访记录
 */

public class CompanyVisitDetailsRQ {

    /**
     * customerId : 1986948
     * pageIndex : 1
     */

    private String customerId;
    private int pageIndex;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
