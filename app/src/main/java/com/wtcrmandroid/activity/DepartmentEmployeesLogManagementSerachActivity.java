package com.wtcrmandroid.activity;

import android.widget.EditText;

import com.wtcrmandroid.R;
import com.wtcrmandroid.http.retrofit2.data.BaseData;
import com.wtcrmandroid.iat.Iat;
import com.wtcrmandroid.utils.L;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 1363655717 on 2017-06-07.
 * 部门员工日志管理搜索页
 */

public class DepartmentEmployeesLogManagementSerachActivity extends BaseActivity {
    @BindView(R.id.et_search)
    EditText etSearch;
    private Iat iat;

    @Override
    public void returnData(int key, BaseData data) {

    }

    @Override
    protected int layout() {
        return R.layout.activity_department_employees_log_management_search;
    }

    @Override
    protected void initview() {

    }

    public void doVoice() {
        if (iat == null) {
            iat = new Iat(this);
        }
        iat.iatRecognize();
        iat.setSetRestult(new Iat.setResult() {
            @Override
            public void succeed(String result) {
                etSearch.setText(result);

            }

            @Override
            public void failed(String iatError) {
                L.e("出现了一个错误，请您重试");
            }
        });
    }

    @OnClick(R.id.tv_right)
    public void onClick() {
        doVoice();
    }
}
