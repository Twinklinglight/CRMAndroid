package com.wtcrmandroid.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.wtcrmandroid.permission.PermissionsActivity;
import com.wtcrmandroid.permission.PermissionsChecker;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.utils.L;
import com.wtcrmandroid.view.AllView;

import butterknife.ButterKnife;

/**
 * Activity基类
 * 申中佳 2017-05-31
 * @param <T> 网络返回实体类对象
 */


public abstract class BaseActivity<T extends BasePresenter, T1> extends AppCompatActivity implements AllView<T1> {
    protected T presenter;
    private static final int REQUEST_CODE = 0; // 请求码
    protected PermissionsChecker mPermissionsChecker; // 权限检测器
    protected String[] PERMISSIONS;
    @Override
    public void showShortToast(String text) {
        L.e(text);
        if (!TextUtils.isEmpty(text)) {
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout());
        mPermissionsChecker = new PermissionsChecker(this);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 加载布局
     *
     * @return
     */
    protected abstract int layout();

    /**
     * 初始化方法
     */
    protected abstract void initView();


    protected void startPermissionsActivity() {
        PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
    }
}