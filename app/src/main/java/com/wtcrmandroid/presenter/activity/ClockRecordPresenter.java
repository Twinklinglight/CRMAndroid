package com.wtcrmandroid.presenter.activity;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.model.dealdata.GroupingClockRecordDD;
import com.wtcrmandroid.model.reponsedata.ClockRecordRP;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.utils.DateUtil;
import com.wtcrmandroid.utils.L;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wt-pc on 2017/6/26.
 */

public class ClockRecordPresenter extends BasePresenter {
    Date date;
    Date lasttime;


    public ClockRecordPresenter(AllView view, Context context) {
        super(view, context);
    }


    @Override
    protected void returnData(int key, String response) {
       List<GroupingClockRecordDD> list = new ArrayList<>();
        List<ClockRecordRP> listRP = new ArrayList<>();
        Type listType = new TypeToken<List<ClockRecordRP>>() {
        }.getType();
        if (response != null || !response.equals(""))
            listRP = new Gson().fromJson(response, listType);

        for (int i = 0; i < listRP.size(); i++) {
            ClockRecordRP bean = listRP.get(i);

            try {
                date = DateUtil.ConverToDate(bean.getCreateTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
            GroupingClockRecordDD dd = new GroupingClockRecordDD();
            dd.setType(1);
            dd.setTime(bean.getCreateTime().split(" ")[1]);
            dd.setAddress(bean.getAddress());
            if (i == 0) {
                GroupingClockRecordDD Add = new GroupingClockRecordDD();
                Add.setTime(bean.getCreateTime().split(" ")[0]);
                Add.setType(0);
                list.add(Add);
                list.add(dd);
                lasttime = date;
            } else {
                if (DateUtil.isSameDate(lasttime, date)) {
                    list.add(dd);
                } else {

                    GroupingClockRecordDD Add = new GroupingClockRecordDD();
                    Add.setTime(bean.getCreateTime().split(" ")[0]);
                    Add.setType(0);
                    list.add(Add);
                    list.add(dd);
                    lasttime = date;
                }


            }
        }
        L.e(list.toString());
        view.returnData(key, list);
    }

    public void sedPost(Object object, int key) {
        post("OutSide/listPersonSignIn", object, key);
    }
}
