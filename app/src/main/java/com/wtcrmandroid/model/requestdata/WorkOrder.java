package com.wtcrmandroid.model.requestdata;

import java.io.Serializable;

/**
 * Created by zxd on 2017/7/17.
 */

public class WorkOrder implements Serializable{

    /*trueCall				有效电话
    trueVisit				有效拜访
    AStore				    A类
    BStore				    B类
    newAStore				新A
    newBStore				新B
    Stroe				    库容*/

    private String trueCall;
    private String trueVisit;
    private String AStore;
    private String BStore;
    private String newAStore;
    private String newBStore;
    private String Stroe;

    public String getTrueCall() {
        return trueCall;
    }

    public void setTrueCall(String trueCall) {
        this.trueCall = trueCall;
    }

    public String getTrueVisit() {
        return trueVisit;
    }

    public void setTrueVisit(String trueVisit) {
        this.trueVisit = trueVisit;
    }

    public String getAStore() {
        return AStore;
    }

    public void setAStore(String AStore) {
        this.AStore = AStore;
    }

    public String getBStore() {
        return BStore;
    }

    public void setBStore(String BStore) {
        this.BStore = BStore;
    }

    public String getNewAStore() {
        return newAStore;
    }

    public void setNewAStore(String newAStore) {
        this.newAStore = newAStore;
    }

    public String getNewBStore() {
        return newBStore;
    }

    public void setNewBStore(String newBStore) {
        this.newBStore = newBStore;
    }

    public String getStroe() {
        return Stroe;
    }

    public void setStroe(String stroe) {
        Stroe = stroe;
    }

    @Override
    public String toString() {
        return "WorkOrder{" +
                "trueCall=" + trueCall +
                ", trueVisit=" + trueVisit +
                ", AStore=" + AStore +
                ", BStore=" + BStore +
                ", newAStore=" + newAStore +
                ", newBStore=" + newBStore +
                ", Stroe=" + Stroe +
                '}';
    }
}
