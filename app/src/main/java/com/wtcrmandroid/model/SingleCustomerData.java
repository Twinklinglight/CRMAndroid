package com.wtcrmandroid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zxd on 2017/6/14
 */

public class SingleCustomerData{

    @SerializedName("workLevel")
    private String WorkSort;
    @SerializedName("custName")
    private String WorkName;
    @SerializedName("nextIdea")
    private String WorkAnalysis;
    @SerializedName("orderMaybe")
    private String WorkPercent;

    public String getWorkSort() {
        return WorkSort;
    }

    public void setWorkSort(String workSort) {
        WorkSort = workSort;
    }

    public String getWorkName() {
        return WorkName;
    }

    public void setWorkName(String workName) {
        WorkName = workName;
    }

    public String getWorkAnalysis() {
        return WorkAnalysis;
    }

    public void setWorkAnalysis(String workAnalysis) {
        WorkAnalysis = workAnalysis;
    }

    public String getWorkPercent() {
        return WorkPercent;
    }

    public void setWorkPercent(String workPercent) {
        WorkPercent = workPercent;
    }

    @Override
    public String toString() {
        return "SingleCustomerData{" +
                "WorkSort='" + WorkSort + '\'' +
                ", WorkName='" + WorkName + '\'' +
                ", WorkAnalysis='" + WorkAnalysis + '\'' +
                ", WorkPercent='" + WorkPercent + '\'' +
                '}';
    }
}
