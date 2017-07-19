package com.wtcrmandroid.model.reponsedata;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zxd on 2017/6/9
 */

public class HtDayplanDetailsData{

    @SerializedName("workLevel")
    private String WorkSort;
    @SerializedName("workUser")
    private String WorkPerson;
    @SerializedName("workPercentage")
    private String WorkPercent;
    @SerializedName("workContent")
    private String WorkContent;
    private String remarks;
    private String workPlanTime;

    public String getWorkSort() {
        return WorkSort;
    }

    public void setWorkSort(String workSort) {
        WorkSort = workSort;
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

    public String getWorkContent() {
        return WorkContent;
    }

    public void setWorkContent(String workContent) {
        WorkContent = workContent;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getWorkPlanTime() {
        return workPlanTime;
    }

    public void setWorkPlanTime(String workPlanTime) {
        this.workPlanTime = workPlanTime;
    }

    @Override
    public String toString() {
        return "HtDayplanDetailsData{" +
                "WorkSort='" + WorkSort + '\'' +
                ", WorkPerson='" + WorkPerson + '\'' +
                ", WorkPercent='" + WorkPercent + '\'' +
                ", WorkContent='" + WorkContent + '\'' +
                ", remarks='" + remarks + '\'' +
                ", workPlanTime='" + workPlanTime + '\'' +
                '}';
    }
}
