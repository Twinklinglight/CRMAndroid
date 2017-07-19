package com.wtcrmandroid.activity.journalmanager.present;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.model.reponsedata.XsDaysumDetailsRP;
import com.wtcrmandroid.model.requestdata.DayDetailsRQ;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;

/**
 * Created by zxd on 2017/7/19.
 */

public class XsDaysumDetailsPresenter extends BasePresenter {


    public XsDaysumDetailsPresenter(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {

        Type type = new TypeToken<XsDaysumDetailsRP>() {
        }.getType();
        XsDaysumDetailsRP rpData = new Gson().fromJson(response, type);
        view.returnData(key,rpData);
    }


    public void postDaysumData(DayDetailsRQ data){

        post("WorkPlan/getUserWorkPlan",data,1);
    }
}
