package com.wtcrmandroid.model.requestdata;

/**
 * Created by wt-pc on 2017/6/26.
 */

public class ListPersonSignInRequestData {
    private int userId;
    private int pageSize;

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
}
