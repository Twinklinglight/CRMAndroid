package com.wtcrmandroid.presenter.activity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.data.LoginData;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.utils.L;
import com.wtcrmandroid.utils.MD5Utils;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;


/**
 * Created by 1363655717 on 2017-06-12.
 */

public class LoginPresenter extends BasePresenter<List<LoginData>> {


    public LoginPresenter(AllView<List<LoginData>> view) {
        super(view);
    }

    @Override
    protected void returnData(int key,String response) {
        Type listType = new TypeToken<List<LoginData>>() {
        }.getType();
        List<LoginData> list1 = new Gson().fromJson(response, listType);
        L.e("成功" + list1.toString());
        view.showToast("登录成功");
        view.returnData(key, list1);
    }

    /**
     * 登录
     */
    public void login() {
        HashMap<String, String> params = new HashMap<>();
        params.put("userName", "shenzhongjia");
        params.put("userPass", MD5Utils.MD5("shen123456"));
        L.e(MD5Utils.MD5("shen123456"));
        post("http://192.168.0.7/api/Login/UserLogin", params,0);

    }
}
