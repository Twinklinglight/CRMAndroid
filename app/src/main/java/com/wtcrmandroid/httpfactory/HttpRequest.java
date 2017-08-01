package com.wtcrmandroid.httpfactory;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.wtcrmandroid.base.Const;
import com.wtcrmandroid.base.MyApplication;
import com.wtcrmandroid.base.WtHeader;
import com.wtcrmandroid.httpfactory.callback.FileCallBack;
import com.wtcrmandroid.httpfactory.callback.RetCallBack;
import com.wtcrmandroid.httpfactory.callback.StringCallBack;
import com.wtcrmandroid.httpfactory.https.SslUtils;
import com.wtcrmandroid.httpfactory.request.PingUtils;
import com.wtcrmandroid.httpfactory.request.PostRequest;
import com.wtcrmandroid.model.reponsedata.LoginData;
import com.wtcrmandroid.utils.L;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * okhttpclient管理类
 * Created by chen on 2016/3/9.
 */

public class HttpRequest {

    private MyApplication app;//全局上下文
    private static HttpRequest httpRequest;

    public OkHttpClient okHttpClient;

    private Handler mainHandler;//主线程handler

    private final static int VERSION_CODE = 1;

    //注入全局上下文
    public void initializeContext(MyApplication myApplication) {
        this.app = myApplication;
    }

    public HttpRequest() {
        okHttpClient = this.createOkhttpClient();
        mainHandler = new Handler(Looper.getMainLooper());
    }

    public static HttpRequest instance() {
        if (httpRequest == null) {
            synchronized (HttpRequest.class) {
                if (httpRequest == null) {
                    httpRequest = new HttpRequest();
                }
            }
        }
        return httpRequest;
    }

    //获得一个okhttpclient对象，并设置超时
    private OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            synchronized (HttpRequest.class) {
                okHttpClient = this.createOkhttpClient();
            }
        }
        return okHttpClient;
    }


    //创建一个定义属性的okhttpclient
    private OkHttpClient createOkhttpClient() {
        return new OkHttpClient().newBuilder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 公有方法：发送get异步请求
     *
     * @param url            地址
     * @param params         参数集
     * @param tag            此请求的标识
     * @param resultCallBack 请求回调
     */
    public void sendGet(String url, Map<String, Object> params, Object tag, StringCallBack resultCallBack) {
//        if (params == null) {
//            params = new HashMap<>();
//        }
//        params.put("ver_version", VERSION_CODE + "");
//        GetRequest getBuilder = new GetRequest(url, app.getUserAgent(), params, tag);
//        Request request = getBuilder.getRequest();
//        deliverResult(url, request, resultCallBack);
    }

    /**
     * 重载
     * 公有方法：发送get异步请求
     *
     * @param url         地址
     * @param params      参数集
     * @param tag         此请求的标识
     * @param retCallBack 请求回调
     */
    public void sendGet(final String url, Map<String, Object> params, Object tag, final RetCallBack retCallBack) {
//        if (params == null) {
//            params = new HashMap<>();
//        }
//        params.put("ver_version", VERSION_CODE + "");
//        GetRequest getBuilder = new GetRequest(url, app.getUserAgent(), params, tag);
//        Request request = getBuilder.getRequest();
//
//        getOkHttpClient().newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                retCallBack.onNetError(e);
//                pingAndSendEmail(url);
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (response.body().toString() != null && !"".equals(response.body().toString())) {
//                    String string = response.body().string();
//                    retCallBack.onResponse(string);
//                } else {
//                    retCallBack.onError(-1, "没有数据返回");
//                }
//            }
//        });
    }

    /**
     * 公有方法：发送post异步请求
     *
     * @param url            地址
     * @param params         参数集
     * @param tag            此请求的标识
     * @param resultCallBack 请求回调
     */

    public void sendPost(String url, Object params, Object tag, StringCallBack resultCallBack) {

        LoginData loginData =MyApplication.application.getLoginData();


//        params.put("ver_version", VERSION_CODE + "");//版本
        PostRequest postRequest = new PostRequest(url,null, params, tag);
        Request request = postRequest.getRequest();
        L.e( postRequest.toString());
        deliverResult(url, request, resultCallBack);
    }

    /**
     * 处理请求和分发回调
     *
     * @param request  请求
     * @param callBack 回调
     */
    private void deliverResult(final String url, final Request request, final StringCallBack callBack) {
        getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onNetError(e);
                pingAndSendEmail(url);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                praseResponse(callBack, call, response);
            }
        });
    }

    //解析获取的结果
    private void praseResponse(StringCallBack callBack, Call call, Response response) {
        String error = "";
        int ret = -1;
        try {
            String result = response.body().string();
            L.e("json", "json_result:" + result);
            JSONObject json = new JSONObject(result);
            L.e("json", "praseResponse: " + json);
            if (!json.isNull("ret")) {//ret不为空
                ret = json.getInt("ret");
                if (ret == 0) {
                    if (!json.isNull("data")) {//data不为空
                        String data = json.getString("data");
                        callBack.onResponse(data);
                    } else {
                        error = "服务器数据格式异常";
                        callBack.onError(ret, error);
                    }
                } else if (ret == 2) {//key验证失败
                    downkey(callBack, call);
                } else if (ret == 4) {
                    callBack.onError(ret, json.getString("data"));
                } else {
                    error = json.optString("msg", "系统错误");
                    callBack.onError(ret, error);
                }
            } else {
                error = "服务器无返回数据！";
                callBack.onError(ret, error);
//					callBack.onError(ret,result);//测试推送时使用
//					callBack.onResponse(error);
            }
        } catch (JSONException e) {
            error = "Json解析异常";
            callBack.onError(ret, error);
        } catch (IOException e) {
            error = "文件异常";
            callBack.onError(ret, error);
        }

    }

    /***
     * 文件下载
     *
     * @param url          网址
     * @param fileCallBack 回调
     * @throws IOException 抛出异常
     */
    public void downFile(final String url, final FileCallBack fileCallBack) throws IOException {
        Request request = new Request.Builder().url(url).build();
        getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                fileCallBack.onError(1, e.toString());
                pingAndSendEmail(url);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                fileCallBack.onResponse(fileCallBack.parseNetworkResponse(response));
            }
        });
    }

    //下载与服务器交互需要的key
    private void downkey(final StringCallBack callBack, Call call) throws IOException {
        Request request = new Request.Builder().url(Const.DOWN_KEY_URL).build();
        Call keyCall = getOkHttpClient().newCall(request);
        Response execute = keyCall.execute();
        if (execute.isSuccessful()) {
            String result = execute.body().string();
            try {
                JSONObject json = new JSONObject(result);
                if (!json.isNull("ret") && json.getInt("ret") == 0 && !TextUtils.isEmpty(json.getString("data"))) {
                    String key = json.getString("data");
                    SharedPreferences.Editor editor = app.getSharedPreferences("wtkey", Context.MODE_PRIVATE).edit();
                    editor.putString("key", key).commit();
                    String ciphertext = "Android&"
                            + WtHeader.getIMEI(app);
//                    app.setUserAgent(DESUtils.encrypt(
//                            ciphertext,
//                            MD5Utils._MD5(key)));
                    callBack.onError(2, "身份验证失败！请重新操作");
                } else {
                    callBack.onError(2, "无法获取身份验证");
                }
            } catch (JSONException e) {
                callBack.onError(2, "身份信息解析失败");
            }
        } else {
            callBack.onError(2, "无法获取身份验证");
        }
    }

    //取消正在队列等待或者正在请求的任务
    public void cancle(Object tag) {
        for (Call call : getOkHttpClient().dispatcher().queuedCalls()) {//在请求队列
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }

        for (Call call : getOkHttpClient().dispatcher().runningCalls()) {//正在请求
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }

    public void cancleAll() {
        getOkHttpClient().dispatcher().cancelAll();
    }

    //把证书转成输入流验证
    public void setCertificates(InputStream[] certificates) {
        SSLSocketFactory sslSocketFactory = SslUtils.getSslSocketFactory(certificates, null, null);

        OkHttpClient.Builder builder = getOkHttpClient().newBuilder();
        builder = builder.sslSocketFactory(sslSocketFactory);
        this.okHttpClient = builder.build();
    }

    //通过证书文件验证
    public void setCertificates(InputStream[] certificates, InputStream bksFile, String password) {
        this.okHttpClient = getOkHttpClient().newBuilder().sslSocketFactory(SslUtils.getSslSocketFactory(certificates, bksFile, password)).build();
    }

    //获取当前版本号
    public int getVersionCode() {
        return VERSION_CODE;
    }

    public Handler getMainHandler() {
        return this.mainHandler;
    }

    private void pingAndSendEmail(final String domainName) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                PingUtils.getInstance().startPing(domainName);
            }
        });
    }


}