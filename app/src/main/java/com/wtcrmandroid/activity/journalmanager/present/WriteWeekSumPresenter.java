package com.wtcrmandroid.activity.journalmanager.present;

import com.google.gson.Gson;
import com.wtcrmandroid.model.reponsedata.WjournalData;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

/**
 * Created by ZSC on 2017/6/30.
 */

public class WriteWeekSumPresenter extends BasePresenter {

    public WriteWeekSumPresenter(AllView view) {
        super(view);
    }

    @Override
    protected void returnData(int key, String response) {
        WjournalData wjournalData = new Gson().fromJson(response, WjournalData.class);
        view.returnData(0,wjournalData);
    }

    /**
     * 提交周总结
     */

    public void SubWeekSum(Object params){
        post("WorkPlan/saveWorkPlan",params,0);
    }

}
