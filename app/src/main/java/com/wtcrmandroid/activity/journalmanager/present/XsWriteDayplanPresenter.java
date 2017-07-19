package com.wtcrmandroid.activity.journalmanager.present;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iflytek.cloud.thirdparty.T;
import com.wtcrmandroid.model.requestdata.XsWriteDayplanRQ;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by zxd on 2017/7/17.
 */

public class XsWriteDayplanPresenter extends BasePresenter {

    public XsWriteDayplanPresenter(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {

        Type type = new TypeToken<List<T>>() {
        }.getType();

        List<T> list = new Gson().fromJson(response, type);
        view.returnData(key,list);
    }

    public void postDayplan(XsWriteDayplanRQ data){
        post("WorkPlan/saveWorkPlan",data,1);
    }
}
