package com.wtcrmandroid.model;

/**
 * Created by zxd on 2017/6/14
 */

public class GetMoneyData {
    /**
     * ret : 0
     * msg : 成功
     * data : [{"workNumber":"" ,"workContent":"搭建配置WAF防御系统" ,"workPlanning":"" ,"workPercentage":"" ,"workTarget":"" ,"workUnfinishedReason":"" ,"workNextFinishTime":"" },{"workNumber":"" ,"workContent":"centos el7系统下配置redmine添加git版本库" ,"workPlanning":"" ,"workPercentage":"" ,"workTarget":"" ,"workUnfinishedReason":"" ,"workNextFinishTime":"" },{"workNumber":"" ,"workContent":"gitlab的简单使用配置" ,"workPlanning":"" ,"workPercentage":"" ,"workTarget":"" ,"workUnfinishedReason":"" ,"workNextFinishTime":"" },{"workNumber":"" ,"workContent":"centos 平台搭建waf及x-waf-admin" ,"workPlanning":"" ,"workPercentage":"" ,"workTarget":"" ,"workUnfinishedReason":"" ,"workNextFinishTime":"" },{"workNumber":"" ,"workContent":"对waf的部署和配置" ,"workPlanning":"" ,"workPercentage":"" ,"workTarget":"" ,"workUnfinishedReason":"" ,"workNextFinishTime":"" }]
     *//*

    private int ret;
    private String msg;
    private String data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }*/




    private String CustomerName;
    private String CustomerType;
    private String ProductType;
    private String BackMoney;

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerType() {
        return CustomerType;
    }

    public void setCustomerType(String customerType) {
        CustomerType = customerType;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

    public String getBackMoney() {
        return BackMoney;
    }

    public void setBackMoney(String backMoney) {
        BackMoney = backMoney;
    }

    @Override
    public String toString() {
        return "GetMoneyData{" +
                "CustomerName='" + CustomerName + '\'' +
                ", CustomerType='" + CustomerType + '\'' +
                ", ProductType='" + ProductType + '\'' +
                ", BackMoney='" + BackMoney + '\'' +
                '}';
    }
}
