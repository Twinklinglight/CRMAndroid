package com.wtcrmandroid.http;

import com.wtcrmandroid.http.data.BaseData;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by 1363655717 on 2017-05-31.
 */

public interface ApiService <T>{
    /**
     * post请求
     * @param url 接口路径
     * @param maps 上传参数
     * @return
     */
    @POST("{url}")
    Observable<BaseData<T>> executePost(
            @Path("url") String url,
            @FieldMap Map<String, String> maps);
}
