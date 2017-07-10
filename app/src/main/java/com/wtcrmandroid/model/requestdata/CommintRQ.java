package com.wtcrmandroid.model.requestdata;

/**
 * Created by zxd on 2017/7/10.
 */

public class CommintRQ {
    private int userId;
    private int logId;
    private String examineText;
    private String leve;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getExamineText() {
        return examineText;
    }

    public void setExamineText(String examineText) {
        this.examineText = examineText;
    }

    public String getLeve() {
        return leve;
    }

    public void setLeve(String leve) {
        this.leve = leve;
    }
}
