package com.wtcrmandroid.main;

import android.Manifest;
import android.content.Intent;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.WTDataBaseManager;
import com.wtcrmandroid.model.reponsedata.LoginData;
import com.wtcrmandroid.presenter.activity.LoginPresenter;
import com.wtcrmandroid.service.GuardianService;
import com.wtcrmandroid.service.JobScheduleService;
import com.wtcrmandroid.service.LocationService;
import com.wtcrmandroid.service.utils.ServiceUtils;
import com.wtcrmandroid.utils.L;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 1363655717 on 2017-06-12.
 */

public class LoginActivity extends BaseActivity<LoginPresenter,LoginData> {

    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_pass)
    EditText etPass;
    @BindView(R.id.cb_passstyle)
    CheckBox cbPassstyle;

    @Override
    protected int layout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        PERMISSIONS = new String[]{
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO

        };
        presenter=new LoginPresenter(this,this);
//        TelephonyManager tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
//        L.e(tm.getDeviceId()+"--");
        presenter=new LoginPresenter(this,this);
        WTDataBaseManager.getsInstance().initDatabase(this);
        etPass.setTransformationMethod(PasswordTransformationMethod.getInstance());

        cbPassstyle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // TODO Auto-generated method stub
                if(cbPassstyle.isChecked()){
                    //设置EditText的密码为可见的
                    etPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    etPass.setSelection(etPass.getText().toString().length());
                }else{
                    //设置密码为隐藏的
                    etPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etPass.setSelection(etPass.getText().toString().length());
                }
            }

        });

    }

    @OnClick(R.id.bt_login)
    public void onClick() {
        presenter.login();
    }

    @Override
    public void returnData(int key, LoginData data) {
        L.e("返回数据"+key+data.toString());
        L.e(data.getUserName());
        startActivity(new Intent(this,MainActivity.class));

    }
    @Override protected void onResume() {
        super.onResume();

        // 缺少权限时, 进入权限配置页面
        if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
            startPermissionsActivity();
        }else {
            boolean isLocalServiceWork = ServiceUtils.isServiceWork(this, "com.wtcrmandroid.service.LocationService");
            boolean isRemoteServiceWork = ServiceUtils.isServiceWork(this, "com.wtcrmandroid.service.GuardianService");
            if(!isLocalServiceWork||
                    !isRemoteServiceWork){
                this.startService(new Intent(this,LocationService.class));
                this.startService(new Intent(this,GuardianService.class));
                Toast.makeText(this, "进程复活", Toast.LENGTH_SHORT).show();
            }

            if(android.os.Build.VERSION.SDK_INT>=21){
                if(ServiceUtils.isServiceWork(this, "com.wtcrmandroid.service.JobScheduleService")) {
                    startService(new Intent(this, JobScheduleService.class));
                }
            }
        }
    }
}
