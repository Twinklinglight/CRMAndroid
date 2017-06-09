package com.wtcrmandroid.httpfactory.callback;

import okhttp3.Request;
import okhttp3.Response;

/**构建结果回到
 * Created by chen on 2016/3/9.
 */
public abstract class ResultCallBack<T> {


	public void onBefore(Request request)
	{
	}

	public void onAfter()
	{
	}

	public void inProgress(float progress)
	{
	}

	public T parseNetworkResponse(Response paramResponse)
			throws Exception{
		return null;
	};

	public abstract void onError(int errorRet,String errorMsg);

	public abstract void onResponse(T response);

	public abstract void onNetError(Exception e);

}
