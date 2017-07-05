package com.wtcrmandroid.presenter.activity;

import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.view.AllView;

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

    /**
     * 获取部门员工日志列表
     */
    public void getData(Object o){
        post("",o,0);
    }

}
