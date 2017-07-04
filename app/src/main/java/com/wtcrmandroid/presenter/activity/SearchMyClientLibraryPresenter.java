package com.wtcrmandroid.presenter.activity;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.model.reponsedata.SearchSalerCustomerRP;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.utils.L;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wt-pc on 2017/6/30.
 */

public class SearchMyClientLibraryPresenter extends BasePresenter {


    public SearchMyClientLibraryPresenter(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {
        L.e("成功" + response);
        List<SearchSalerCustomerRP> list=new ArrayList<>();
        Type listType = new TypeToken<List<SearchSalerCustomerRP>>() {
        }.getType();
        if(response!=null||!response.equals(""))
            list = new Gson().fromJson(response, listType);
        view.returnData(key,list);
    }
    public void searchCompany(Object object,int key){
        post("SalerCustomer/searchSalerCustomerByCompany",object,key);
    }

}
