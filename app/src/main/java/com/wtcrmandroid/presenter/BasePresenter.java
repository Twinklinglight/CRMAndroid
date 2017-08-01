package com.wtcrmandroid.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.wtcrmandroid.Const;
import com.wtcrmandroid.httpfactory.HttpRequest;
import com.wtcrmandroid.httpfactory.callback.StringCallBack;
import com.wtcrmandroid.utils.L;
import com.wtcrmandroid.view.AllView;
import com.wtcrmandroid.view.dialog.LoadingDialog;

/**
 * Created by 1363655717 on 2017-06-12.
 * 基类
 */

public abstract class BasePresenter {
    protected AllView view;
    LoadingDialog loadingDialog;
    protected Handler mainHandler;
    protected Context context;
    public BasePresenter(AllView view, Context context) {
        this.view = view;
        mainHandler = new Handler(Looper.getMainLooper());
        loadingDialog=new LoadingDialog(context);
        this.context=context;

    }

    protected abstract void returnData(int key, String response);

    protected void post(String url, Object params, final int key) {
        loadingDialog.show();

        HttpRequest.instance().sendPost(Const.http + url, params, null, new StringCallBack() {
            @Override
            public void onError(int errorRet, final String errorMsg) {

                loadingDialog.dismiss();
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //已在主线程中，可以更新UI
                        view.showShortToast(errorMsg);



                    }
                });
            }

            @Override
            public void onResponse(final String response) {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //已在主线程中，可以更新UI
                        returnData(key, response);
                        loadingDialog.dismiss();
                    }
                });


            }

            @Override
            public void onNetError(Exception e) {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //已在主线程中，可以更新UI
                        view.showShortToast("网络错误！");
                    }
                });

                L.e("BasePresenter.onNetError(Exception e)"+e.toString());
                loadingDialog.dismiss();
            }
        });
    }
}
