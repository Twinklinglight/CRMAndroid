package com.wtcrmandroid.http.retrofit2;

import com.wtcrmandroid.data.BaseData;
import com.wtcrmandroid.data.LoginData;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by 1363655717 on 2017-06-12.
 */

public class Api implements ApiService<List<LoginData>>{
    @Override
    public Observable<BaseData<List<LoginData>>> executePost(@Path("url") String url, @Body RequestBody body) {
        return null;
    }
}
