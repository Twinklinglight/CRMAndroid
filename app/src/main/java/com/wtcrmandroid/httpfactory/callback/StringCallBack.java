package com.wtcrmandroid.httpfactory.callback;

import okhttp3.Response;

/**
 * 返回参数为String
 * Created by chen on 2016/3/9.
 */
public abstract  class StringCallBack extends ResultCallBack<String>{

	@Override
	public String parseNetworkResponse(Response paramResponse) throws Exception {

		return paramResponse.body().string();
	}
}
