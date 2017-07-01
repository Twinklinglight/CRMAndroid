package com.wtcrmandroid.activity.journalmanager;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.journalmanager.present.WriteWeekSumPresenter;
import com.wtcrmandroid.adapter.listview.WriterWeekConclusionAdapter;
import com.wtcrmandroid.model.WriterWeekSumData;
import com.wtcrmandroid.model.reponsedata.WjournalData;
import com.wtcrmandroid.model.requestdata.WweekSumRequstData;
import com.wtcrmandroid.utils.DateUtils;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.view.dialog.WeekDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 1363655717 on 2017-06-05.
 * 写周总结
 */

public class WriteWeekConclusionActivity extends BaseActivity<WriteWeekSumPresenter,WjournalData> implements WeekDialog.WeekListener {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.lv_write_work_plan)
    ListView lvWriteWorkPlan;
    @BindView(R.id.tv_date_show)
    TextView tvDateShow;

    private int position = 2;
    private String nowWeek;
    List<WriterWeekSumData> list;

    @Override
    protected int layout() {
        return R.layout.activity_write_week_plan;
    }

    @Override
    protected void initView() {
        presenter = new WriteWeekSumPresenter(this);

        titlebar.setTitletext("写周总结");
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WriteWeekConclusionActivity.this.finish();
            }
        });

        nowWeek = new DateUtils().getNowWeek();
        tvDateShow.setText(nowWeek);
        WriterWeekSumData writerWeekPlaneData = new WriterWeekSumData();
        writerWeekPlaneData.setWorkNumber("本周总结");
        list = new ArrayList<>();
        list.add(writerWeekPlaneData);
        lvWriteWorkPlan.setAdapter(new WriterWeekConclusionAdapter(this, list));

    }

    @Override
    public void returnData(int key, WjournalData data) {

        this.finish();

    }

    @OnClick({R.id.ll_select_date, R.id.bt_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_select_date:
                new WeekDialog(this,this,position).show();
                break;
            case R.id.bt_submit:
                WweekSumRequstData wweekSumRequstData = new WweekSumRequstData();
                wweekSumRequstData.setTime(nowWeek);
                wweekSumRequstData.setUserId(MyApplication.application.getLoginData().getUserID());
                wweekSumRequstData.setType("week");
                wweekSumRequstData.setPlan(false);
                wweekSumRequstData.setWork(list);
                wweekSumRequstData.setLearningAndReflection("");

                presenter.SubWeekSum(wweekSumRequstData);
                break;
        }
    }

    //日期选择回调
    @Override
    public void weekSelect(String weekText, int position) {

        this.position = position;
        tvDateShow.setText(weekText);

    }
}
