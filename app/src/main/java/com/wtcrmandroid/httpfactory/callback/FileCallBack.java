package com.wtcrmandroid.httpfactory.callback;


import com.wtcrmandroid.httpfactory.HttpRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Response;

/**
 * 文件下载回调
 * Created by chen on 2016/3/9.
 */
public abstract class FileCallBack extends ResultCallBack<File>{
	private String dirName;
	private String fileName;
	private long finalSum;

	public FileCallBack(String dirName, String fileName){
		this.dirName=dirName;
		this.fileName=fileName;


	}
	public File parseNetworkResponse(Response paramResponse) throws IOException {
		return save(paramResponse);
	}



	//保存文件到指定路径
	public File save(Response response){
		InputStream inputStream = response.body().byteStream();
		byte [] buf=new byte[2048];
		int len=0;
		final long totalLenth=response.body().contentLength();;

		File dirFile=new File(dirName);
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
		File file = new File(dirFile, fileName);
		FileOutputStream fos=null;
		try {
			long sum=0;
			fos = new FileOutputStream(file);
			while ((len=inputStream.read(buf))!=-1){
				fos.write(buf,0,len);
				sum+=len;
				finalSum=sum;
				HttpRequest.instance().getMainHandler().post(new Runnable() {
					@Override
					public void run() {
						inProgress(totalLenth,(int) (finalSum*100.0f/totalLenth*1.0f));
					}
				});
			}
			fos.flush();
			return file;
		}catch (IOException e){

		}finally {
			//关闭输入输出流
			if(inputStream!=null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (fos!=null){
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	/**
	 * 回传文件的总长度和下载的进度
	 * @param totalLenth 总长度
	 * @param progress	下载进度
	 */
	public void inProgress(long totalLenth,float progress){

	}


}

