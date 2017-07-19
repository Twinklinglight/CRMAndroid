package com.wtcrmandroid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zxd on 2017/6/12
 */

public class MajorCustomerData{

    @SerializedName("workLevel")
    private String WorkSort;
    @SerializedName("custName")
    private String CustomerName;
    @SerializedName("nextIdea")
    private String WorkAnalysis;

    public String getWorkSort() {
        return WorkSort;
    }

    public void setWorkSort(String workSort) {
        WorkSort = workSort;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getWorkAnalysis() {
        return WorkAnalysis;
    }

    public void setWorkAnalysis(String workAnalysis) {
        WorkAnalysis = workAnalysis;
    }

    @Override
    public String toString() {
        return "MajorCustomerData{" +
                "WorkSort='" + WorkSort + '\'' +
                ", CustomerName='" + CustomerName + '\'' +
                ", WorkAnalysis='" + WorkAnalysis + '\'' +
                '}';
    }
}
