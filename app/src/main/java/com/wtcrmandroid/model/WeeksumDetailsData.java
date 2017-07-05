package com.wtcrmandroid.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zxd on 2017/6/12
 */

public class WeeksumDetailsData {

    @SerializedName("workNumber")
    private String WeekTitle;       //总结标题
    @SerializedName("workContent")
    private String WeekContent;     //工作内容
    @SerializedName("workPlanning")
    private String WeekTarget;      //计划目标
    @SerializedName("workPercentage")
    private String WeekPercent;     //所占比重
    @SerializedName("workTarget")
    private String WeekComplete;    //完成情况

    private String workUnfinishedReason;
    private String workNextFinishTime;

    public String getWeekTitle() {
        return WeekTitle;
    }

    public void setWeekTitle(String weekTitle) {
        WeekTitle = weekTitle;
    }

    public String getWeekContent() {
        return WeekContent;
    }

    public void setWeekContent(String weekContent) {
        WeekContent = weekContent;
    }

    public String getWeekTarget() {
        return WeekTarget;
    }

    public void setWeekTarget(String weekTarget) {
        WeekTarget = weekTarget;
    }

    public String getWeekPercent() {
        return WeekPercent;
    }

    public void setWeekPercent(String weekPercent) {
        WeekPercent = weekPercent;
    }

    public String getWeekComplete() {
        return WeekComplete;
    }

    public void setWeekComplete(String weekComplete) {
        WeekComplete = weekComplete;
    }

    public String getWorkUnfinishedReason() {
        return workUnfinishedReason;
    }

    public void setWorkUnfinishedReason(String workUnfinishedReason) {
        this.workUnfinishedReason = workUnfinishedReason;
    }

    public String getWorkNextFinishTime() {
        return workNextFinishTime;
    }

    public void setWorkNextFinishTime(String workNextFinishTime) {
        this.workNextFinishTime = workNextFinishTime;
    }
}
