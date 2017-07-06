package com.wtcrmandroid.activity.journalmanager.present;

import android.content.Context;

import com.google.gson.Gson;
import com.wtcrmandroid.model.reponsedata.WjournalData;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

/**
 * Created by ZSC on 2017/6/30.
 */

public class WriteWeekSumPresenter extends BasePresenter {


    public WriteWeekSumPresenter(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {
        WjournalData wjournalData = new Gson().fromJson(response, WjournalData.class);
        view.returnData(key,wjournalData);
    }

    /**
     * 提交周总结
     */

    public void SubWeekSum(Object params){
        post("WorkPlan/saveWorkPlan",params,0);
    }

}
