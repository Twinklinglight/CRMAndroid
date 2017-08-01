package com.wtcrmandroid.main;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iflytek.cloud.thirdparty.T;
import com.wtcrmandroid.model.reponsedata.MineRpData;
import com.wtcrmandroid.model.requestdata.MineRQ;
import com.wtcrmandroid.model.requestdata.NumberRQ;
import com.wtcrmandroid.model.requestdata.UpdatePhotoRQ;
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

        switch(key){
            case 1:
                Type typelist = new TypeToken<List<MineRpData>>(){}.getType();
                List<MineRpData> lsit = new Gson().fromJson(response,typelist);
                view.returnData(key,lsit);
                break;
            case 2:
                Type type = new TypeToken<List<T>>(){}.getType();
                List<T> data = new Gson().fromJson(response,type);
                view.returnData(key,data);
                break;
            case 3:
                view.returnData(key,response);
        }
    }

    public void postData(MineRQ o){
        post("UserInformation/getUserInformation",o,1);
    }

    public void postNumber(NumberRQ data){
        post("UserInformation/saveUserPhone",data,2);
    }

    public void postPhoto(UpdatePhotoRQ rqData){
        post("UserInformation/saveUserHeaderImg",rqData,3);
    }
}
