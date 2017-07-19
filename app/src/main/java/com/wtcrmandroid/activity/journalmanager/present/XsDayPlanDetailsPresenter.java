package com.wtcrmandroid.activity.journalmanager.present;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.model.reponsedata.XsDayplanDetailsRP;
import com.wtcrmandroid.model.requestdata.DayDetailsRQ;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;

/**
 * Created by zxd on 2017/7/18.
 */

public class XsDayPlanDetailsPresenter extends BasePresenter{

    public XsDayPlanDetailsPresenter(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {

        Type type = new TypeToken<XsDayplanDetailsRP>() {
        }.getType();
        XsDayplanDetailsRP data = new Gson().fromJson(response, type);
        view.returnData(key,data);
    }

    public void postWpDetails(DayDetailsRQ detailsRQ){

        post("WorkPlan/getUserWorkPlan",detailsRQ,1);
    }
}
