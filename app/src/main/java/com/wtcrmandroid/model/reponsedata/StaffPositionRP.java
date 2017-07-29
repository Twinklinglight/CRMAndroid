package com.wtcrmandroid.model.reponsedata;

import java.io.Serializable;

/**
 * Created by wt-pc on 2017/7/26.
 */

public class StaffPositionRP implements Serializable {

    /**
     * id : 2408
     * createTime : 2017/7/26 15:11:18
     * address : 河南省郑州市管城回族区东明路51号附4号
     * userId : 2627
     * positionType : 2
     * userName : 贾信河
     * roleName : 物通董事长
     * lng : 113.706420546813
     * lat : 34.7541003960663
     */

    private String id;
    private String createTime;
    private String address;
    private String userId;
    private String positionType;
    private String userName;
    private String roleName;
    private double lng;
    private double lat;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
