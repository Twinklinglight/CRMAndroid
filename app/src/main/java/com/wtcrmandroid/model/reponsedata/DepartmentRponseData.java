package com.wtcrmandroid.model.reponsedata;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zxd on 2017/7/10.
 */

public class DepartmentRponseData implements Serializable{

    private int userid;
    private String username;
    private String roleLevel;
    @SerializedName("examineCount")
    private String Column1;
    private int roleclass;

    public int getRoleclass() {
        return roleclass;
    }

    public void setRoleclass(int roleclass) {
        this.roleclass = roleclass;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(String roleLevel) {
        this.roleLevel = roleLevel;
    }

    public String getColumn1() {
        return Column1;
    }

    public void setColumn1(String column1) {
        Column1 = column1;
    }

    @Override
    public String toString() {
        return "DepartmentRponseData{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", roleLevel='" + roleLevel + '\'' +
                ", Column1='" + Column1 + '\'' +
                ", roleclass=" + roleclass +
                '}';
    }
}
