package com.wtcrmandroid.presenter.activity;

import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.utils.L;
import com.wtcrmandroid.view.AllView;

/**
 * Created by wt-pc on 2017/6/26.
 */

public class MyClientLibraryPresenter extends BasePresenter {
    public MyClientLibraryPresenter(AllView view) {
        super(view);
    }

    @Override
    protected void returnData(int key, String response) {
        L.e("成功" + response);
    }
    public void getData(Object object){
        post("SalerCustomer/searchSalerCustomer",object,0);
    }
}
