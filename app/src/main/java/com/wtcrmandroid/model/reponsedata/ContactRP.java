package com.wtcrmandroid.model.reponsedata;

import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;

import java.io.Serializable;

/**
 * Created by zxd on 2017/7/29.
 */

public class ContactRP extends BaseIndexPinyinBean implements Serializable{

    /**
     * UserID : 3
     * UserNo : lilifeng
     * UserName : 李莉锋
     * Telphone : 15838365596
     * RoleName :
     */

    private String UserID;
    private String UserNo;
    private String UserName;
    private String Telphone;
    private String RoleName;
    private String headerimg;

    public ContactRP(){

    }

    public ContactRP setTop(String User){
        setUserName(User);
        return this;
    }

    public String getHeaderimg() {
        return headerimg;
    }

    public void setHeaderimg(String headerimg) {
        this.headerimg = headerimg;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getUserNo() {
        return UserNo;
    }

    public void setUserNo(String UserNo) {
        this.UserNo = UserNo;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getTelphone() {
        return Telphone;
    }

    public void setTelphone(String Telphone) {
        this.Telphone = Telphone;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String RoleName) {
        this.RoleName = RoleName;
    }

    @Override
    public String toString() {
        return "ContactRP{" +
                "UserID='" + UserID + '\'' +
                ", UserNo='" + UserNo + '\'' +
                ", UserName='" + UserName + '\'' +
                ", Telphone='" + Telphone + '\'' +
                ", RoleName='" + RoleName + '\'' +
                '}';
    }

    @Override
    public String getTarget() {
        return UserName;
    }


    @Override
    public boolean isNeedToPinyin() {
        return !UserName.equals("不需要");
    }

    @Override
    public boolean isShowSuspension() {
        return !UserName.equals("不需要");
    }
}
