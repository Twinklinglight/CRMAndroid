package com.wtcrmandroid.presenter.activity;

import android.content.Context;

import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

/**
 * Created by 1363655717 on 2017-06-13.
 */

public class MyJournalPresenter extends BasePresenter{


    public MyJournalPresenter(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {

    }
    public void getData(){
//        HashMap<String, Object> params = new HashMap<>();
//        params.put("userName", "shenzhongjia");
//        params.put("userPass", MD5Utils.MD5("shen123456"));
//        params.put("type", "day");
//        params.put("isPlan", "false");
//        params.put("nowDate", "2017-06-12");
//        L.e(MD5Utils.MD5("shen123456"));
//        post("http://192.168.0.7/api/WorkPlan/getUserWorkPlan", params,0);
    }

}
