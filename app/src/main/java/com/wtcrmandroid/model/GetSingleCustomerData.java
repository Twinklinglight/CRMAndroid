package com.wtcrmandroid.model;

/**
 * Created by zxd on 2017/6/14
 */

public class GetSingleCustomerData {

    private String WorkSort;
    private String WorkName;
    private String ReasonAnalysis;
    private String Genjinplan;
    private String ifGet;

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

    public String getReasonAnalysis() {
        return ReasonAnalysis;
    }

    public void setReasonAnalysis(String reasonAnalysis) {
        ReasonAnalysis = reasonAnalysis;
    }

    public String getGenjinplan() {
        return Genjinplan;
    }

    public void setGenjinplan(String genjinplan) {
        Genjinplan = genjinplan;
    }

    public String getIfGet() {
        return ifGet;
    }

    public void setIfGet(String ifGet) {
        this.ifGet = ifGet;
    }

    @Override
    public String toString() {
        return "GetSingleCustomerData{" +
                "WorkSort='" + WorkSort + '\'' +
                ", WorkName='" + WorkName + '\'' +
                ", ReasonAnalysis='" + ReasonAnalysis + '\'' +
                ", Genjinplan='" + Genjinplan + '\'' +
                ", ifGet='" + ifGet + '\'' +
                '}';
    }
}
