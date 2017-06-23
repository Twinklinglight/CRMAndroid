package com.wtcrmandroid.model.requestdata;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wt-pc on 2017/6/22.
 * 登录接口上传参数实体类
 */

public class LoginRequestData {
    //将java对象的属性转换成指定的json名字
    @SerializedName("userName")
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
