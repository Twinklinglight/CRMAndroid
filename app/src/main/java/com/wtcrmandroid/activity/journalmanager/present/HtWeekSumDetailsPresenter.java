package com.wtcrmandroid.activity.journalmanager.present;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.model.reponsedata.WeekSumDetailsRpData;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

import java.lang.reflect.Type;

/**
 * Created by zxd on 2017/7/4.
 */

public class HtWeekSumDetailsPresenter extends BasePresenter {


    public HtWeekSumDetailsPresenter(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {
        Log.i("returnData",response);
//        String testData = "{\"leve\":\"\",\"work\":[{\"workPercentage\":\"20%\",\"workNextFinishTime\":\"\",\"workPlanning\":\"\",\"workTarget\":\"完成\",\"workContent\":\"实现我的模块的界面实现\",\"workUnfinishedReason\":\"\",\"workNumber\":\"A\"},{\"workPercentage\":\"20%\",\"workNextFinishTime\":\"下周\",\"workPlanning\":\"\",\"workTarget\":\"未完成\",\"workContent\":\"日志管理模块进行接口对接，实现真实数据的显示\",\"workUnfinishedReason\":\"接口有错误和接口返回数据为空\",\"workNumber\":\"A\"},{\"workPercentage\":\"20%\",\"workNextFinishTime\":\"\",\"workPlanning\":\"\",\"workTarget\":\"未完成\",\"workContent\":\"对动态添加编辑的功能进行网上资料查找，对实现方式进行最大可能的合理\",\"workUnfinishedReason\":\"界面需求改变\",\"workNumber\":\"A\"},{\"workPercentage\":\"20%\",\"workNextFinishTime\":\"下周\",\"workPlanning\":\"\",\"workTarget\":\"未完成\",\"workContent\":\"部门管理模块进行接口对接，实现网络数据的真实显示\",\"workUnfinishedReason\":\"接口有错误和接口返回数据为空\",\"workNumber\":\"A\"},{\"workPercentage\":\"20%\",\"workNextFinishTime\":\"\",\"workPlanning\":\"\",\"workTarget\":\"完成\",\"workContent\":\"对本周的工作进行整理与完成，时间充裕的话实现我的模块的功能\",\"workUnfinishedReason\":\"\",\"workNumber\":\"A\"}],‘exam’:[],\"learning\":\"本周主要实现地图导入与外勤部分界面的实现和时光轴控件的封装以及部分菜单栏的布局控件自定义封装\"}";
        Type type = new TypeToken<WeekSumDetailsRpData>() {}.getType();
        WeekSumDetailsRpData weekSumDetailsRpData =  new Gson().fromJson(response, type);


        view.returnData(key,weekSumDetailsRpData);
    }
    /**
     * 获取周总结详情的数据
     */
    public void GetWeeSumDeails(Object o){
        post("WorkPlan/getUserWorkPlan",o,0);
    }
}
