package com.wtcrmandroid.activity.aboutdocument;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.view.custompricing.TitleBar;

import butterknife.BindView;

public class DocNomalDetailsActivity extends BaseActivity {


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
    @BindView(R.id.ry_nomalrecord)
    RecyclerView ryNomalrecord;

    @Override
    protected int layout() {
        return R.layout.activity_doc_nomal_details;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void returnData(int key, Object data) {

    }
}
