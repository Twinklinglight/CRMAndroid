package com.wtcrmandroid.model.reponsedata;

import com.wtcrmandroid.model.MajorCustomerData;
import com.wtcrmandroid.model.SingleCustomerData;

import java.util.List;

/**
 * Created by zxd on 2017/7/18.
 */

public class XsDayplanDetailsRP {

    private List<HtDayplanDetailsData>workdetail;
    private List<SingleCustomerData>workDreamOrder;
    private List<MajorCustomerData>workFocus;

    public List<HtDayplanDetailsData> getWorkdetail() {
        return workdetail;
    }

    public void setWorkdetail(List<HtDayplanDetailsData> workdetail) {
        this.workdetail = workdetail;
    }

    public List<SingleCustomerData> getWorkDreamOrder() {
        return workDreamOrder;
    }

    public void setWorkDreamOrder(List<SingleCustomerData> workDreamOrder) {
        this.workDreamOrder = workDreamOrder;
    }

    public List<MajorCustomerData> getWorkFocus() {
        return workFocus;
    }

    public void setWorkFocus(List<MajorCustomerData> workFocus) {
        this.workFocus = workFocus;
    }
}
