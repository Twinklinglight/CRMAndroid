package com.wtcrmandroid.presenter;

import com.wtcrmandroid.Const;
import com.wtcrmandroid.httpfactory.HttpRequest;
import com.wtcrmandroid.httpfactory.callback.StringCallBack;
import com.wtcrmandroid.utils.L;
import com.wtcrmandroid.view.AllView;

/**
 * Created by 1363655717 on 2017-06-12.
 * 基类
 *
 */

public abstract class BasePresenter {
    protected AllView view;
    public BasePresenter(AllView view) {
        this.view=view;
    }
    protected abstract void returnData(int key,String response);
    protected void post(String url,  Object params, final int key){
        HttpRequest.instance().sendPost(Const.http+url, params,null, new StringCallBack() {
            @Override
            public void onError(int errorRet, String errorMsg) {
                L.e(errorMsg);
            }

            @Override
            public void onResponse(String response) {
                returnData(key,response);

            }

            @Override
            public void onNetError(Exception e) {
                L.e("出错了");
            }
        });
    }
}
