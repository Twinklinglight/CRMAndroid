package com.wtcrmandroid.presenter.activity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.model.reponsedata.SearchSalerCustomerReponseData;
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
    public SearchMyClientLibraryPresenter(AllView view) {
        super(view);
    }

    @Override
    protected void returnData(int key, String response) {
        L.e("成功" + response);
        List<SearchSalerCustomerReponseData> list=new ArrayList<>();
        Type listType = new TypeToken<List<SearchSalerCustomerReponseData>>() {
        }.getType();
        if(response!=null||!response.equals(""))
            list = new Gson().fromJson(response, listType);
        view.returnData(key,list);
    }
    public void searchCompany(Object object,int key){
        post("SalerCustomer/searchSalerCustomerByCompany",object,key);
    }

}
