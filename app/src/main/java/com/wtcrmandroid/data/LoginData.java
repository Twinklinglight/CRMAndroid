package com.wtcrmandroid.data;

/**
 * Created by 1363655717 on 2017-06-12.
 */

public class LoginData {

    private int UserID;
    private String UserName;
    private String Attribution;
    private int DepartmentID;

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
}
