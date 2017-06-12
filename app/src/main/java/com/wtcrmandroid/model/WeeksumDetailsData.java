package com.wtcrmandroid.model;

/**
 * Created by zxd on 2017/6/12
 */

public class WeeksumDetailsData {
    private String WeekTitle;       //总结标题
    private String WeekContent;     //工作内容
    private String WeekTarget;      //计划目标
    private String WeekPercent;     //所占比重
    private String WeekComplete;    //完成情况
    private String WeekStudy;       //学习反省

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

    public String getWeekStudy() {
        return WeekStudy;
    }

    public void setWeekStudy(String weekStudy) {
        WeekStudy = weekStudy;
    }
}
