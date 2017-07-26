package com.wtcrmandroid.model.requestdata;

import com.wtcrmandroid.model.reponsedata.WriteDaysumData;

import java.util.List;

/**
 * Created by ZSC on 2017/6/30.
 */

public class WDaySumRequestData {

    private int userId;
    private String workRecordTime;
    private String type;
    private boolean isPlan;
    private List<WriteDaysumData> work;
    private String learningAndReflection;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getWorkRecordTime() {
        return workRecordTime;
    }

    public void setWorkRecordTime(String workRecordTime) {
        this.workRecordTime = workRecordTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPlan() {
        return isPlan;
    }

    public void setPlan(boolean plan) {
        isPlan = plan;
    }

    public List<WriteDaysumData> getWork() {
        return work;
    }

    public void setWork(List<WriteDaysumData> work) {
        this.work = work;
    }

    public String getLearningAndReflection() {
        return learningAndReflection;
    }

    public void setLearningAndReflection(String learningAndReflection) {
        this.learningAndReflection = learningAndReflection;
    }
}
