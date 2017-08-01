package com.wtcrmandroid.model.requestdata;

/**
 * Created by zxd on 2017/7/26.
 */

public class NumberRQ {
    private int userId;
    private String oldPhone;
    private String newPhone;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOldPhone() {
        return oldPhone;
    }

    public void setOldPhone(String oldPhone) {
        this.oldPhone = oldPhone;
    }

    public String getNewPhone() {
        return newPhone;
    }

    public void setNewPhone(String newPhone) {
        this.newPhone = newPhone;
    }
}
