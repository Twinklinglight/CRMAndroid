package com.wtcrmandroid.model.reponsedata;

/**
 * 公文列表 返回数据的实体类
 * Created by zxd on 2017/7/5.
 */

public class DocumentListRpData {

    private String title;
    private String name;
    private String date;
    private String state;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
