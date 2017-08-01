package com.wtcrmandroid.activity.aboutdocument;

import android.support.v7.widget.RecyclerView;

import com.wtcrmandroid.base.BaseActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.view.custompricing.TitleBar;

import butterknife.BindView;

public class DocSelectPersonActivity extends BaseActivity {

    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.rcy_selperson)
    RecyclerView rcySelperson;

    @Override
    protected int layout() {
        return R.layout.activity_doc_select_person;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void returnData(int key, Object data) {

    }
}
