package com.wtcrmandroid.fragment.journalmanager.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.model.WriterWeekPlaneData;
import com.wtcrmandroid.model.reponsedata.WeekSumDetailsRpData;
import com.wtcrmandroid.model.requestdata.CommintRQ;
import com.wtcrmandroid.model.requestdata.DayDetailsRQ;
import com.wtcrmandroid.model.requestdata.WeekDetailsRequestData;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by zxd on 2017/7/10.
 */

public class DepartWeekPresenter extends BasePresenter {


    public DepartWeekPresenter(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {

        switch (key){
            case 1:
                Type listtype = new TypeToken<List<WriterWeekPlaneData>>() {
                }.getType();
                List<WriterWeekPlaneData> list = new Gson().fromJson(response,listtype);
                view.returnData(1,list);
                break;
            case 2:
                Type type = new TypeToken<WeekSumDetailsRpData>() {}.getType();
                WeekSumDetailsRpData weekSumDetailsRpData =  new Gson().fromJson(response, type);
                view.returnData(key,weekSumDetailsRpData);
                break;
            case 3:
                view.returnData(3,response);
                break;
        }

    }

    /**
     * 部门员工周 日志详情数据请求 1,周计划 2.周总结 3.提交评论
     */
    public void postDepartWeek(WeekDetailsRequestData o){
        post("WorkPlan/getUserWorkPlan",o,1);
    }

    public void postWeeksum(WeekDetailsRequestData data){
        post("WorkPlan/getUserWorkPlan",data,2);
    }

    public void postWeekCommint(CommintRQ data){

        post("WorkPlan/saveWorkComment",data,3);
    }

}
