package com.wtcrmandroid.presenter.activity;

import android.content.Context;

import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

/**
 * Created by wt-pc on 2017/6/22.
 */

public class FieldClockPresenter extends BasePresenter {


    public FieldClockPresenter(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {
        view.returnData(key,response);
    }

    /**
     * 打卡
     * @param object
     * @param key 返回数据标记
     */
    public void clock(Object object,int key){
        post("OutSide/savePosition",object,key);
    }
    /**
     * 打卡记录
     * @param object
     * @param key 返回数据标记
     */
    public void clockRecord(Object object,int key){
        post("OutSide/listPersonTodaySignIn",object,key);
    }
}
