package com.wtcrmandroid.presenter.activity;

import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.utils.L;
import com.wtcrmandroid.view.AllView;

/**
 * Created by wt-pc on 2017/6/26.
 */

public class ClockRecordPresenter extends BasePresenter {
    public ClockRecordPresenter(AllView view) {
        super(view);
    }

    @Override
    protected void returnData(int key, String response) {
        L.e("成功" + response);
    }
    public void sedPost(Object object,int key){
        post("OutSide/listPersonSignIn",object,0);
    }
}
