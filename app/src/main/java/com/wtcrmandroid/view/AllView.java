package com.wtcrmandroid.view;

/**
 * Created by 1363655717 on 2017-05-31.
 */

public interface AllView<T>{
    /**
     * 显示吐司
     * @param text 吐司显示文本
     */
    void showToast(String text);

    /**
     * 网路请求返回数据
     * @param data 数据
     * @param key
     */

    void returnData(int key,T data);



}
