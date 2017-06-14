package com.wtcrmandroid.http.retrofit2;

import com.wtcrmandroid.data.BaseData;
import com.wtcrmandroid.data.LoginData;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by 1363655717 on 2017-05-31.
 */

public interface ApiService<T> {
    /**
     * post请求
     * @param url 接口路径
     * @RequestBody body 上传参数
     * @return
     */

    @POST("{url}")
     Observable<BaseData<List<LoginData>>> executePost(
            @Path("url") String url,
            @Body RequestBody body);
}
