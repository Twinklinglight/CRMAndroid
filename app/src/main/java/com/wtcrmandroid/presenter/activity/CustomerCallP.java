package com.wtcrmandroid.presenter.activity;

import android.content.Context;

import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.utils.L;
import com.wtcrmandroid.view.AllView;

/**
 * Created by wt-pc on 2017/7/14.
 * 客户拜访
 */

public class CustomerCallP extends BasePresenter {
    public CustomerCallP(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {
        view.returnData(key, response);

    }
    public void sedPost(Object object, int key) {
        L.e(object.toString());
        post("CustomerVisitRecord/saveCustomerVisitRecord", object, key);
    }
}
