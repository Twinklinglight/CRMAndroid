package com.wtcrmandroid.fragment.journalmanager.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.model.reponsedata.HtDayplanDetailsData;
import com.wtcrmandroid.model.reponsedata.DaySumDetailsRpData;
import com.wtcrmandroid.model.requestdata.CommintRQ;
import com.wtcrmandroid.model.requestdata.DayDetailsRQ;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by zxd on 2017/7/10.
 */

public class DepartDayPresenter extends BasePresenter {

    public DepartDayPresenter(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {

        switch (key){
            case 1:
                Type listType = new TypeToken<List<HtDayplanDetailsData>>(){}.getType();
                List<HtDayplanDetailsData> DdDetailsRd = new Gson().fromJson(response, listType);
                view.returnData(1,DdDetailsRd);
                break;
            case 2:
                Type type = new  TypeToken<DaySumDetailsRpData>(){}.getType();
                DaySumDetailsRpData daysumData = new Gson().fromJson(response, type);
                view.returnData(2,daysumData);
                break;
            case 3:
                view.returnData(3,response);
                break;
        }
    }

    /**
     * 部门员工日 日志详情数据请求 1,日计划 2.日总结 3.提交评论
     */
    public void postDepartDay(DayDetailsRQ o){
        post("WorkPlan/getUserWorkPlan",o,1);
    }

    public void postDaysum(DayDetailsRQ data){
        post("WorkPlan/getUserWorkPlan",data,2);
    }

    public void postDayCommint(CommintRQ data){

        post("WorkPlan/saveWorkComment",data,3);
    }

}
