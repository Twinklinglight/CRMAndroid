package com.wtcrmandroid.model.reponsedata;

import java.util.List;

/**
 * Created by zxd on 2017/7/3.
 */

public class DaySumDetailsRpData {

    private int logId;
    private List<HtDaysumDetailsData> work;
    private List<CommentData> exam;

    private String learning;


    public String getLearning() {
        return learning;
    }

    public void setLearning(String learning) {
        this.learning = learning;
    }

    public List<HtDaysumDetailsData> getWork() {
        return work;
    }

    public void setWork(List<HtDaysumDetailsData> work) {
        this.work = work;
    }

    public List<CommentData> getExam() {
        return exam;
    }

    public void setExam(List<CommentData> exam) {
        this.exam = exam;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

}
