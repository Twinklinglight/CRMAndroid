package com.wtcrmandroid.presenter.activity;

import com.wtcrmandroid.model.WriteDayplanData;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

import java.util.List;

/**
 * Created by ZSC on 2017/6/27.
 */

public class WriteDayPlanPresenter extends BasePresenter {

    public WriteDayPlanPresenter(AllView view) {
        super(view);
    }

    @Override
    protected void returnData(int key, String response) {

    }

    /**
     * 提交后台日计划
     */
    public void SubDayPlan(List<WriteDayplanData> list){
        post("WorkPlan/saveWorkPlan",list,0);
    }
}
