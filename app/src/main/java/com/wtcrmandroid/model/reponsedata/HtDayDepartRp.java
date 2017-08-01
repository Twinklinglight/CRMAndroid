package com.wtcrmandroid.model.reponsedata;

import java.util.List;

/**
 * Created by zxd on 2017/7/20.
 */

public class HtDayDepartRp {


    private int logId;
    private String learning;
    private List<CommentData> exam;
    private List<HtDayplanDetailsData> plan;
    private List<HtDaysumDetailsData> work;

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }


    public String getLearning() {
        return learning;
    }

    public void setLearning(String learning) {
        this.learning = learning;
    }

    public List<CommentData> getExam() {
        return exam;
    }

    public void setExam(List<CommentData> exam) {
        this.exam = exam;
    }

    public List<HtDayplanDetailsData> getPlan() {
        return plan;
    }

    public void setPlan(List<HtDayplanDetailsData> plan) {
        this.plan = plan;
    }

    public List<HtDaysumDetailsData> getWork() {
        return work;
    }

    public void setWork(List<HtDaysumDetailsData> work) {
        this.work = work;
    }

}
