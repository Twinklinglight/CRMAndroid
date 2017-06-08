package com.wtcrmandroid.activity;

import android.content.Intent;
import android.view.View;

import com.wtcrmandroid.R;
import com.wtcrmandroid.http.retrofit2.data.BaseData;

import butterknife.OnClick;

/**
 * 日志管理界面
 */
public class JournalManagerActivity extends BaseActivity {


    @Override
    protected int layout() {
        return R.layout.activity_journal_manager;
    }

    @Override
    protected void initview() {

    }

    @Override
    public void returnData(int key, BaseData data) {

    }


    @OnClick({R.id.ll_write_day_conclusion, R.id.ll_write_day_plan, R.id.ll_write_week_conclusion, R.id.ll_write_week_plane, R.id.rl_department_employees_log_management, R.id.rl_my_log})
    public void onClick(View view) {
        switch (view.getId()) {
            //写日总结
            case R.id.ll_write_day_conclusion:
                break;
            //写日计划
            case R.id.ll_write_day_plan:
                break;
            //写周总结
            case R.id.ll_write_week_conclusion:
                break;
            //写周计划
            case R.id.ll_write_week_plane:
                startActivity(new Intent(JournalManagerActivity.this, WriteWeekPlanActivity.class));
                break;

            //我的日志
            case R.id.rl_my_log:
                startActivity(new Intent(JournalManagerActivity.this, WriteWeekPlanActivity.class));
                break;
            //部门员工日志
            case R.id.rl_department_employees_log_management:
                startActivity(new Intent(JournalManagerActivity.this, DepartmentEmployeesLogManagementMainActivity.class));
                break;
        }
    }
}
