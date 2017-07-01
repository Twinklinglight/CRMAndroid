package com.wtcrmandroid.activity.journalmanager.present;

import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

/**
 * Created by ZSC on 2017/7/1.
 */

public class HtDayplanDetailsPresenter extends BasePresenter {

    public HtDayplanDetailsPresenter(AllView view) {
        super(view);
    }

    @Override
    protected void returnData(int key, String response) {

    }

    /**
     * 获取日计划列表
     */

    public void getDayPlanData(Object o){

        post("",o,0);

    }

}
