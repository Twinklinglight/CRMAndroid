package com.wtcrmandroid.model.requestdata;

/**
 * Created by wt-pc on 2017/6/22.
 * 位置数据保存
 */

public class PlaceSaveRQ {
   //用户Id
    private int userId;
    //经度
    private Double lat;
    //维度
    private Double lng ;
    //数据类型(1打卡、2与员工位置)
    private String positionType ;

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }
    /**
     * 维度
     */
    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }
}
