package com.wtcrmandroid.http;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.wtcrmandroid.http.cach.RxRetrofitCache;
import com.wtcrmandroid.http.rxrequest.RxHelper;
import com.wtcrmandroid.http.rxrequest.RxSubscribe;

import java.io.IOException;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;

/**
 * Created by 1363655717 on 2017-05-31.
 */

public class HttpUtils {
    public ApiService apiService;
    public final static String REQUEST_TITLE = "正在加载";
    public HttpUtils() {
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(new Gson());

        ScalarsConverterFactory scalarsConverterFactory = ScalarsConverterFactory.create();
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new SafeTypeAdapterFactory()).create();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();
        apiService = new Retrofit.Builder()
//                .baseUrl("http://www.jiushijiudian.com/")
                .baseUrl("http://192.168.0.108:8080//Jsjdjkxmssm/")
                .addConverterFactory(scalarsConverterFactory)
//                .addConverterFactory(gsonConverterFactory)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build().create(ApiService.class);
    }
    public  void post(String url,Map<String, String> map, Activity activity) {

        try {
            Observable<String> fromNetwrok = apiService.executePost("fsff",map)
                    .compose(RxHelper.<String>handleResult());

            RxRetrofitCache.load(fromNetwrok)
                    .subscribe(new RxSubscribe<String>(activity,
                            REQUEST_TITLE) {
                        @Override
                        protected void _onNext(String model) {


                        }

                        @Override
                        protected void _onError(String message) {

                        }

                    });
        } catch (Exception ex) {

        }
    }

    private final static class SafeTypeAdapterFactory<T> implements TypeAdapterFactory {
        @Override
        public TypeAdapter create(Gson gson, final TypeToken type) {
            final TypeAdapter delegate = gson.getDelegateAdapter(this, type);
            return new TypeAdapter<T>() {
                @Override
                public void write(JsonWriter out, T value) throws IOException {
                    try {
                        delegate.write(out, value);
                    } catch (IOException e) {
                        delegate.write(out, "");
                    }
                }


                @Override
                public T read(JsonReader in) throws IOException {
                    try {
                        return (T) delegate.read(in);
                    } catch (IOException e) {
                        in.skipValue();
                        return null;
                    } catch (IllegalStateException e) {
                        in.skipValue();
                        return null;
                    } catch (JsonSyntaxException e) {
                        in.skipValue();
                        if (type.getType() instanceof Class) {
                            try {
                                return (T) ((Class) type.getType()).newInstance();
                            } catch (Exception e1) {

                            }
                        }
                        return null;
                    }
                }
            };
        }
    }
}
