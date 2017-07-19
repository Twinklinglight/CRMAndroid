package com.wtcrmandroid.presenter.activity;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.model.reponsedata.VisitStatisticalRP;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by wt-pc on 2017/7/14.
 * 客户拜访统计
 */

public class CustomerCallStatisticalP extends BasePresenter {
    public CustomerCallStatisticalP(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {
        Type listType = new TypeToken< List<VisitStatisticalRP>>() {
        }.getType();
        List<VisitStatisticalRP> list = new Gson().fromJson(response, listType);
        view.returnData(key,list);
    }
    public void sedPost(Object object, int key) {
        post("CustomerVisitRecord/listUserVisitCount", object, key);
    }
}
