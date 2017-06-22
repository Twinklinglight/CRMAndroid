package com.wtcrmandroid.presenter.activity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.data.LoginData;
import com.wtcrmandroid.httpfactory.requestdata.LogoRequestData;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.utils.L;
import com.wtcrmandroid.utils.MD5Utils;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;


/**
 * Created by 1363655717 on 2017-06-12.
 */

public class LoginPresenter extends BasePresenter{


    public LoginPresenter(AllView view) {
        super(view);
    }

    @Override
    protected void returnData(int key,String response) {
        Type listType = new TypeToken<LoginData>() {
        }.getType();
        LoginData loginData = new Gson().fromJson(response, listType);
        L.e("成功" + loginData.toString());
        view.showToast("登录成功");
        view.returnData(key, loginData);
    }

    /**
     * 登录
     */
    public void login() {
        LogoRequestData logoRequestData= new LogoRequestData();
        logoRequestData.setUserName("shenzhongjia");
        logoRequestData.setUserPass(MD5Utils.MD5("shen123456"));
        L.e(logoRequestData.toString());
        post("Login/UserLogin",logoRequestData,0);

    }
}
