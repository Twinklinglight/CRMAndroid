package com.wtcrmandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.utils.L;
import com.wtcrmandroid.view.AllView;

import butterknife.ButterKnife;

/**
 * Activity基类
 * 申中佳 2017-05-31
 *
 * @param <T> 网络返回实体类对象
 */


public abstract class BaseActivity<T extends BasePresenter, T1> extends AppCompatActivity implements AllView<T1> {
    protected T presenter;

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
//    @Override
//    public void returnBean(int key, String data) {
//
//        returnData(key,list);
//    }
}