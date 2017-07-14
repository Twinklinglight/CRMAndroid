package com.wtcrmandroid.presenter.activity;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.model.reponsedata.CompanyVisitDetailsRP;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by wt-pc on 2017/7/14.
 * 客户拜访情况
 */

public class CustomerCallSituationP extends BasePresenter {
    public CustomerCallSituationP(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {
        Type listType = new TypeToken< List<CompanyVisitDetailsRP>>() {
        }.getType();
        List<CompanyVisitDetailsRP> list = new Gson().fromJson(response, listType);
        view.returnData(key,list);
    }
    public void sedPost(Object object, int key) {
        post("CustomerVisitRecord/listCustomerVisit", object, key);
    }
}
