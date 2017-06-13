package com.wtcrmandroid.activity;

import android.widget.EditText;

import com.wtcrmandroid.R;
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

public class DepartmentEmployeesLogManagementSerachActivity extends BaseActivity   {
    @BindView(R.id.et_search)
    EditText etSearch;
    private Iat iat;



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
        params.put("userName", "shenzhongjia");
        params.put("userPass", MD5Utils.MD5("shen123456"));
        L.e(MD5Utils.MD5("shen123456"));
        HttpRequest.instance().sendPost("http://192.168.0.7/api/Login/UserLogin", params,null, new StringCallBack() {
            @Override
            public void onError(int errorRet, String errorMsg) {

            }

            @Override
            public void onResponse(String response) {

            }

            @Override
            public void onNetError(Exception e) {

            }
        });
//     HashMap<String, String> params = new HashMap<>();
//        params.put("userName", "shenzhongjia");
//        params.put("userPass", MD5Utils.MD5("shen123456"));
//        new HttpUtils().post("UserLogin",params,DepartmentEmployeesLogManagementSerachActivity.this);
////        doVoice();
    }


    @Override
    public void returnData(int key, Object data) {

    }
}
