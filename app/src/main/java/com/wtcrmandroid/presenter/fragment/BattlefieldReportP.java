package com.wtcrmandroid.presenter.fragment;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.model.reponsedata.SalesRankingRP;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by wt-pc on 2017/7/12.
 */

public class BattlefieldReportP extends BasePresenter{
    public BattlefieldReportP(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {
        Type listType = new TypeToken<List<SalesRankingRP>>() {
        }.getType();
        List<SalesRankingRP> list = new Gson().fromJson(response, listType);
        view.returnData(key,list);
    }
    public void getTeamRanking(Object object, int key) {
        post("SalerDispatches/listDepartmentDispathes", object, key);
    }
    public void getPersonalRanking(Object object, int key) {
        post("SalerDispatches/listSalerDispathes", object, key);
    }
}
