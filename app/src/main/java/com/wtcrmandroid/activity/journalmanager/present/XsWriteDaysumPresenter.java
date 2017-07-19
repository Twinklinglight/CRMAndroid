package com.wtcrmandroid.activity.journalmanager.present;

import android.content.Context;

import com.wtcrmandroid.model.requestdata.XsWriteDaysumRQ;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

/**
 * Created by zxd on 2017/7/17.
 */

public class XsWriteDaysumPresenter extends BasePresenter {

    public XsWriteDaysumPresenter(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {

    }

    public void postDaysum(XsWriteDaysumRQ daysumRQ){

        post("WorkPlan/saveWorkPlan",daysumRQ,1);
    }
}
