package com.wtcrmandroid.activity.journalmanager.present;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.model.reponsedata.HtDayplanDetailsData;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by ZSC on 2017/7/1.
 */

public class HtDayplanDetailsPresenter extends BasePresenter {


    public HtDayplanDetailsPresenter(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {

        Type listType = new TypeToken<List<HtDayplanDetailsData>>(){}.getType();
        List<HtDayplanDetailsData> DdDetailsRd = new Gson().fromJson(response, listType);
        view.returnData(0,DdDetailsRd);
    }

    /**
     * 获取日计划列表
     */

    public void getDayPlanData(Object o){

        post("WorkPlan/getUserWorkPlan",o,0);

    }

}
