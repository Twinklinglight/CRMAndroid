package com.wtcrmandroid.fragment.journalmanager.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.model.reponsedata.XsDayDepartRp;
import com.wtcrmandroid.model.reponsedata.XsDayplanDetailsRP;
import com.wtcrmandroid.model.reponsedata.XsDaysumDetailsRP;
import com.wtcrmandroid.model.requestdata.CommintRQ;
import com.wtcrmandroid.model.requestdata.DayDetailsRQ;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;

/**
 * Created by zxd on 2017/7/19.
 */

public class XsDepartDayPresenter extends BasePresenter {

    public XsDepartDayPresenter(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {

        switch (key){
            case 1:
                Type type = new TypeToken<XsDayDepartRp>() {
                }.getType();
                XsDayDepartRp data = new Gson().fromJson(response, type);
                view.returnData(key,data);
                break;
            case 2:
                view.returnData(key,response);
                break;
        }
    }

    /**
     *
     * @param detailsRQ 1、dayplan 、daysum 2、commint
     */
    public void PostDepartDayplan(DayDetailsRQ detailsRQ){
        post("WorkPlan/getUserWorkPlan",detailsRQ,1);
    }

    public void PostCommint(CommintRQ data){
        post("WorkPlan/saveWorkComment",data,2);
    }
}
