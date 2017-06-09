package com.wtcrmandroid.httpfactory.callback;

import okhttp3.Response;

/**
 * 不做任何处理的callbaack
 * Created by wutongS on 2016/4/26.
 */
public abstract class RetCallBack extends ResultCallBack {

    @Override
    public void onResponse(Object response) {
        if(response instanceof Response){
            Response r = (Response) response;
            onResponse(r.body().toString());
        }
    }

    public abstract void onResponse(String response);
}
