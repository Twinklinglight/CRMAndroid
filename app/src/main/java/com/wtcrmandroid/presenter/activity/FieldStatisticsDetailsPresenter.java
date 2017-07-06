package com.wtcrmandroid.presenter.activity;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.model.reponsedata.ClockRecordRP;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by wt-pc on 2017/7/4.
 * 外勤统计
 */

public class FieldStatisticsDetailsPresenter extends BasePresenter {
    public FieldStatisticsDetailsPresenter(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {
        Type listType = new TypeToken<List<ClockRecordRP>>() {
        }.getType();
        List<ClockRecordRP> list = new Gson().fromJson(response, listType);
        view.returnData(key,list);

    }
    public void sedPost(Object object, int key) {
        post("OutSide/listSignInDetail", object, key);
    }
}
