package com.wtcrmandroid.presenter.activity;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.model.reponsedata.PersonalAllRecordRP;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wt-pc on 2017/7/17.
 * 我的拜访记录
 */

public class MyCallRecordP extends BasePresenter {
    public MyCallRecordP(AllView view, Context context) {
        super(view, context);
    }


    @Override
    protected void returnData(int key, String response) {
        List<PersonalAllRecordRP> list=new ArrayList<>();
        Type listType = new TypeToken<List<PersonalAllRecordRP>>() {
        }.getType();
        if(response!=null||!response.equals(""))
            list = new Gson().fromJson(response, listType);
        view.returnData(key,list);
    }
    public void getData(Object object,int key){
        post("CustomerVisitRecord/listEmployeeVisitRecord",object,key);
    }
}
