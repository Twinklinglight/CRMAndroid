package com.wtcrmandroid.model.reponsedata;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zxd on 2017/6/9
 */

public class HtDaysumDetailsData {

    @SerializedName("workLevel")
    private String WorkSort;

    @SerializedName("workUser")
    private String WorkPerson;

    @SerializedName("workContent")
    private String WorkContent;

    @SerializedName("workState")
    private String WorkComplete;

   /* @SerializedName("workUnfinishedReason")
    private String WorkStudy;*/

    private String workUnfinishedReason;
    private String workNextFinishTime;

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

    public String getWorkContent() {
        return WorkContent;
    }

    public void setWorkContent(String workContent) {
        WorkContent = workContent;
    }

    public String getWorkComplete() {
        return WorkComplete;
    }

    public void setWorkComplete(String workComplete) {
        WorkComplete = workComplete;
    }

    /*public String getWorkStudy() {
        return WorkStudy;
    }

    public void setWorkStudy(String workStudy) {
        WorkStudy = workStudy;
    }*/
}
