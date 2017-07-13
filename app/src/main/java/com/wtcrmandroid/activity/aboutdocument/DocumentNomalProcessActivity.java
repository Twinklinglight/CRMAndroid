package com.wtcrmandroid.activity.aboutdocument;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.aboutdocument.presenter.MyApplyDocPresenter;
import com.wtcrmandroid.adapter.recycleview.DocumentDealAdapter;
import com.wtcrmandroid.model.reponsedata.DocumentListRpData;
import com.wtcrmandroid.model.requestdata.MyApplyDocRqData;
import com.wtcrmandroid.view.custompricing.TitleBar;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DocumentNomalProcessActivity extends BaseActivity<MyApplyDocPresenter, List<DocumentListRpData>> {


    @BindView(R.id.rcy_docNomal)
    RecyclerView rcyDocNomal;
    DocumentDealAdapter documentDealAdapter;
    @BindView(R.id.titlebar)
    TitleBar titlebar;

    @Override
    protected int layout() {
        return R.layout.activity_document_nomal_process;
    }

    @Override
    protected void initView() {

        presenter = new MyApplyDocPresenter(this, this);
        titlebar.setTitletext("公文审批");
        MyApplyDocRqData myApplyDocRqData = new MyApplyDocRqData();
        myApplyDocRqData.setUserId(1066);
        myApplyDocRqData.setPageSize(1);
        myApplyDocRqData.setPageCount(6);
        presenter.postMyApply(myApplyDocRqData);

    }

    @Override
    public void returnData(int key, List<DocumentListRpData> data) {

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rcyDocNomal.setLayoutManager(llm);
        documentDealAdapter = new DocumentDealAdapter(this, data);
        rcyDocNomal.setAdapter(documentDealAdapter);
    }

    @OnClick(R.id.circle_write)
    public void onClick() {

    }
}
