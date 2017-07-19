package com.wtcrmandroid.model.requestdata;

/**
 * Created by ZSC on 2017/7/1.
 */

public class DayDetailsRQ {

    private int userId;
    private String type;
    private boolean isPlan;
    private String nowDate;

    private int roleClass;

    public int getRoleClass() {
        return roleClass;
    }

    public void setRoleClass(int roleClass) {
        this.roleClass = roleClass;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getIsPlan() {
        return isPlan;
    }

    public void setIsPlan(boolean isPlan) {
        this.isPlan = isPlan;
    }

    public String getNowDate() {
        return nowDate;
    }

    public void setNowDate(String nowDate) {
        this.nowDate = nowDate;
    }

    @Override
    public String toString() {
        return "DayDetailsRQ{" +
                "userId=" + userId +
                ", type='" + type + '\'' +
                ", isPlan=" + isPlan +
                ", nowDate='" + nowDate + '\'' +
                ", roleClass=" + roleClass +
                '}';
    }
}
