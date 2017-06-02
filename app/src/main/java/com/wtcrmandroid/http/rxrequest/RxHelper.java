package com.wtcrmandroid.http.rxrequest;


import com.wtcrmandroid.http.data.BaseData;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @className:RxHelper
 * @classDescription: Rx 一些巧妙的处理
 * @author: leibing
 * @createTime: 2016/8/12
 */
public class RxHelper {

    /**
     * 对结果进行预处理
     * @author 申中佳
     * @createTime 2016/8/12
     * @lastModify 2016/8/12
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<BaseData<T>, T> handleResult() {
        return new Observable.Transformer<BaseData<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseData<T>> tObservable) {
                return tObservable.flatMap(new Func1<BaseData<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(BaseData<T> result) {
                        if (result.success()) {
                            return createData(result.spread);
                        } else {
                            return Observable.error(new ServerException(result.resultdesc));
                        }
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }

        };

    }

    /**
     * 创建成功的数据
     * @author 申中佳
     * @createTime 2016/8/12
     * @lastModify 2016/8/12
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
