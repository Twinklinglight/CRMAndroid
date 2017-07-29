package com.wtcrmandroid.activity.journalmanager.present;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iflytek.cloud.thirdparty.T;
import com.wtcrmandroid.model.requestdata.XsWriteDaysumRQ;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by zxd on 2017/7/17.
 */

public class XsWriteDaysumPresenter extends BasePresenter {

    public XsWriteDaysumPresenter(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {

        Type listType = new TypeToken<List<T>>(){}.getType();
        List<T> list = new Gson().fromJson(response,listType);
        view.returnData(key,list);
    }

    public void postDaysum(XsWriteDaysumRQ daysumRQ){

        post("WorkPlan/saveWorkPlan",daysumRQ,1);
    }
}
