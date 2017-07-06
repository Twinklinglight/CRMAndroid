package com.wtcrmandroid.activity.journalmanager.present;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.model.reponsedata.DaySumDetailsRpData;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;

/**
 * Created by zxd on 2017/7/3.
 */

public class HtDaysumDetailsPresenter extends BasePresenter {


    public HtDaysumDetailsPresenter(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {
        Type type = new  TypeToken<DaySumDetailsRpData>(){}.getType();
        DaySumDetailsRpData daysumData = new Gson().fromJson(response, type);
        view.returnData(0,daysumData);
    }

    /**
     * 获取日总结详情请求
     */
    public void GetDaySumDetails(Object o){
        post("WorkPlan/getUserWorkPlan",o,0);
    }
}
