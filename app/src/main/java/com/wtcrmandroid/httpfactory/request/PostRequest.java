package com.wtcrmandroid.httpfactory.request;

import com.google.gson.Gson;

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
		builder.url(url).post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json));
	}


}
