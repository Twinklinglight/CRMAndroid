package com.wtcrmandroid.main;

import android.content.Intent;
import android.telephony.TelephonyManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.WTDataBaseManager;
import com.wtcrmandroid.model.reponsedata.LoginData;
import com.wtcrmandroid.presenter.activity.LoginPresenter;
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
        presenter=new LoginPresenter(this);
        TelephonyManager tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        L.e(tm.getDeviceId()+"--");
        presenter=new LoginPresenter(this);
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
}
