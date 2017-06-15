package com.wtcrmandroid.model;

/**
 * Created by zxd on 2017/6/14
 */

public class GetMoneyData {


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
