package com.wtcrmandroid.activity.aboutdocument;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.view.custompricing.TitleBar;

import butterknife.BindView;
import butterknife.OnClick;

public class SubDocumentActivity extends BaseActivity {


    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.et_doctitle)
    EditText etDoctitle;
    @BindView(R.id.et_docContext)
    EditText etDocContext;
    @BindView(R.id.tv_other)
    TextView tvOther;
    @BindView(R.id.tv_person)
    TextView tvPerson;

    @Override
    protected int layout() {
        return R.layout.activity_sub_document;
    }

    @Override
    protected void initView() {
        titlebar.setTitletext("发起审批");

    }

    @Override
    public void returnData(int key, Object data) {

    }

    @OnClick({R.id.rl_other, R.id.rl_person, R.id.tv_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_other:
                break;
            case R.id.rl_person:
                break;
            case R.id.tv_submit:
                break;
        }
    }
}
