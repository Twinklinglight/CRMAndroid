package com.wtcrmandroid.model.requestdata;

import com.wtcrmandroid.model.reponsedata.MajorCustomerData;
import com.wtcrmandroid.model.reponsedata.SingleCustomerData;
import com.wtcrmandroid.model.reponsedata.WriteDayplanData;

import java.util.List;

/**
 * Created by zxd on 2017/7/17.
 */

public class XsWriteDayplanRQ {

    private int userId;
    private String workRecordTime;
    private String type;
    private boolean isPlan;
    private int roleClass;
    private List<WriteDayplanData> workdetail;
    private List<SingleCustomerData>workdreamorder;
    private List<MajorCustomerData>workfocus;
    private String learningAndReflection;

    public int getUserId() {
        return userId;
    }

    public int getRoleClass() {
        return roleClass;
    }

    public void setRoleClass(int roleClass) {
        this.roleClass = roleClass;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getWorkRecordTime() {
        return workRecordTime;
    }

    public void setWorkRecordTime(String workRecordTime) {
        this.workRecordTime = workRecordTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPlan() {
        return isPlan;
    }

    public void setPlan(boolean plan) {
        isPlan = plan;
    }

    public List<WriteDayplanData> getWorkdetail() {
        return workdetail;
    }

    public void setWorkdetail(List<WriteDayplanData> workdetail) {
        this.workdetail = workdetail;
    }

    public List<SingleCustomerData> getWorkdreamorder() {
        return workdreamorder;
    }

    public void setWorkdreamorder(List<SingleCustomerData> workdreamorder) {
        this.workdreamorder = workdreamorder;
    }

    public List<MajorCustomerData> getWorkfocus() {
        return workfocus;
    }

    public void setWorkfocus(List<MajorCustomerData> workfocus) {
        this.workfocus = workfocus;
    }

    public String getLearningAndReflection() {
        return learningAndReflection;
    }

    public void setLearningAndReflection(String learningAndReflection) {
        this.learningAndReflection = learningAndReflection;
    }
}
