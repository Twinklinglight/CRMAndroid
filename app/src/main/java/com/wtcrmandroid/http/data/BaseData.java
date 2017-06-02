package com.wtcrmandroid.http.data;

import java.io.Serializable;

/**
 * Created by 1363655717 on 2017-05-31.
 * 网络返回数据实体基类
 */

public class BaseData<T> implements Serializable {
    public String msg;
    public String resultdesc;
    public T spread;

    public boolean success() {
        if (msg.equals("OK") || msg.equals("ok"))
            return true;
        else
            return false;
    }
}
