package com.wtcrmandroid.model.requestdata;

import com.wtcrmandroid.model.WriteDayplanData;

import java.util.List;

/**
 * Created by ZSC on 2017/6/30.
 */

public class WDayPlanRQ {

    private int userId;
    private String workRecordTime;
    private String type;
    private boolean isPlan;
    private List<WriteDayplanData> work;
    private String learningAndReflection;

    public String getWorkRecordTime() {
        return workRecordTime;
    }

    public void setWorkRecordTime(String workRecordTime) {
        this.workRecordTime = workRecordTime;
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

    public boolean isPlan() {
        return isPlan;
    }

    public void setPlan(boolean plan) {
        isPlan = plan;
    }

    public List<WriteDayplanData> getWork() {
        return work;
    }

    public void setWork(List<WriteDayplanData> work) {
        this.work = work;
    }

    public String getLearningAndReflection() {
        return learningAndReflection;
    }

    public void setLearningAndReflection(String learningAndReflection) {
        this.learningAndReflection = learningAndReflection;
    }
}
