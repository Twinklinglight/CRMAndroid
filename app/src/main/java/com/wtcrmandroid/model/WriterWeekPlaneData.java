package com.wtcrmandroid.model;

/**
 * Created by 1363655717 on 2017-06-06.
 * 写周计划实体类
 */

public class WriterWeekPlaneData {
    /*
        { workNumber:"序号",
        workContent:"工作计划事项",
        workPlanning:"计划达成目标/完成进度",
        workPercentage:"所占比重" }
    */
    private String workNumber;
    private String workContent;
    private String workPlanning;
    private String workPercentage;

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

    public String toString() {
        return "{" +
                "workNumber='" + workNumber + '\'' +
                ", workContent='" + workContent + '\'' +
                ", workPlanning='" + workPlanning + '\'' +
                ", workPercentage='" + workPercentage + '\'' +
                '}';
    }
}
