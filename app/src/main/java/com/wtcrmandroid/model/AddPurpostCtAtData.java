package com.wtcrmandroid.model;

/**
 * Created by zxd on 2017/6/14
 */

public class AddPurpostCtAtData {

    private String WorkSort;
    private String CustomerName;
    private String AnalysisGjin;

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

    public String getAnalysisGjin() {
        return AnalysisGjin;
    }

    public void setAnalysisGjin(String analysisGjin) {
        AnalysisGjin = analysisGjin;
    }

    @Override
    public String toString() {
        return "AddPurpostCtAtData{" +
                "WorkSort='" + WorkSort + '\'' +
                ", CustomerName='" + CustomerName + '\'' +
                ", AnalysisGjin='" + AnalysisGjin + '\'' +
                '}';
    }
}
