package com.wtcrmandroid.activity.journalmanager;

import android.view.View;
import android.widget.ListView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.adapter.listview.WriterWeekPlaneAdapter;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.model.WriterWeekPlaneData;
import com.wtcrmandroid.presenter.activity.WriteWeekPlanPresenter;
import com.wtcrmandroid.utils.L;
import com.wtcrmandroid.utils.MD5Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 1363655717 on 2017-06-05.
 * 写周计划
 */

public class WriteWeekPlanActivity extends BaseActivity<WriteWeekPlanPresenter,String> {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.lv_write_work_plan)
    ListView lvWriteWorkPlan;

    private WriterWeekPlaneAdapter adapter;
    @Override
    protected int layout() {
        return R.layout.activity_write_week_plan;
    }

    @Override
    protected void initView() {
        titlebar.setTitletext("写周计划");
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        WriterWeekPlaneData writerWeekPlaneData = new WriterWeekPlaneData();
        writerWeekPlaneData.setWorkNumber("0");
        List<WriterWeekPlaneData> list = new ArrayList<>();
        list.add(writerWeekPlaneData);
        adapter=new WriterWeekPlaneAdapter(this, list);
        lvWriteWorkPlan.setAdapter(adapter);
        presenter=new WriteWeekPlanPresenter(this);

    }



    @OnClick(R.id.bt_submit)
    public void onClick() {

        HashMap<String, Object> params = new HashMap<>();
        params.put("userName", "shenzhongjia");
        params.put("userPass", MD5Utils.MD5("shen123456"));
        params.put("type", "week");
        params.put("isPlan", "true");
        params.put("work", adapter.getList());
        presenter.submit(params);
        String string=params.toString();
        L.e(string);
        JSONObject json = null;
        try {
            json = new JSONObject(string);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        L.e(json.toString());
//        L.e(adapter.getList().toString());

    }

    @Override
    public void returnData(int key, String data) {

    }
}
