package com.wtcrmandroid.presenter.activity;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.model.reponsedata.PushCustomerRP;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.utils.L;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wt-pc on 2017/6/26.
 * 我的地推客户
 */

public class MyPushCustomerP extends BasePresenter {


    public MyPushCustomerP(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {
        L.e("成功" + response);
        List<PushCustomerRP> list=new ArrayList<>();
        Type listType = new TypeToken<List<PushCustomerRP>>() {
        }.getType();
        if(response!=null||!response.equals(""))
            list = new Gson().fromJson(response, listType);
        view.returnData(key,list);
    }
    public void getData(Object object,int key){
        post("SalerCustomer/searchToPushHuiYuan",object,key);
    }
}
