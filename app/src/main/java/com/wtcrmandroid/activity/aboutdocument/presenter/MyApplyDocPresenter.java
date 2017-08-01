package com.wtcrmandroid.activity.aboutdocument.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.model.reponsedata.DocumentListRpData;
import com.wtcrmandroid.model.requestdata.MyApplyDocRqData;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by zxd on 2017/7/5.
 */

public class MyApplyDocPresenter extends BasePresenter {


    public MyApplyDocPresenter(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {
        if (response == null || response.equals("[]"))
            view.showShortToast("无数据");
        else {
            Type type = new TypeToken<List<DocumentListRpData>>() {
            }.getType();
            DocumentListRpData responseData = new Gson().fromJson(response, type);
            view.returnData(key, responseData);
        }

    }

    /**
     * 我发起的审批 数据请求
     */
    public void postMyApply(MyApplyDocRqData docRqData) {

        post("Application/listApplication", docRqData, 0);
    }
}
