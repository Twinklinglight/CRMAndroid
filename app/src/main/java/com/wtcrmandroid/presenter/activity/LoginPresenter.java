package com.wtcrmandroid.presenter.activity;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.MyApplication;
import com.wtcrmandroid.model.reponsedata.LoginData;
import com.wtcrmandroid.model.requestdata.LoginRequestData;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.utils.L;
import com.wtcrmandroid.utils.MD5Utils;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;


/**
 * Created by 1363655717 on 2017-06-12.
 */

public class LoginPresenter extends BasePresenter{


    public LoginPresenter(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key,String response) {
        Type listType = new TypeToken<LoginData>() {
        }.getType();
        LoginData loginData = new Gson().fromJson(response, listType);
        L.e("成功" + loginData.toString());
        MyApplication.application.setLoginData(loginData);
        view.showShortToast("登录成功");
        view.returnData(key, loginData);
    }

    /**
     * 登录
     */
    public void login() {
        LoginRequestData loginRequestData = new LoginRequestData();

        loginRequestData.setUserName("wtxz");
        loginRequestData.setImei("9209843230929990");
        loginRequestData.setUserPass(MD5Utils.MD5("111111"));
        MyApplication.application.setImei("9209843230929990");

        /*loginRequestData.setUserName("jiashaowen");
        loginRequestData.setUserPass(MD5Utils.MD5("2746"));
        loginRequestData.setImei("612433e62ec71f96");
        MyApplication.application.setImei("612433e62ec71f96");*/
//        loginRequestData.setUserName("jiaxinhe");
//        loginRequestData.setImei("9209843230929988");
//        MyApplication.application.setImei("9209843230929988");

//        loginRequestData.setUserName("wt");
//        loginRequestData.setUserPass(MD5Utils.MD5("111111"));
//        loginRequestData.setImei("9209843230929999");
//        MyApplication.application.setImei("9209843230929999");
        /*loginRequestData.setUserName("shenzhongjia");
        loginRequestData.setUserPass(MD5Utils.MD5("shen123456"));
        loginRequestData.setImei("9209843230929971");
        MyApplication.application.setImei("9209843230929971");*/
//        loginRequestData.setUserName("fanqiayun");
//        loginRequestData.setUserPass("6ddedf54c788f57f");
//        loginRequestData.setImei("920984323092997");
        /*loginRequestData.setUserName("like");
        loginRequestData.setUserPass("766aed991c06fd48");
        loginRequestData.setImei("9209843230929972");*/
        L.e(loginRequestData.toString());
        post("Login/UserLogin", loginRequestData,0);

    }
}
