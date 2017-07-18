package com.wtcrmandroid.activity.aboutdocument;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.view.custompricing.TitleBar;

import butterknife.BindView;
import butterknife.OnClick;

public class DocumentDetailsActivity extends BaseActivity {

    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.tv_docTitle)
    TextView tvDocTitle;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_person)
    TextView tvPerson;
    @BindView(R.id.tv_context)
    TextView tvContext;
    @BindView(R.id.rcy_recognize)
    RecyclerView rcyRecognize;

    @Override
    protected int layout() {
        return R.layout.activity_document_details;
    }

    @Override
    protected void initView() {
        titlebar.setTitletext("审批详情");
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentDetailsActivity.this.finish();
            }
        });

    }

    @Override
    public void returnData(int key, Object data) {

    }

    @OnClick({R.id.tv_back, R.id.tv_wait, R.id.tv_apply})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:      //退单
                break;
            case R.id.tv_wait:      //驳回
                break;
            case R.id.tv_apply:     //审批
                break;
        }
    }
}
