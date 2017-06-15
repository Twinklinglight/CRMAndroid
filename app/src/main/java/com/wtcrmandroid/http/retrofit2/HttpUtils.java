package com.wtcrmandroid.http.retrofit2;

import android.app.Activity;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.wtcrmandroid.data.LoginData;
import com.wtcrmandroid.http.retrofit2.rxrequest.RxHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
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
        ScalarsConverterFactory scalarsConverterFactory = ScalarsConverterFactory.create();
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new SafeTypeAdapterFactory()).create();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();
        apiService = new Retrofit.Builder()
//                .baseUrl("http://www.jiushijiudian.com/")
                .baseUrl("http://192.168.0.7/api/Login/")
//                .baseUrl("http://android.chinawutong.com/")
                .addConverterFactory(scalarsConverterFactory)
//                .addConverterFactory(gsonConverterFactory)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build().create(ApiService.class);
    }
    public  void post(String url, Map<String, String> map, final Activity activity) {

        try {
            Toast.makeText(activity,"chufa",Toast.LENGTH_SHORT).show();
            String string=map.toString();
            JSONObject json = null;
            try {
                json = new JSONObject(string);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json.toString());
          Observable<List<LoginData>> fromNetwrok = apiService.executePost(url,body)
                    .compose(RxHelper.<List<LoginData>>handleResult());

//            RxRetrofitCache.load(fromNetwrok)
//                    .subscribe(new RxSubscribe<List<LoginData>>(activity,
//                            REQUEST_TITLE) {
//                        @Override
//                        protected void _onNext(List<LoginData> model) {
//
//                            Toast.makeText(activity,model.get(0).getUserName(),Toast.LENGTH_SHORT).show();
//
//                        }
//
//                        @Override
//                        protected void _onError(String message) {
//                            L.e(message);
//                            Toast.makeText(activity,message,Toast.LENGTH_SHORT).show();
//                        }

//                    });
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
