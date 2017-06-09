package com.wtcrmandroid.utils;

import android.widget.Button;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * 工具类
 * Created by 1363655717 on 2017/3/21.
 */

public  class Tool {

    /**
     * 判断数据是否为空
     * @param strings
     * @return
     */
    public static boolean isEmpty(String[] strings){
        for (int i=0;i<strings.length;i++){
           if( strings[i]==null||strings[i].equals(""))
               return false;
        }
        return true;
    }
    /**
     * 密码md5加密
     * @param in
     * @return
     */
    public static String md5(String in) {
        if(in==null||in.equals(""))
            return null;
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.reset();
            digest.update(in.getBytes());
            byte[] a = digest.digest();
            int len = a.length;
            StringBuilder sb = new StringBuilder(len << 1);
            for (int i = 0; i < len; i++) {
                sb.append(Character.forDigit((a[i]>>4)&0xf,16));
                sb.append(Character.forDigit(a[i]&0xf, 16));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 按钮倒计时
     * @param time
     * @param button
     */
    public static void ButtonCutDown(int time, final Button button){
        countdown(time).subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
//                        button.setText("获取验证码");
//                        button.setClickable(true);
//                        button.setBackgroundResource(R.drawable.btn_n_b);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
//                        button.setText(""+integer);
//                        button.setClickable(false);
//                        button.setBackgroundResource(R.drawable.btn_d);
                    }
                });
    }
    public static Observable<Integer> countdown(int time) {
        if (time < 0) time = 0;

        final int countTime = time;
        return Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<Long, Integer>() {
                    @Override
                    public Integer call(Long increaseTime) {
                        return countTime - increaseTime.intValue();
                    }
                })
                .take(countTime + 1);

    }
}
