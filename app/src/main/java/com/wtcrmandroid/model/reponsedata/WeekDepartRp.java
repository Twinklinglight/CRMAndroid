package com.wtcrmandroid.model.reponsedata;

import java.util.List;

/**
 * Created by zxd on 2017/7/20.
 */

public class WeekDepartRp {

    private int logId;
    private String learning;
    private List<WriterWeekPlaneData> plan;
    private List<WeeksumDetailsData> work;
    private List<CommentData> exam;

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

    public List<WriterWeekPlaneData> getPlan() {
        return plan;
    }

    public void setPlan(List<WriterWeekPlaneData> plan) {
        this.plan = plan;
    }

    public List<WeeksumDetailsData> getWork() {
        return work;
    }

    public void setWork(List<WeeksumDetailsData> work) {
        this.work = work;
    }

    public List<CommentData> getExam() {
        return exam;
    }

    public void setExam(List<CommentData> exam) {
        this.exam = exam;
    }
}
