package com.wtcrmandroid.model.reponsedata;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 1363655717 on 2017-06-06.
 * 写周计划实体类
 */

public class WriterWeekSumData {

    private String workNumber;
    private String workContent;
    private String workPlanning;
    private String workPercentage;
    @SerializedName("workTarget")
    private String workComplete;
    private String workUnfinishedReason;
    private String workNextFinishTime;

    public String getWorkNextFinishTime() {
        return workNextFinishTime;
    }

    public void setWorkNextFinishTime(String workNextFinishTime) {
        this.workNextFinishTime = workNextFinishTime;
    }

    public String getWorkUnfinishedReason() {
        return workUnfinishedReason;
    }

    public void setWorkUnfinishedReason(String workUnfinishedReason) {
        this.workUnfinishedReason = workUnfinishedReason;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public String getWorkPlanning() {
        return workPlanning;
    }

    public void setWorkPlanning(String workPlanning) {
        this.workPlanning = workPlanning;
    }

    public String getWorkPercentage() {
        return workPercentage;
    }

    public void setWorkPercentage(String workPercentage) {
        this.workPercentage = workPercentage;
    }

    public String getWorkComplete() {
        return workComplete;
    }

    public void setWorkComplete(String workComplete) {
        this.workComplete = workComplete;
    }

    @Override
    public String toString() {
        return "WriterWeekSumData{" +
                "workNumber='" + workNumber + '\'' +
                ", workContent='" + workContent + '\'' +
                ", workPlanning='" + workPlanning + '\'' +
                ", workPercentage='" + workPercentage + '\'' +
                ", workComplete='" + workComplete + '\'' +
                '}';
    }
}
