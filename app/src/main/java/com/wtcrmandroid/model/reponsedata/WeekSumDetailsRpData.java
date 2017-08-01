package com.wtcrmandroid.model.reponsedata;

import java.util.List;

/**
 * Created by zxd on 2017/7/4.
 */

public class WeekSumDetailsRpData {

    private int logId;

    private List<WeeksumDetailsData> work;

    private List<CommentData> exam;

    private String learning;


    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
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

    public String getLearning() {
        return learning;
    }

    public void setLearning(String learning) {
        this.learning = learning;
    }
}
