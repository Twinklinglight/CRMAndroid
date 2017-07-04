package com.wtcrmandroid.model.reponsedata;

/**
 * Created by wt-pc on 2017/7/3.
 * 请求返回数据
 * 打卡记录
 */

public class ClockRecordRP {

    /**
     * positionType : 1
     * id : 1
     * createTime : 2017/6/26 9:08:41
     * lng : 34.754332
     * lat : 113.706259
     * userId : 3066
     * Row : 1
     */

    private String positionType;
    private String id;
    private String createTime;
    private String lng;
    private String lat;
    private String userId;
    private String Row;
    private String address;

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRow() {
        return Row;
    }

    public void setRow(String Row) {
        this.Row = Row;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
