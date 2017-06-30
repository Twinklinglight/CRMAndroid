package com.wtcrmandroid.activity.crm;

import android.view.View;
import android.widget.EditText;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wt-pc on 2017/6/30.
 * CRM搜索页面
 */

public class SearchClientLibraryActivity extends BaseActivity {
    @BindView(R.id.et_search)
    EditText etSearch;

    @Override
    public void returnData(int key, Object data) {

    }

    @Override
    protected int layout() {
        return R.layout.activity_search_library;
    }

    @Override
    protected void initView() {

    }



    @OnClick({R.id.iv_left, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:
                break;
        }
    }
}
