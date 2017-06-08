package com.wtcrmandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wtcrmandroid.http.retrofit2.data.BaseData;
import com.wtcrmandroid.view.AllView;

import butterknife.ButterKnife;

/**
 * Activity基类
 * 申中佳 2017-05-31
 *
 * @param <T> 网络返回实体类对象
 */


public abstract class BaseActivity<T extends BaseData> extends AppCompatActivity implements AllView<T> {

    @Override
    public void showToast(String text) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout());
        ButterKnife.bind(this);
        initview();
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
    protected abstract void initview();
}