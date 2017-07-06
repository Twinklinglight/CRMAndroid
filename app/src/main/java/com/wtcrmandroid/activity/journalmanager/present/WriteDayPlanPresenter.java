package com.wtcrmandroid.activity.journalmanager.present;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.model.reponsedata.WjournalData;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;

/**
 * Created by ZSC on 2017/6/27.
 */

public class WriteDayPlanPresenter extends BasePresenter {


    public WriteDayPlanPresenter(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {

        Type listType = new TypeToken<WjournalData>() {
        }.getType();
        WjournalData wjournalData = new Gson().fromJson(response, listType);

        view.returnData(key,wjournalData);

    }

    /**
     * 提交后台日计划
     */
    public void SubDayPlan(Object list){
        post("WorkPlan/saveWorkPlan",list,0);
    }
}
