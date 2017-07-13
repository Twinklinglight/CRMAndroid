package com.wtcrmandroid.model;

import com.google.gson.annotations.SerializedName;

/**
 * 后台写日总结的实体类
 * Created by zxd on 2017/6/13
 */

public class WriteDaysumData {

    @SerializedName("workLevel")
    private String WorkSort;
    @SerializedName("workContent")
    private String WorkContent;
    @SerializedName("workUser")
    private String WorkPerson;
    @SerializedName("workState")
    private String WorkComplete;

    private String workUnfinishedReason;
    private String workFinishTime;
    private String workNextFinishTime;

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

    public String getWorkComplete() {
        return WorkComplete;
    }

    public void setWorkComplete(String workComplete) {
        WorkComplete = workComplete;
    }

    public String getWorkUnfinishedReason() {
        return workUnfinishedReason;
    }

    public void setWorkUnfinishedReason(String workUnfinishedReason) {
        this.workUnfinishedReason = workUnfinishedReason;
    }

    public String getWorkFinishTime() {
        return workFinishTime;
    }

    public void setWorkFinishTime(String workFinishTime) {
        this.workFinishTime = workFinishTime;
    }

    public String getWorkNextFinishTime() {
        return workNextFinishTime;
    }

    public void setWorkNextFinishTime(String workNextFinishTime) {
        this.workNextFinishTime = workNextFinishTime;
    }
}
