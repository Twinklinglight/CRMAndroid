package com.wtcrmandroid.model.reponsedata;

import java.util.List;

/**
 * Created by zxd on 2017/7/20.
 */

public class XsDayDepartRp {

    private int logId;
    private XsDausumWorkData work;
    private XsDayplanDetailsRP plan;
    private List<CommentData> exam;
    private String learning;

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public XsDausumWorkData getWork() {
        return work;
    }

    public void setWork(XsDausumWorkData work) {
        this.work = work;
    }

    public XsDayplanDetailsRP getPlan() {
        return plan;
    }

    public void setPlan(XsDayplanDetailsRP plan) {
        this.plan = plan;
    }

    public List<CommentData> getExam() {
        return exam;
    }

    public void setExam(List<CommentData> exam) {
        this.exam = exam;
    }

    public String getLearning() {
        return learning;
    }

    public void setLearning(String learning) {
        this.learning = learning;
    }
}
