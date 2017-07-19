package com.wtcrmandroid.model.reponsedata;

import com.wtcrmandroid.model.AddPurpostCtAtData;
import com.wtcrmandroid.model.GetMoneyData;
import com.wtcrmandroid.model.GetSingleCustomerData;
import com.wtcrmandroid.model.requestdata.WorkOrder;

import java.util.List;

/**
 * Created by zxd on 2017/7/19.
 */

public class XsDausumWorkData {

    private List<HtDaysumDetailsData> workdetail;
    private List<WorkOrder> workorder;
    private List<GetMoneyData>workload;
    private List<GetSingleCustomerData>workdream;
    private List<AddPurpostCtAtData>addcustinfo;

    public List<HtDaysumDetailsData> getWorkdetail() {
        return workdetail;
    }

    public void setWorkdetail(List<HtDaysumDetailsData> workdetail) {
        this.workdetail = workdetail;
    }

    public List<WorkOrder> getWorkorder() {
        return workorder;
    }

    public void setWorkorder(List<WorkOrder> workorder) {
        this.workorder = workorder;
    }

    public List<GetMoneyData> getWorkload() {
        return workload;
    }

    public void setWorkload(List<GetMoneyData> workload) {
        this.workload = workload;
    }

    public List<GetSingleCustomerData> getWorkdream() {
        return workdream;
    }

    public void setWorkdream(List<GetSingleCustomerData> workdream) {
        this.workdream = workdream;
    }

    public List<AddPurpostCtAtData> getAddcustinfo() {
        return addcustinfo;
    }

    public void setAddcustinfo(List<AddPurpostCtAtData> addcustinfo) {
        this.addcustinfo = addcustinfo;
    }
}
