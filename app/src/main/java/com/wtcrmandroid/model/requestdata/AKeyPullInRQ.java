package com.wtcrmandroid.model.requestdata;

/**
 * Created by wt-pc on 2017/7/13.
 * 设为我的客户
 */

public class AKeyPullInRQ {
    private int userId;
    private String customerId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
