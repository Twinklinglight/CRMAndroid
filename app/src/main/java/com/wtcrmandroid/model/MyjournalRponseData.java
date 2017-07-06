package com.wtcrmandroid.model;

/**
 *  type 1.日计划，2.日总结，3.周计划，4.周总结
 * Created by zxd on 2017/6/8
 */

public class MyjournalRponseData {

    private String LogID;
    private String ShortRecordDate;
    private String WeekBegin;
    private String WeekEnd;
    private String Content;
    private String Type;

    public String getLogID() {
        return LogID;
    }

    public void setLogID(String logID) {
        LogID = logID;
    }

    public String getShortRecordDate() {
        return ShortRecordDate;
    }

    public void setShortRecordDate(String shortRecordDate) {
        ShortRecordDate = shortRecordDate;
    }

    public String getWeekBegin() {
        return WeekBegin;
    }

    public void setWeekBegin(String weekBegin) {
        WeekBegin = weekBegin;
    }

    public String getWeekEnd() {
        return WeekEnd;
    }

    public void setWeekEnd(String weekEnd) {
        WeekEnd = weekEnd;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
