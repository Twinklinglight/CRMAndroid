package com.wtcrmandroid.checkupdate;

/**
 * 网络异常接口
 * Created by Mr-Zhang on 2016/6/6.
 */
public interface IOnInternetErrorModule {
    void setInternetErrorListener(InternetErrorListener internetErrorListener);

    interface InternetErrorListener {
        void netError();

        void noData();

        void timeOut();
    }
}
