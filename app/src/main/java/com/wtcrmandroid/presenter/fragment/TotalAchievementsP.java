package com.wtcrmandroid.presenter.fragment;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.model.reponsedata.TotalAchievementsRP;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by wt-pc on 2017/7/12.
 */

public class TotalAchievementsP extends BasePresenter {
    public TotalAchievementsP(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {
        Type listType = new TypeToken<List<TotalAchievementsRP>>() {
        }.getType();
        List<TotalAchievementsRP> list = new Gson().fromJson(response, listType);
        view.returnData(key,list.get(0));
    }
    public void getTotalAchievements(Object object, int key) {
        post("SalerDispatches/getTotalPerformance", object, key);
    }
}
