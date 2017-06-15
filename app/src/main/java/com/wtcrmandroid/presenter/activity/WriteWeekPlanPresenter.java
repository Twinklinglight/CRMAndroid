package com.wtcrmandroid.presenter.activity;

import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

import java.util.HashMap;

/**
 * Created by 1363655717 on 2017-06-13.
 */

public class WriteWeekPlanPresenter extends BasePresenter{
    public WriteWeekPlanPresenter(AllView view) {
        super(view);
    }

    @Override
    protected void returnData(int key, String response) {

    }
    public void submit(HashMap<String, Object> params){
        post("http://192.168.0.7/api/WorkPlan/saveWorkPlan",params,0);

    }
}
