package com.wtcrmandroid.model.requestdata;

import com.wtcrmandroid.model.reponsedata.WriterWeekSumData;

import java.util.List;

/**
 * Created by ZSC on 2017/6/30.
 */

public class WweekSumRequstData {

    private int userId;
    private String weekStart;
    private String weekEnd;
    private String type;
    private boolean isPlan;
    private List<WriterWeekSumData> work;
    private String learningAndReflection;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getWeekStart() {
        return weekStart;
    }

    public void setWeekStart(String weekStart) {
        this.weekStart = weekStart;
    }

    public String getWeekEnd() {
        return weekEnd;
    }

    public void setWeekEnd(String weekEnd) {
        this.weekEnd = weekEnd;
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

    public List<WriterWeekSumData> getWork() {
        return work;
    }

    public void setWork(List<WriterWeekSumData> work) {
        this.work = work;
    }

    public String getLearningAndReflection() {
        return learningAndReflection;
    }

    public void setLearningAndReflection(String learningAndReflection) {
        this.learningAndReflection = learningAndReflection;
    }
}
