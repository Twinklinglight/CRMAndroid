package com.wtcrmandroid.presenter.activity;

import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.utils.L;
import com.wtcrmandroid.view.AllView;

/**
 * Created by wt-pc on 2017/6/22.
 */

public class FieldClockPresenter extends BasePresenter {
    public FieldClockPresenter(AllView view) {
        super(view);
    }

    @Override
    protected void returnData(int key, String response) {
        L.e("成功" + response);
    }
    public void sedPost(Object object){
        post("OutSide/savePosition",object,0);
    }
}
