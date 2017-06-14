package com.wtcrmandroid.presenter.activity;

import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.utils.L;
import com.wtcrmandroid.utils.MD5Utils;
import com.wtcrmandroid.view.AllView;

import java.util.HashMap;

/**
 * Created by 1363655717 on 2017-06-13.
 */

public class MyJournalPresenter extends BasePresenter{
    public MyJournalPresenter(AllView view) {
        super(view);
    }

    @Override
    protected void returnData(int key, String response) {

    }
    public void getData(){
        HashMap<String, String> params = new HashMap<>();
        params.put("userName", "shenzhongjia");
        params.put("userPass", MD5Utils.MD5("shen123456"));
        params.put("type", "day");
        params.put("isPlan", "false");
        params.put("nowDate", "2017-06-12");
        L.e(MD5Utils.MD5("shen123456"));
        post("http://192.168.0.7/api/WorkPlan/getUserWorkPlan", params,0);
    }

}
