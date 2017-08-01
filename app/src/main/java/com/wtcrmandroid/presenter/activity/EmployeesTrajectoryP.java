package com.wtcrmandroid.presenter.activity;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.model.reponsedata.StaffPositionRP;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.utils.L;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wt-pc on 2017/7/26.
 * 员工位置
 */

public class EmployeesTrajectoryP extends BasePresenter {
    public EmployeesTrajectoryP(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {
        L.e(response);
        List<StaffPositionRP> list=new ArrayList<>();
        Type listType = new TypeToken<List<StaffPositionRP>>() {
        }.getType();
        if(response!=null||!response.equals(""))
            list = new Gson().fromJson(response, listType);
        view.returnData(key,list);
    }
    public void getData(Object object,int key){
        post("OutSide/listTodayPosition",object,key);
    }
}
