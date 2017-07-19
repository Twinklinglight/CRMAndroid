package com.wtcrmandroid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zxd on 2017/6/14
 */

public class GetMoneyData implements Serializable{

    @SerializedName("custName")
    private String CustomerName;
    @SerializedName("custKind")
    private String CustomerType;
    @SerializedName("productType")
    private String ProductType;
    @SerializedName("OrderMoney")
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
