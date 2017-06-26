package com.wtcrmandroid.activity.journalmanager;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.BaseActivity;
import com.wtcrmandroid.view.custompricing.TitleBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author zxd
 * @date 2017/6/6
 * 日志管理
 * 修改 6.12 申中佳
 */

public class JournalManagerActivity extends BaseActivity {


    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.rl_my_log)
    RelativeLayout mRlJournalManagerMyjournal;  //我的日志
    @BindView(R.id.ll_journalManager_write_daysum)
    LinearLayout mLlJournalManagerWriteDaysum;  //写日总结
    @BindView(R.id.ll_write_day_plan)
    LinearLayout mLlJournalManagerWriteDayplan; //写日计划
    @BindView(R.id.ll_journalManager_write_weeksum)
    LinearLayout mLlJournalManagerWriteWeeksum; //写周总结
    @BindView(R.id.ll_journalManager_write_weekplan)
    LinearLayout mLlJournalManagerWriteWeekplan; //写周计划
    @BindView(R.id.rl_department_employees_log_management)
    RelativeLayout mRlJournalManagerBmygrz;     //部门员工日志

    private boolean isSales = true;    //是否是销售人员

    @Override
    protected int layout() {
        return R.layout.activity_journal_manager;
    }

    @Override
    protected void initview() {
        mTitlebar.setTitletext("日志管理");
        mTitlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    @OnClick({R.id.ll_journalManager_write_daysum, R.id.rl_my_log,
            R.id.ll_write_day_plan, R.id.ll_journalManager_write_weeksum,
            R.id.ll_journalManager_write_weekplan, R.id.rl_department_employees_log_management})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //写日总结
            case R.id.ll_journalManager_write_daysum:
                if (isSales){
                    startActivity(new Intent(JournalManagerActivity.this,XsWriteDaysumActivity.class));
                }else {
                    startActivity(new Intent(JournalManagerActivity.this,WriteDaySumActivity.class));
                }
                break;
            //写日计划
            case R.id.ll_write_day_plan:
                if (isSales){
                    startActivity(new Intent(JournalManagerActivity.this,XsWriteDayplanActivity.class));
                }else {
                    startActivity(new Intent(JournalManagerActivity.this,WriteDayPlanActivity.class));
                }
                break;
            //写周总结
            case R.id.ll_journalManager_write_weeksum:
                startActivity(new Intent(JournalManagerActivity.this,WriteWeekConclusionActivity.class));
                break;
            //写周计划
            case R.id.ll_journalManager_write_weekplan:
                startActivity(new Intent(JournalManagerActivity.this,WriteWeekPlanActivity.class));
                break;
            //我的日志
            case R.id.rl_my_log:
                startActivity(new Intent(JournalManagerActivity.this, MyJournalActivity.class));
                break;
            //部门日志
            case R.id.rl_department_employees_log_management:
                startActivity(new Intent(JournalManagerActivity.this,DepartmentEmployeesLogManagementMainActivity.class));
                break;

        }
    }

    @Override
    public void returnData(int key, Object data) {

    }
}
