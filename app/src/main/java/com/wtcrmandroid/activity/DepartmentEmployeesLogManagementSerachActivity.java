package com.wtcrmandroid.activity;

import android.widget.EditText;

import com.wtcrmandroid.R;
import com.wtcrmandroid.http.retrofit2.data.BaseData;
import com.wtcrmandroid.httpfactory.HttpRequest;
import com.wtcrmandroid.httpfactory.callback.StringCallBack;
import com.wtcrmandroid.iat.Iat;
import com.wtcrmandroid.utils.L;
import com.wtcrmandroid.utils.MD5Utils;

import java.util.HashMap;

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
        HashMap<String, String> params = new HashMap<>();
        params.put("userName", "afdsfga");
        params.put("userPass", MD5Utils.MD5("122"));
        HttpRequest.instance().sendPost("http://192.168.0.7/api/Login/UserLogin", params, DepartmentEmployeesLogManagementMainActivity.class, new StringCallBack() {
            @Override
            public void onError(int errorRet, String errorMsg) {
                   L.e(errorMsg);
            }

            @Override
            public void onNetError(Exception e) {
                L.e("onNetError"+e.toString());
            }

            @Override
            public void onResponse(String response) {
                L.e(response);
            }
        });
//        Map map=new HashMap();
//        map.put("username","18336302752");
//        map.put("psw","18336302752");
//        map.put("key","18336302752");
//        map.put("uuid","fasdfas");
//        new HttpUtils().post("UserLogin",map,DepartmentEmployeesLogManagementSerachActivity.this);
//        doVoice();
    }
}
