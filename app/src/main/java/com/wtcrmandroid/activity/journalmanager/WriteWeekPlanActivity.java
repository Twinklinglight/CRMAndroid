package com.wtcrmandroid.activity.journalmanager;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.listview.WriterWeekPlaneAdapter;
import com.wtcrmandroid.model.WriterWeekPlaneData;
import com.wtcrmandroid.model.reponsedata.WjournalData;
import com.wtcrmandroid.model.requestdata.WweekPlanRequestData;
import com.wtcrmandroid.activity.journalmanager.present.WriteWeekPlanPresenter;
import com.wtcrmandroid.utils.DateUtils;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.view.dialog.WeekDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 1363655717 on 2017-06-05.
 * 写周计划
 */

public class WriteWeekPlanActivity extends BaseActivity<WriteWeekPlanPresenter, WjournalData> implements WeekDialog.WeekListener {

    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.lv_write_work_plan)
    ListView lvWriteWorkPlan;

    List<WriterWeekPlaneData> list;
    @BindView(R.id.tv_date_show)
    TextView tvDateShow;

    private WriterWeekPlaneAdapter adapter;
    private int position = 2;
    private String nowWeek;

    @Override
    protected int layout() {
        return R.layout.activity_write_week_plan;
    }

    @Override
    protected void initView() {
        presenter = new WriteWeekPlanPresenter(this,this);
        titlebar.setTitletext("写周计划");
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WriteWeekPlanActivity.this.finish();
            }
        });
        nowWeek = new DateUtils().getNowWeek();
        tvDateShow.setText(nowWeek);
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        WriterWeekPlaneData writerWeekPlaneData = new WriterWeekPlaneData();
        writerWeekPlaneData.setWorkNumber("本周计划1");
        list = new ArrayList<>();
        list.add(writerWeekPlaneData);
        adapter = new WriterWeekPlaneAdapter(this, list);
        lvWriteWorkPlan.setAdapter(adapter);


    }


    @OnClick({R.id.bt_submit,R.id.ll_select_date})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_submit:
                WweekPlanRequestData wweekPlanRequestData = new WweekPlanRequestData();
                wweekPlanRequestData.setTime(nowWeek);
                wweekPlanRequestData.setUserId(MyApplication.application.getLoginData().getUserID());
                wweekPlanRequestData.setType("week");
                wweekPlanRequestData.setPlan(true);
                wweekPlanRequestData.setWork(list);
                wweekPlanRequestData.setLearningAndReflection("");

                presenter.submit(wweekPlanRequestData);
                break;
            case R.id.ll_select_date:
                new WeekDialog(this,this,position).show();
        }


    }

    @Override
    public void returnData(int key, WjournalData data) {

        WriteWeekPlanActivity.this.finish();
    }

    /**
     * 日期选择回调
     * @param weekText  选择日期
     * @param position  第几个
     */
    @Override
    public void weekSelect(String weekText, int position) {
        this.position = position;
        tvDateShow.setText(weekText);
    }
}
