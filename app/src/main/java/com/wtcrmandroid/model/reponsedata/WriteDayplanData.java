package com.wtcrmandroid.model.reponsedata;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zxd on 2017/6/13
 */

public class WriteDayplanData {

    //将java对象的属性转换成指定的json名字
    @SerializedName("workLevel")
    private String WorkSort;
    @SerializedName("workContent")
    private String WorkContent;
    @SerializedName("workUser")
    private String WorkPerson;
    @SerializedName("workPercentage")
    private String WorkPercent;
    @SerializedName("workPlanTime")
    private String WrokTime;
    @SerializedName("remarks")
    private String WrokBeizhu;

    public String getWorkSort() {
        return WorkSort;
    }

    public void setWorkSort(String workSort) {
        WorkSort = workSort;
    }

    public String getWorkContent() {
        return WorkContent;
    }

    public void setWorkContent(String workContent) {
        WorkContent = workContent;
    }

    public String getWorkPerson() {
        return WorkPerson;
    }

    public void setWorkPerson(String workPerson) {
        WorkPerson = workPerson;
    }

    public String getWorkPercent() {
        return WorkPercent;
    }

    public void setWorkPercent(String workPercent) {
        WorkPercent = workPercent;
    }

    public String getWrokTime() {
        return WrokTime;
    }

    public void setWrokTime(String wrokTime) {
        WrokTime = wrokTime;
    }

    public String getWrokBeizhu() {
        return WrokBeizhu;
    }

    public void setWrokBeizhu(String wrokBeizhu) {
        WrokBeizhu = wrokBeizhu;
    }

    @Override
    public String toString() {
        return "WriteDayplanData{" +
                "WorkSort='" + WorkSort + '\'' +
                ", WorkContent='" + WorkContent + '\'' +
                ", WorkPerson='" + WorkPerson + '\'' +
                ", WorkPercent='" + WorkPercent + '\'' +
                ", WrokTime='" + WrokTime + '\'' +
                ", WrokBeizhu='" + WrokBeizhu + '\'' +
                '}';
    }
}
