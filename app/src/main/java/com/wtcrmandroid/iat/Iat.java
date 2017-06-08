package com.wtcrmandroid.iat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.iflytek.sunflower.FlowerCollector;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *科大讯飞语音
 * Created by fq on 2016/6/6.
 */
public class Iat {
	private Context context;
	private static String TAG = Iat.class.getSimpleName();
	private SpeechRecognizer mIat;
	private RecognizerDialog mIatDialog;
	protected HashMap<String, String> mIatResults;// 用HashMap存储听写结果
	private Toast mToast;
	private String mEngineType = SpeechConstant.TYPE_CLOUD;// 引擎类型
	String result="";//听写信息
	private static String iatError="暂时无法获取录音，请稍后再试";

    @SuppressLint("ShowToast")
	public Iat(Context context){
		this.context = context;
		mIat = SpeechRecognizer.createRecognizer(context, mInitListener);// 使用SpeechRecognizer对象，可根据回调消息自定义界面；
		mIatDialog = new RecognizerDialog(context, mInitListener);// 初始化听写Dialog，如果只使用有UI听写功能，无需创建SpeechRecognizer
		mToast = Toast.makeText(context, "sdaf", Toast.LENGTH_SHORT);
		mIatResults = new LinkedHashMap<String, String>();
		mToast.show();
		if(mIat==null){
			Toast.makeText(context, "空参数1", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 语音识别
	 */
	public void iatRecognize(){
//		FlowerCollector.onEvent(context, "iat_recognize");// 移动数据分析，收集开始听写事件
//		result="";// 清空显示内容
//		mIatResults.clear();
		setParam();// 设置参数
		mIatDialog.setListener(mRecognizerDialogListener);// 显示听写对话框
//		mIatDialog.show();
//		showTip("请开始说话…");
	}

	/**
	 * 取消听写
	 */
	public void stopIatRecongnize(){
		if(mIat !=null && mIat.isListening()){
			mIat.stopListening();
			mIat.cancel();
		}
	}

	/**
	 * 初始化监听器。
	 */
	private InitListener mInitListener = new InitListener() {
		@Override
		public void onInit(int code) {
			Log.e(TAG, "SpeechRecognizer init() code = " + code);
			if (code != ErrorCode.SUCCESS) {
				showTip("初始化失败，错误码：" + code);
			}
		}
	};

	/**
	 * 听写监听器。
	 */
	private RecognizerListener mRecognizerListener = new RecognizerListener() {
		@Override
		public void onBeginOfSpeech() {
//			showTip("开始说话");// 此回调表示：sdk内部录音机已经准备好了，用户可以开始语音输入
		}

		@Override
		public void onError(SpeechError error) {
//			showTip(error.getPlainDescription(true));// 错误码：10118(您没有说话)，可能是录音机权限被禁，需要提示用户打开应用的录音权限。 // 如果使用本地功能（语记）需要提示用户开启语记的录音权限。
			if(setRestult != null){
				setRestult.failed(iatError);
			}
		}

		@Override
		public void onEndOfSpeech() {
//			showTip("结束说话");// 此回调表示：检测到了语音的尾端点，已经进入识别过程，不再接受语音输入
		}

		@Override
		public void onResult(RecognizerResult results, boolean isLast) {
			Log.d(TAG, results.getResultString());
			printResult(results,isLast);
			if (isLast) {
				Log.d(TAG, "isLast："+results.toString());
			}
		}

		/**
		 * @param volume 音量大小
         * @param data 音频数据
         */
		@Override
		public void onVolumeChanged(int volume, byte[] data) {
		}

		@Override
		public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
		}
	};

	private void  printResult(RecognizerResult results, boolean isLast) {
		String text = JsonParser.parseIatResult(results.getResultString());
		String sn = null;
		// 读取json结果中的sn字段
		try {
			JSONObject resultJson = new JSONObject(results.getResultString());
			sn = resultJson.optString("sn");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		mIatResults.put(sn, text);
		StringBuffer resultBuffer = new StringBuffer();
		for (String key : mIatResults.keySet()) {
			resultBuffer.append(mIatResults.get(key));
		}
		result = resultBuffer.toString();
		if(setRestult != null&& isLast){
			setRestult.succeed(result);
		}
	}

	/**
	 * 听写UI监听器
	 */
	private RecognizerDialogListener mRecognizerDialogListener = new RecognizerDialogListener() {
		//听写UI监听器 results
		public void onResult(RecognizerResult results, boolean isLast) {
				printResult(results,isLast);
		}
		// 识别回调错误.
		public void onError(SpeechError error) {
//			showTip(error.getPlainDescription(true));
			if(setRestult != null){
				setRestult.failed(iatError);
			}
		}
	};

	private void showTip(final String str) {
		mToast.setText(str);
		mToast.show();
	}

	/**
	 * 参数设置
	 */
	private void setParam() {
		if(mIat!=null) {
			mIat.setParameter(SpeechConstant.PARAMS, null);// 清空参数
			mIat.setParameter(SpeechConstant.ENGINE_TYPE, mEngineType);// 设置听写引擎
			mIat.setParameter(SpeechConstant.RESULT_TYPE, "json");// 设置返回结果格式
			mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");// 设置语言 简体中文
			mIat.setParameter(SpeechConstant.ACCENT, "mandarin");//支持普通话
			mIat.setParameter(SpeechConstant.VAD_BOS, "4000");// 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
			mIat.setParameter(SpeechConstant.VAD_EOS, "1000");// 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
			mIat.setParameter(SpeechConstant.ASR_PTT, "1");// 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
			mIat.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");// 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限  // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
			mIat.setParameter(SpeechConstant.ASR_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/msc/iat.wav");
		}else {
			Toast.makeText(context, "空参数", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 释放连接
	 */
	public void onDestroyCall() {
		if(mIat != null){
			mIat.cancel();
			mIat.destroy();
		}
	}

	/**
	 * 开放统计 移动数据统计分析
	 * onResume 时调用
	 */
	public void onResumeCall() {
		FlowerCollector.onResume(context);
		FlowerCollector.onPageStart(TAG);
	}

	/**
	 * 开放统计 移动数据统计分析
	 * onPause 时调用
	 */
	public void onPauseCall() {
		FlowerCollector.onPageEnd(TAG);
		FlowerCollector.onPause(context);
	}

    public setResult setRestult;

	public void setSetRestult(setResult setRestult) {
		this.setRestult = setRestult;
	}

	public interface  setResult{
        void succeed(String result);
        void failed(String iatError);
    }

}
