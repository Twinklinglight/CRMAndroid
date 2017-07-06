package com.wtcrmandroid.fragment.battlefieldreport.presenter;

import android.content.Context;

import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

/**
 * Created by wt-pc on 2017/7/1.
 * t
 * 团队排名
 */

public class TeamRankingPresenter extends BasePresenter {


    public TeamRankingPresenter(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {

    }
    public void getData(Object object,int key){
        post("SalerDispatches/listDepartmentDispathes",object,key);
    }
}
