package com.wtcrmandroid.utils.areaslection;

import com.google.gson.annotations.SerializedName;

/**
 * area地区实体类
 * Created by Mr-Zhang on 2016/3/24.
 */
public class Area {
    @SerializedName("id")
    private int id;
    @SerializedName("sheng")
    private String sheng;
    @SerializedName("shi")
    private String shi;
    @SerializedName("xian")
    private String xian;
    @SerializedName("street")
    private String street;//街道名
    @SerializedName("street_number")
    private String street_number;//街道门牌号
    @SerializedName("lat")
    private String lat;
    @SerializedName("lng")
    private String lng;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSheng() {
        return sheng;
    }

    public void setSheng(String sheng) {
        this.sheng = sheng;
    }

    public String getShi() {
        return shi;
    }

    public void setShi(String shi) {
        this.shi = shi;
    }

    public String getXian() {
        return xian;
    }

    public void setXian(String xian) {
        this.xian = xian;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet_number() {
        return street_number;
    }

    public void setStreet_number(String street_number) {
        this.street_number = street_number;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", sheng='" + sheng + '\'' +
                ", shi='" + shi + '\'' +
                ", xian='" + xian + '\'' +
                ", street='" + street + '\'' +
                ", street_number='" + street_number + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
    }
}
