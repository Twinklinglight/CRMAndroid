package com.wtcrmandroid.model.reponsedata;

import com.wtcrmandroid.model.WeeksumDetailsData;

import java.util.List;

/**
 * Created by zxd on 2017/7/4.
 */

public class WeekSumDetailsRpData {



    private List<WeeksumDetailsData> work;

    private List<CommentData> exam;

    private String leve;

    private String learning;


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

    public String getLeve() {
        return leve;
    }

    public void setLeve(String leve) {
        this.leve = leve;
    }

    public String getLearning() {
        return learning;
    }

    public void setLearning(String learning) {
        this.learning = learning;
    }
}
