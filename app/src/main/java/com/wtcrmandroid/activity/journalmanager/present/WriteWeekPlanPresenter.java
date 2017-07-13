package com.wtcrmandroid.activity.journalmanager.present;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iflytek.cloud.thirdparty.T;
import com.wtcrmandroid.model.reponsedata.WjournalData;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by 1363655717 on 2017-06-13.
 */

public class WriteWeekPlanPresenter extends BasePresenter{


    public WriteWeekPlanPresenter(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {

        Type type = new TypeToken<List<T>>() {
        }.getType();
        List<T> list = new Gson().fromJson(response,type);
        view.returnData(0,list);

    }
    public void submit(Object params){
        post("WorkPlan/saveWorkPlan",params,0);

    }
}
