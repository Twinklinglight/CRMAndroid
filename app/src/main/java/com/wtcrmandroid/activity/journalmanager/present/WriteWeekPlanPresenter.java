package com.wtcrmandroid.activity.journalmanager.present;

import com.google.gson.Gson;
import com.wtcrmandroid.model.reponsedata.WjournalData;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

import java.util.HashMap;

/**
 * Created by 1363655717 on 2017-06-13.
 */

public class WriteWeekPlanPresenter extends BasePresenter{
    public WriteWeekPlanPresenter(AllView view) {
        super(view);
    }

    @Override
    protected void returnData(int key, String response) {

        WjournalData wjournalData = new Gson().fromJson(response, WjournalData.class);
        view.returnData(0,wjournalData);

    }
    public void submit(Object params){
        post("WorkPlan/saveWorkPlan",params,0);

    }
}
