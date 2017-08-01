package com.wtcrmandroid.contacts.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.model.reponsedata.ContactsDpmentRP;
import com.wtcrmandroid.model.requestdata.MineRQ;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by zxd on 2017/7/29.
 */

public class ContactDepartPresenter extends BasePresenter {

    public ContactDepartPresenter(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {

        Type type = new TypeToken<List<ContactsDpmentRP>>() {
        }.getType();
        List<ContactsDpmentRP> list = new Gson().fromJson(response,type);
        view.returnData(key,list);
    }

    public void postDepartment(MineRQ rqData){
        post("AddressList/listDeptUser",rqData,1);
    }
}
