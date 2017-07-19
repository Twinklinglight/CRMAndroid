package com.wtcrmandroid.activity.journalmanager;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wtcrmandroid.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.activity.journalmanager.present.DepartmentMainPresenter;
import com.wtcrmandroid.adapter.listview.DepartmentJournalAdapter;
import com.wtcrmandroid.model.reponsedata.DepartmentRponseData;
import com.wtcrmandroid.model.requestdata.DepartmentRquestData;
import com.wtcrmandroid.view.custompricing.TitleBar;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 1363655717 on 2017-06-07.
 * 部门员工日志管理首页
 */

public class DepartmentMainActivity extends BaseActivity<DepartmentMainPresenter, List<DepartmentRponseData>> {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.lv_department)
    ListView listDepart;
    private DepartmentJournalAdapter mAdapter;

    private boolean isSale = true;

    @Override
    protected int layout() {
        return R.layout.activity_department_employees_log_management_main;
    }

    @Override
    protected void initView() {

        presenter = new DepartmentMainPresenter(this, this);
        DepartmentRquestData departmentRquestData = new DepartmentRquestData();
        departmentRquestData.setUserId(MyApplication.application.getLoginData().getUserID());
        presenter.postDepartment(departmentRquestData);

        titlebar.setTitletext("部门员工日志");
        titlebar.setRightImageResource(R.mipmap.ic_search);
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DepartmentMainActivity.this.finish();
            }
        });
        titlebar.setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(DepartmentMainActivity.this, DepartmentSerachActivity.class));
            }
        });

    }

    @Override
    public void returnData(int key, final List<DepartmentRponseData> data) {

        mAdapter = new DepartmentJournalAdapter(this, data);
        listDepart.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        listDepart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (isSale){

                    Intent intent = new Intent(DepartmentMainActivity.this, XsJournalDetails.class);
                    intent.putExtra("userid", data.get(position).getUserid());
                    startActivity(intent);

                }else {

                    Intent intent = new Intent(DepartmentMainActivity.this,
                            HtJournalDetails.class);
                    intent.putExtra("userid", data.get(position).getUserid());
                    startActivity(intent);

                }
            }
        });

    }
}
