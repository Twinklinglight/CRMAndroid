package com.wtcrmandroid.activity.journalmanager.present;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.model.WriterWeekPlaneData;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by ZSC on 2017/7/1.
 */

public class WeekPlanDetailsPresenter extends BasePresenter {

    public WeekPlanDetailsPresenter(AllView view) {
        super(view);
    }

    @Override
    protected void returnData(int key, String response) {

        Type type = new TypeToken<List<WriterWeekPlaneData>>() {
        }.getType();
        List<WriterWeekPlaneData> list = new Gson().fromJson(response, type);

        view.returnData(0,list);
    }

    /**
     * 周计划详情数据获取
     */
    public void GetWeekPlanData(Object o){

        post("WorkPlan/getUserWorkPlan",o,0);
    }
}
