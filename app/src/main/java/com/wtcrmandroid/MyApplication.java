package com.wtcrmandroid;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.iflytek.cloud.SpeechUtility;
import com.wtcrmandroid.model.reponsedata.LoginData;

import java.io.IOException;

/**
 * Created by 1363655717 on 2017-06-07.
 */

public class MyApplication extends Application {
    /**
     * 登录返回数据
     */
    public LoginData loginData =new LoginData();
    public static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        // 讯飞应用程序入口处调用，避免手机内存过小，杀死后台进程后通过历史intent进入Activity造成SpeechUtility对象为null
        SpeechUtility.createUtility(MyApplication.this, "appid=" + "5937b406");
        /**
         * 百度地图
         * 在使用SDK各组件之前初始化context信息，传入ApplicationContext
         * 注意该方法要再setContentView方法之前实现
         */
        SDKInitializer.initialize(getApplicationContext());
        application = this;
    }

    public LoginData getLoginData() {
        return loginData;
    }

    public void setLoginData(LoginData loginData) {
        this.loginData = loginData;
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
