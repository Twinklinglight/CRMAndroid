package com.wtcrmandroid.httpfactory.request;

import okhttp3.Request;

/**
 * request请求抽象类
 * Created by chen on 2016/3/9.
 */
public abstract class OkHttpRequest {
	protected String url;
	protected Object params;
	protected Object tag;
	protected String userAgent;
	protected Request.Builder builder=new Request.Builder();
	public OkHttpRequest(String url,String userAgent,Object params,Object tag){
		this.url=url;
		this.userAgent=userAgent;
		this.params=params;
		this.tag=tag;
		//抛出异常
		initBuilder();
	}

	protected abstract void initBuilder();
	public Request getRequest(){
		return this.builder.build();
}
}
