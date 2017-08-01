package com.wtcrmandroid.model.reponsedata;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by 1363655717 on 2017-06-12.
 */

public class LoginData implements Serializable {

    /**
     * UserID : 3066
     * UserName : 申中佳
     * Attribution : WT
     * DepartmentID : 34
     * Token : 4879BD23AAA7FA31
     * IsSaler : false
     */
    @SerializedName("UserID")
    private int UserID;
    @SerializedName("UserName")
    private String UserName;
    @SerializedName("Attribution")
    private String Attribution;
    @SerializedName("DepartmentID")
    private int DepartmentID;
    @SerializedName("Token")
    private String Token;
    @SerializedName("RoleClass")
    private int RoleClass;

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getAttribution() {
        return Attribution;
    }

    public void setAttribution(String Attribution) {
        this.Attribution = Attribution;
    }

    public int getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(int DepartmentID) {
        this.DepartmentID = DepartmentID;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public int getRoleClass() {
        return RoleClass;
    }

    public void setRoleClass(int roleClass) {
        RoleClass = roleClass;
    }
}
