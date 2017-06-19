package com.wtcrmandroid.activity;

import android.content.Intent;

import com.wtcrmandroid.R;
import com.wtcrmandroid.data.LoginData;
import com.wtcrmandroid.presenter.activity.LoginPresenter;
import com.wtcrmandroid.utils.L;

import java.util.List;

import butterknife.OnClick;

/**
 * Created by 1363655717 on 2017-06-12.
 */

public class LoginActivity extends BaseActivity<LoginPresenter,List<LoginData>>{


    @Override
    protected int layout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initview() {
        presenter=new LoginPresenter(this);

    }

    @OnClick(R.id.bt_login)
    public void onClick() {
        presenter.login();
    }

    @Override
    public void returnData(int key, List<LoginData> data) {
        L.e("返回数据"+key+data.toString());
        L.e(data.get(0).getUserName());
        startActivity(new Intent(this,MainActivity.class));

    }
}
