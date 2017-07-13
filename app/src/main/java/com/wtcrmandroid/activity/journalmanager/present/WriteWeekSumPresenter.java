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
 * Created by ZSC on 2017/6/30.
 */

public class WriteWeekSumPresenter extends BasePresenter {


    public WriteWeekSumPresenter(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {
        Type type = new TypeToken<List<T>>() {
        }.getType();
        List<T> list = new Gson().fromJson(response, type);
        view.returnData(key, list);
    }

    /**
     * 提交周总结
     */

    public void SubWeekSum(Object params){
        post("WorkPlan/saveWorkPlan",params,0);
    }

}
