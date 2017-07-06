package com.wtcrmandroid.presenter.activity;

import android.content.Context;

import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

/**
 * Created by wt-pc on 2017/7/6.
 */

public class SalePullintoCustomerPresenter extends BasePresenter {
    public SalePullintoCustomerPresenter(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {
        view.returnData(key,response);

    }
    public void sedPost(Object object, int key) {
        post("SaveCustomer/saveCustomer", object, key);
    }
}
