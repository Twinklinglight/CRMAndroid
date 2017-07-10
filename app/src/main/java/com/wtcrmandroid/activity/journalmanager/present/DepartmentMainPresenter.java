package com.wtcrmandroid.activity.journalmanager.present;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.model.reponsedata.DepartmentRponseData;
import com.wtcrmandroid.model.requestdata.DepartmentRquestData;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by zxd on 2017/7/10.
 */

public class DepartmentMainPresenter extends BasePresenter {

    public DepartmentMainPresenter(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {

        Type type = new TypeToken<List<DepartmentRponseData>>() {
        }.getType();
        List<DepartmentRponseData> list = new Gson().fromJson(response,type);
        view.returnData(key,list);
    }

    /**
     * 部门员工列表网络请求
     */
    public void postDepartment(DepartmentRquestData data){

        post("WorkPlan/listEmployeeMessage",data,1);
    }
}
