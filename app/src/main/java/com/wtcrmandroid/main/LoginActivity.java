package com.wtcrmandroid.main;

import android.content.Intent;
import android.telephony.TelephonyManager;

import com.wtcrmandroid.R;
import com.wtcrmandroid.WTDataBaseManager;
import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.model.reponsedata.LoginData;
import com.wtcrmandroid.presenter.activity.LoginPresenter;
import com.wtcrmandroid.utils.L;

import butterknife.OnClick;

/**
 * Created by 1363655717 on 2017-06-12.
 */

public class LoginActivity extends BaseActivity<LoginPresenter,LoginData> {

    @Override
    protected int layout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        presenter=new LoginPresenter(this);
        TelephonyManager tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        L.e(tm.getDeviceId()+"--");
        WTDataBaseManager.getsInstance().initDatabase(this);

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
