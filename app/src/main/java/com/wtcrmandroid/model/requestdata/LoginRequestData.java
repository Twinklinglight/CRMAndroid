package com.wtcrmandroid.model.requestdata;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by wt-pc on 2017/6/22.
 * 登录接口上传参数实体类
 */

public class LoginRequestData implements Serializable {
    //将java对象的属性转换成指定的json名字
    @SerializedName("userName")
    private String userName;
    @SerializedName("userPass")
    private String userPass;
    @SerializedName("imei")
    private String imei;

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

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
