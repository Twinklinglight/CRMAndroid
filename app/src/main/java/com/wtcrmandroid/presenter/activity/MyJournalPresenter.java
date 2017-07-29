package com.wtcrmandroid.presenter.activity;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.model.reponsedata.MyjournalRponseData;
import com.wtcrmandroid.model.requestdata.MyJournalRequestData;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by 1363655717 on 2017-06-13.
 */

public class MyJournalPresenter extends BasePresenter{


    public MyJournalPresenter(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {

        Type type = new TypeToken<List<MyjournalRponseData>>() {
        }.getType();

        List<MyjournalRponseData> ReponseData = new Gson().fromJson(response, type);
        view.returnData(key,ReponseData);

    }

    /**
     * 获取我的日志列表 type = 1 获取当前
     */
    public void getData(MyJournalRequestData requestData ,int type){
        post("WorkPlan/getUsersWorkPlan",requestData,type);
    }

}
