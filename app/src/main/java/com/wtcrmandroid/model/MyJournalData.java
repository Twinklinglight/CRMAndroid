package com.wtcrmandroid.model;

/**
 *  type 1.日计划，2.日总结，3.周计划，4.周总结
 * Created by zxd on 2017/6/8
 */

public class MyJournalData {

    private String mJournalTitle;
    private String mJournalContent;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getJournalTitle() {
        return mJournalTitle;
    }

    public void setJournalTitle(String journalTitle) {
        mJournalTitle = journalTitle;
    }

    public String getJournalContent() {
        return mJournalContent;
    }

    public void setJournalContent(String journalContent) {
        mJournalContent = journalContent;
    }
}
