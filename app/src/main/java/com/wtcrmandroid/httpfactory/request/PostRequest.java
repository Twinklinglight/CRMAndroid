package com.wtcrmandroid.httpfactory.request;

import com.wtcrmandroid.utils.L;

import java.util.Map;

import okhttp3.FormBody;
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
		FormBody.Builder body = new FormBody.Builder();
		for(Map.Entry<String,String> entry:params.entrySet()){
			if (entry.getValue()==null){
				L.e("PostRequest","Param value can not be null!");
				return;
			}
			body.add(entry.getKey(),entry.getValue());
		}
		RequestBody requestBody=body.build();
//		builder.url(url).header("User-Agent",userAgent).post(requestBody);
		builder.url(url).post(requestBody);
	}
}
