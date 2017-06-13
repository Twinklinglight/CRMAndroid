package com.wtcrmandroid.data;

import java.io.Serializable;

/**
 * Created by 1363655717 on 2017-05-31.
 * 网络返回数据实体基类
 */

public class BaseData<T> implements Serializable {
    public String ret;
    public String msg;
    public T data;

    public boolean success() {
        if (msg.equals("成功"))
            return true;
        else
            return false;
    }
}
