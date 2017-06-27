package com.wtcrmandroid.presenter.activity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.model.reponsedata.SearchCustomerReponseData;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.utils.L;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by wt-pc on 2017/6/26.
 */

public class MainClientLibraryPresenter extends BasePresenter {
    public MainClientLibraryPresenter(AllView view) {
        super(view);
    }

    @Override
    protected void returnData(int key, String response) {
        Type listType = new TypeToken<List<SearchCustomerReponseData>>() {
        }.getType();
        List<SearchCustomerReponseData> list = new Gson().fromJson(response, listType);
        L.e("成功" + list.get(0).getSite());
        view.returnData(0,list);

    }
    public void getData(Object object){
        post("AllCustomerList/seachCustomer",object,0);
    }
}
