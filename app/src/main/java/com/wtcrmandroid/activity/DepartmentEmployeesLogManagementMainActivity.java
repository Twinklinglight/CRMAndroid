package com.wtcrmandroid.activity;

import android.content.Intent;
import android.view.View;

import com.wtcrmandroid.R;
import com.wtcrmandroid.custompricing.TitleBar;
import com.wtcrmandroid.http.retrofit2.data.BaseData;

import butterknife.BindView;

/**
 * Created by 1363655717 on 2017-06-07.
 * 部门员工日志管理首页
 */

public class DepartmentEmployeesLogManagementMainActivity extends BaseActivity {
    @BindView(R.id.titlebar)
    TitleBar titlebar;

    @Override
    public void returnData(int key, BaseData data) {

    }

    @Override
    protected int layout() {
        return R.layout.activity_department_employees_log_management_main;
    }

    @Override
    protected void initview() {
        titlebar.setTitletext("部门员工日志");
        titlebar.setRightImageResource(R.mipmap.ico_plus);
        titlebar.setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DepartmentEmployeesLogManagementMainActivity.this,DepartmentEmployeesLogManagementSerachActivity.class));
            }
        });

    }
}
