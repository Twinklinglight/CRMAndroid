package com.wtcrmandroid.main;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.model.reponsedata.MineRpData;
import com.wtcrmandroid.model.requestdata.MineRQ;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by zxd on 2017/7/25.
 */

public class MinePresenter extends BasePresenter {

    public MinePresenter(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {

        Type typelist = new TypeToken<List<MineRpData>>(){}.getType();
        List<MineRpData> lsit = new Gson().fromJson(response,typelist);
        view.returnData(key,lsit);
    }

    public void postData(MineRQ o){
        post("UserInformation/getUserInformation",o,1);
    }
}
