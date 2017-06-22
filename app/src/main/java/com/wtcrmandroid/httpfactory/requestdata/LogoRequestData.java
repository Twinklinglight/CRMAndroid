package com.wtcrmandroid.httpfactory.requestdata;

/**
 * Created by wt-pc on 2017/6/22.
 */

public class LogoRequestData {
    private String userName;
    private String userPass;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    @Override
    public String toString() {
        return "{" +
                "userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                '}';
    }
}
