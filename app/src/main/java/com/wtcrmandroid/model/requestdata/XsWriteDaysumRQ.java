package com.wtcrmandroid.model.requestdata;

import com.wtcrmandroid.model.reponsedata.AddPurpostCtAtData;
import com.wtcrmandroid.model.reponsedata.GetMoneyData;
import com.wtcrmandroid.model.reponsedata.GetSingleCustomerData;
import com.wtcrmandroid.model.reponsedata.WriteDaysumData;

import java.util.List;

/**
 * Created by zxd on 2017/7/17.
 */

public class XsWriteDaysumRQ {

    private int userId;
    private String workRecordTime;
    private String type;
    private boolean isPlan;
    private int roleClass;
    private List<WriteDaysumData> workdetail;       //基本写总结列表
    private WorkOrder workorder;                    //今日工作量
    private List<AddPurpostCtAtData>addcustinfo;    //新增意向客户
    private List<GetSingleCustomerData>workdream;   //预测单客户是否踩中
    private List<GetMoneyData>workload;             //汇款到单
    private String learningAndReflection;

    public int getUserId() {
        return userId;
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

    public int getRoleClass() {
        return roleClass;
    }

    public void setRoleClass(int roleClass) {
        this.roleClass = roleClass;
    }

    public List<WriteDaysumData> getWorkdetail() {
        return workdetail;
    }

    public void setWorkdetail(List<WriteDaysumData> workdetail) {
        this.workdetail = workdetail;
    }

    public WorkOrder getWorkorder() {
        return workorder;
    }

    public void setWorkorder(WorkOrder workorder) {
        this.workorder = workorder;
    }

    public List<AddPurpostCtAtData> getAddcustinfo() {
        return addcustinfo;
    }

    public void setAddcustinfo(List<AddPurpostCtAtData> addcustinfo) {
        this.addcustinfo = addcustinfo;
    }

    public List<GetSingleCustomerData> getWorkdream() {
        return workdream;
    }

    public void setWorkdream(List<GetSingleCustomerData> workdream) {
        this.workdream = workdream;
    }

    public List<GetMoneyData> getWorkload() {
        return workload;
    }

    public void setWorkload(List<GetMoneyData> workload) {
        this.workload = workload;
    }

    public String getLearningAndReflection() {
        return learningAndReflection;
    }

    public void setLearningAndReflection(String learningAndReflection) {
        this.learningAndReflection = learningAndReflection;
    }
}
