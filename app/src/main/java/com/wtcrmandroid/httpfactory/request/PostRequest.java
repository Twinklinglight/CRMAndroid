package com.wtcrmandroid.httpfactory.request;

import com.wtcrmandroid.utils.L;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 *	post请求builder
 * Created by chen on 2016/3/9.
 */
public class PostRequest extends OkHttpRequest{

	public PostRequest(String url, String userAgent, Map<String, String> params, Object tag) {
		super(url, userAgent, params, tag);
	}
	@Override
	protected void initBuilder() {
		String string=params.toString();
		L.e(string);
        JSONObject json = null;
        try {
            json = new JSONObject(string);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        L.e(json.toString());
//        RequestBody requestBody=body.build();
//		requestBody.contentType();

//		builder.url(url).header("User-Agent",userAgent).post(requestBody);
		builder.url(url).post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString()));
	}
}
