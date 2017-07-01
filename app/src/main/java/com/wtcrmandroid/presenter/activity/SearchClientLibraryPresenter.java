package com.wtcrmandroid.presenter.activity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.model.reponsedata.SearchCustomerReponseData;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wt-pc on 2017/6/30.
 */

public class SearchClientLibraryPresenter extends BasePresenter {
    public SearchClientLibraryPresenter(AllView view) {
        super(view);
    }

    @Override
    protected void returnData(int key, String response) {
        List<SearchCustomerReponseData> list=new ArrayList<>();
        Type listType = new TypeToken<List<SearchCustomerReponseData>>() {
        }.getType();
        if(response!=null||!response.equals(""))
            list = new Gson().fromJson(response, listType);
        view.returnData(key,list);
    }
    public void searchCompany(Object object,int key){
        post("AllCustomerList/searchCustomerByCompanyName",object,key);
    }

}