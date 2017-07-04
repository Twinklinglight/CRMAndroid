package com.wtcrmandroid.model.dealdata;

/**
 * Created by wt-pc on 2017/7/3.
 */

public class GroupingClockRecordDD {
    /**
     * 类别
     */
    private int Type;

    private String time;
    private String address;
    private float lat;
    private float lnt;

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLnt() {
        return lnt;
    }

    public void setLnt(float lnt) {
        this.lnt = lnt;
    }

    @Override
    public String toString() {
        return
                "Type=" + Type +
                ", time='" + time + '\'' +
                ", address='" + address + '\'' +
                ", lat=" + lat +
                ", lnt=" + lnt ;
    }
}
