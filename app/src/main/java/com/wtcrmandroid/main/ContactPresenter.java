package com.wtcrmandroid.main;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.model.reponsedata.ContactRP;
import com.wtcrmandroid.model.requestdata.MineRQ;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by zxd on 2017/7/29.
 */

public class ContactPresenter extends BasePresenter {

    public ContactPresenter(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {
        Type type = new TypeToken<List<ContactRP>>() {
        }.getType();
        List<ContactRP> list = new Gson().fromJson(response, type);
        view.returnData(key,list);
    }

    public void postContactAll(MineRQ data){
        post("AddressList/listEmployees",data,1);
    }
}
