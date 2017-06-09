package com.wtcrmandroid.httpfactory.request;

import android.text.TextUtils;


import com.wtcrmandroid.utils.L;

import java.util.Map;

/**
 * get请求builder
 * Created by chen on 2016/3/9.
 */
public class GetRequest extends OkHttpRequest{
	public GetRequest(String url, String userAgent, Map<String, String> params, Object tag) {
		super(url, userAgent, params, tag);
	}

	@Override
	protected void initBuilder() {
		String requestParams;
		if(params!=null){
			requestParams = map2String(params);
			if (requestParams==null){
				//输入了空指针的value，结束请求
				L.e("GetRequest","Param value can not be null !");
				return;
			}
			if(!TextUtils.isEmpty(requestParams)){//有参数
				if(url.indexOf("?")!=-1){//url已经有参数
					url+="&"+requestParams;
				}else{//url无参数
					url+="?"+requestParams;
				}
			}
		}
		builder.url(url).header("User-Agent",userAgent).build();
		L.e("url","url:"+url);
	}
	private String map2String(Map<String,String> params){
		StringBuffer stringBuffer = new StringBuffer();
		for (Map.Entry<String,String> entry:params.entrySet()) {
			stringBuffer.append(entry.getKey());
			stringBuffer.append("=");
			if (entry.getValue()!=null){
				stringBuffer.append(entry.getValue());
			}else {
				//输入了空指针的value
				return null;
			}
			stringBuffer.append("&");
		}
		stringBuffer.deleteCharAt(stringBuffer.length()-1);
		return  stringBuffer.toString();
	}
}
