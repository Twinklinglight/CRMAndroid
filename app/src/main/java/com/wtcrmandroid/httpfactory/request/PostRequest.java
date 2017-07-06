package com.wtcrmandroid.httpfactory.request;

import com.google.gson.Gson;
import com.wtcrmandroid.MyApplication;
import com.wtcrmandroid.model.reponsedata.LoginData;
import com.wtcrmandroid.utils.L;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 *	post请求builder
 * Created by chen on 2016/3/9.
 */
public class PostRequest extends OkHttpRequest{

	public PostRequest(String url, String userAgent,  Object params, Object tag) {
		super(url, userAgent, params, tag);
	}
	@Override
	protected void initBuilder() {
//		String string=params.toString();
//		JSONObject json = null;
//		try {
//			json = new JSONObject(string);
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		L.e(json.toString());
//        RequestBody requestBody=body.build();
//    requestBody.contentType();

//    builder.url(url).header("User-Agent",userAgent).post(requestBody);

        Gson gson=new Gson();
        String json=gson.toJson(params);

		L.e(json);
		LoginData loginData =MyApplication.application.getLoginData();
		if(loginData !=null) {
			L.e("添加头部");
			builder.url(url).header("token", loginData.getToken()).addHeader("userid", loginData.getUserID() + "").addHeader("imei",MyApplication.application.getImei()).post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json));
		}else
			builder.url(url).post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json));
	}


}
