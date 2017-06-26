package com.wtcrmandroid.httpfactory.reponsedata;

/**
 * Created by 1363655717 on 2017-06-12.
 */

public class LoginData {
    /**
     * UserID : 3066
     * UserName : 申中佳
     * Attribution : WT
     * DepartmentID : 34
     * Token : 9A7DCA02DC90B62E
     * Element : null
     */

    private int UserID;
    private String UserName;
    private String Attribution;
    private int DepartmentID;
    private String Token;
    private Object Element;

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

    public Object getElement() {
        return Element;
    }

    public void setElement(Object Element) {
        this.Element = Element;
    }


}
